package com.ruoyi.common.core.utils.poi.style;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.utils.string.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelStyleFactory {

    private final Map<String, CellStyle> styles = new HashMap<>();

    public Map<String, CellStyle> getStyles() {
        return styles;
    }

    public Map<String, CellStyle> createAllStyles(Workbook wb, List<Object[]> fields) {
        styles.clear();
        styles.put("title", createTitleStyle(wb));
        styles.put("data", createDataStyle(wb));
        styles.put("total", createTotalStyle(wb));

        // Header styles
        for (Object[] os : fields) {
            Excel excel = (Excel) os[1];
            String headerKey = StringUtils.format("header_{}_{}", excel.headerColor(), excel.headerBackgroundColor());
            if (!styles.containsKey(headerKey)) {
                styles.put(headerKey, createHeaderStyle(wb, excel));
            }

            String dataKey = StringUtils.format("data_{}_{}_{}_{}_{}",
                    excel.align(), excel.color(), excel.backgroundColor(),
                    excel.cellType(), excel.wrapText());
            if (!styles.containsKey(dataKey)) {
                styles.put(dataKey, createCustomDataStyle(wb, excel));
            }
        }
        return styles;
    }

    private CellStyle createTitleStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private CellStyle createDataStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        return style;
    }

    private CellStyle createTotalStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        return style;
    }

    private CellStyle createHeaderStyle(Workbook wb, Excel excel) {
        CellStyle style = cloneBorderedStyle(wb);
        style.setFillForegroundColor(excel.headerBackgroundColor().getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        font.setColor(excel.headerColor().getIndex());
        style.setFont(font);
        return style;
    }

    private CellStyle createCustomDataStyle(Workbook wb, Excel excel) {
        CellStyle style = cloneBorderedStyle(wb);
        style.setAlignment(excel.align());
        style.setWrapText(excel.wrapText());
        style.setFillForegroundColor(excel.backgroundColor().getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setColor(excel.color().getIndex());
        style.setFont(font);
        return style;
    }

    private CellStyle cloneBorderedStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        return style;
    }
    public Map<String, CellStyle> createSimpleStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<>();

        CellStyle titleStyle = wb.createCellStyle();
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        styles.put("title", titleStyle);

        CellStyle headerStyle = wb.createCellStyle();
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        styles.put("header", headerStyle);

        return styles;
    }

}
