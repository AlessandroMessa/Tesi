package com.ruoyi.common.core.utils.poi.exporter;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelHandlerAdapter;
import com.ruoyi.common.core.utils.poi.image.ExcelImageHandler;
import com.ruoyi.common.core.utils.poi.mapper.ExcelFieldMapper;
import com.ruoyi.common.core.utils.poi.style.ExcelStyleFactory;
import com.ruoyi.common.core.utils.reflect.ReflectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ExcelExporter<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelExporter.class);

    private final ExcelFieldMapper<T> fieldMapper;
    private final ExcelStyleFactory styleFactory;
    private final ExcelStatisticsTracker statisticsTracker;
    private final ExcelImageHandler imageHandler;

    public ExcelExporter(ExcelFieldMapper<T> fieldMapper,
                         ExcelStyleFactory styleFactory,
                         ExcelStatisticsTracker statisticsTracker,
                         ExcelImageHandler imageHandler) {
        this.fieldMapper = fieldMapper;
        this.styleFactory = styleFactory;
        this.statisticsTracker = statisticsTracker;
        this.imageHandler = imageHandler;
    }

    public void exportTo(HttpServletResponse response, List<T> list, String sheetName, String title) {
        try (SXSSFWorkbook wb = new SXSSFWorkbook(500)) {
            Sheet sheet = wb.createSheet(sheetName);
            List<Object[]> fields = fieldMapper.getMappedFields();
            Map<String, CellStyle> styles = styleFactory.createAllStyles(wb, fields);

            int rownum = 0;

            // Title row
            if (StringUtils.isNotEmpty(title)) {
                Row titleRow = sheet.createRow(rownum++);
                titleRow.setHeightInPoints(30);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellStyle(styles.get("title"));
                titleCell.setCellValue(title);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, fields.size() - 1));
            }

            // Header row
            Row headerRow = sheet.createRow(rownum++);
            int col = 0;
            for (Object[] os : fields) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                Cell cell = headerRow.createCell(col++);
                cell.setCellValue(excel.name());
                cell.setCellStyle(styles.get(headerKey(excel)));
                sheet.setColumnWidth(cell.getColumnIndex(), (int) ((excel.width() + 0.72) * 256));
            }

            // Data rows
            for (T record : list) {
                Row row = sheet.createRow(rownum++);
                int cellNum = 0;

                for (Object[] os : fields) {
                    Field field = (Field) os[0];
                    Excel excel = (Excel) os[1];
                    field.setAccessible(true);
                    Object value = getFieldValue(record, field, excel);
                    String valueStr = Convert.toStr(value);

                    Cell cell = row.createCell(cellNum);
                    setCellValue(cell, value, excel, styles.get(dataKey(excel)), wb);

                    // Aggiorna le statistiche
                    statisticsTracker.addValue(cellNum, valueStr, excel);

                    cellNum++;
                }
            }

            // Riga "Totale" alla fine
            statisticsTracker.writeStatisticsRow(sheet, styles.get("total"));

            // Output
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            wb.write(response.getOutputStream());

        } catch (Exception e) {
            log.error("导出Excel失败", e);
        }
    }

    private void setCellValue(Cell cell, Object value, Excel excel, CellStyle style, Workbook wb) {
        cell.setCellStyle(style);
        if (value == null) {
            cell.setCellValue(excel.defaultValue());
            return;
        }

        try {
            if (!excel.handler().equals(ExcelHandlerAdapter.class)) {
                ExcelHandlerAdapter handler = (ExcelHandlerAdapter) excel.handler().newInstance();
                value = handler.format(value, excel.args(), cell, wb);
                cell.setCellValue(Convert.toStr(value));
                return;
            }

            if (StringUtils.isNotEmpty(excel.readConverterExp())) {
                value = convertByExp(Convert.toStr(value), excel.readConverterExp(), excel.separator());
            }

            switch (excel.cellType()) {
                case NUMERIC:
                    cell.setCellValue(Convert.toDouble(value));
                    break;
                case IMAGE:
                    imageHandler.insertImage(cell, Convert.toStr(value));
                    break;
                case STRING:
                case TEXT:
                default:
                    cell.setCellValue(Convert.toStr(value) + excel.suffix());
            }

        } catch (Exception e) {
            log.error("设置单元格值失败", e);
            cell.setCellValue(Convert.toStr(value));
        }
    }

    private Object getFieldValue(Object entity, Field field, Excel excel) {
        Object value = ReflectUtils.invokeGetter(entity, field.getName());
        if (StringUtils.isNotEmpty(excel.targetAttr())) {
            for (String attr : excel.targetAttr().split("\\.")) {
                value = ReflectUtils.invokeGetter(value, attr);
            }
        }
        return value;
    }

    private String convertByExp(String value, String exp, String separator) {
        StringBuilder sb = new StringBuilder();
        for (String mapping : exp.split(",")) {
            String[] pair = mapping.split("=");
            if (value.contains(separator)) {
                for (String part : value.split(separator)) {
                    if (pair[0].equals(part)) {
                        sb.append(pair[1]).append(separator);
                    }
                }
            } else {
                if (pair[0].equals(value)) return pair[1];
            }
        }
        return StringUtils.stripEnd(sb.toString(), separator);
    }

    private String headerKey(Excel excel) {
        return StringUtils.format("header_{}_{}", excel.headerColor(), excel.headerBackgroundColor());
    }

    private String dataKey(Excel excel) {
        return StringUtils.format("data_{}_{}_{}_{}_{}", excel.align(), excel.color(), excel.backgroundColor(),
                excel.cellType(), excel.wrapText());
    }
}
