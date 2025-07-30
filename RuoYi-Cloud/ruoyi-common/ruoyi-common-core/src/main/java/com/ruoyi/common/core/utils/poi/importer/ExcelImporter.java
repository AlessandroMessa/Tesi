package com.ruoyi.common.core.utils.poi.importer;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.exception.UtilException;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.date.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelHandlerAdapter;
import com.ruoyi.common.core.utils.reflect.ReflectUtils;
import com.ruoyi.common.core.utils.poi.mapper.ExcelFieldMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelImporter<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelImporter.class);

    private final Class<T> clazz;
    private final ExcelFieldMapper<T> fieldMapper;

    public ExcelImporter(Class<T> clazz, ExcelFieldMapper<T> fieldMapper) {
        this.clazz = clazz;
        this.fieldMapper = fieldMapper;
    }

    public List<T> importFrom(InputStream is, String sheetName, int titleRows) {
        List<T> list = new ArrayList<>();
        try (Workbook wb = WorkbookFactory.create(is)) {
            Sheet sheet = StringUtils.isNotEmpty(sheetName) ? wb.getSheet(sheetName) : wb.getSheetAt(0);
            if (sheet == null) {
                throw new UtilException("文件sheet不存在");
            }

            Map<String, Integer> columnIndexMap = new HashMap<>();
            Row headerRow = sheet.getRow(titleRows);
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    columnIndexMap.put(cell.getStringCellValue(), i);
                }
            }

            List<Object[]> fields = fieldMapper.getMappedFields();
            Map<Integer, Object[]> fieldsMap = new HashMap<>();
            for (Object[] entry : fields) {
                Excel attr = (Excel) entry[1];
                Integer col = columnIndexMap.get(attr.name());
                if (col != null) {
                    fieldsMap.put(col, entry);
                }
            }

            for (int i = titleRows + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (isRowEmpty(row)) continue;

                T entity = clazz.newInstance();
                for (Map.Entry<Integer, Object[]> entry : fieldsMap.entrySet()) {
                    Object val = getCellValue(row.getCell(entry.getKey()));
                    Field field = (Field) entry.getValue()[0];
                    Excel attr = (Excel) entry.getValue()[1];
                    Object converted = convertValue(val, field, attr, wb, null);
                    ReflectUtils.invokeSetter(entity, field.getName(), converted);
                }
                list.add(entity);
            }
        } catch (Exception e) {
            log.error("导入Excel异常", e);
            throw new UtilException("导入失败: " + e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return list;
    }

    private boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private Object getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case BOOLEAN: return cell.getBooleanCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) return cell.getDateCellValue();
                return cell.getNumericCellValue();
            case FORMULA: return cell.getCellFormula();
            default: return null;
        }
    }

    private Object convertValue(Object val, Field field, Excel attr, Workbook wb, Cell cell) throws Exception {
        if (val == null) return null;
        Class<?> type = field.getType();

        if (attr.handler() != ExcelHandlerAdapter.class) {
            ExcelHandlerAdapter handler = (ExcelHandlerAdapter) attr.handler().newInstance();
            return handler.format(val, attr.args(), cell, wb);
        }

        String strVal = val.toString();
        if (String.class == type) {
            return strVal;
        } else if (Integer.TYPE == type || Integer.class == type) {
            return Convert.toInt(strVal);
        } else if (Long.TYPE == type || Long.class == type) {
            return Convert.toLong(strVal);
        } else if (Double.TYPE == type || Double.class == type) {
            return Convert.toDouble(strVal);
        } else if (Float.TYPE == type || Float.class == type) {
            return Convert.toFloat(strVal);
        } else if (Boolean.TYPE == type || Boolean.class == type) {
            return Convert.toBool(strVal);
        } else if (Date.class == type) {
            return DateUtils.parseDate(strVal);
        } else {
            return strVal;
        }
    }
}
