package com.ruoyi.common.core.utils.poi.template;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.mapper.ExcelFieldMapper;
import com.ruoyi.common.core.utils.poi.style.ExcelStyleFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ExcelTemplateBuilder<T> {

    private final Class<T> clazz;
    private final ExcelFieldMapper<T> fieldMapper;
    private final ExcelStyleFactory styleFactory;

    public ExcelTemplateBuilder(Class<T> clazz) {
        this.clazz = clazz;
        this.fieldMapper = new ExcelFieldMapper<>(clazz, Excel.Type.IMPORT, null, null);
        this.styleFactory = new ExcelStyleFactory();
    }

    public void exportTemplate(HttpServletResponse response, String sheetName, String title) throws IOException {
        try (SXSSFWorkbook wb = new SXSSFWorkbook()) {
            Sheet sheet = wb.createSheet(sheetName);
            Map<String, CellStyle> styles = styleFactory.createSimpleStyles(wb);

            int rownum = 0;

            if (StringUtils.isNotEmpty(title)) {
                Row titleRow = sheet.createRow(rownum++);
                titleRow.setHeightInPoints(30);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellStyle(styles.get("title"));
                titleCell.setCellValue(title);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, fieldMapper.getMappedFields().size() - 1));
            }

            Row headerRow = sheet.createRow(rownum);
            int col = 0;
            for (Object[] os : fieldMapper.getMappedFields()) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                Cell cell = headerRow.createCell(col);
                cell.setCellStyle(styles.get("header"));
                cell.setCellValue(excel.name());
                sheet.setColumnWidth(col, (int) ((excel.width() + 0.72) * 256));
                col++;
            }

            // Output
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + sheetName + "_template.xlsx");
            wb.write(response.getOutputStream());
        }
    }
}
