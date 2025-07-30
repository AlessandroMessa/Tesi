package com.ruoyi.common.core.utils.poi;

import com.ruoyi.common.core.annotation.Excel.Type;
import com.ruoyi.common.core.utils.poi.exporter.ExcelExporter;
import com.ruoyi.common.core.utils.poi.exporter.ExcelStatisticsTracker;
import com.ruoyi.common.core.utils.poi.image.ExcelImageHandler;
import com.ruoyi.common.core.utils.poi.importer.ExcelImporter;
import com.ruoyi.common.core.utils.poi.mapper.ExcelFieldMapper;
import com.ruoyi.common.core.utils.poi.style.ExcelStyleFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public class ExcelUtil<T> {

    private final Class<T> clazz;

    // moduli
    private final ExcelFieldMapper<T> fieldMapper;
    private final ExcelImporter<T> importer;
    private final ExcelExporter<T> exporter;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
        // inizializza moduli
        this.fieldMapper = new ExcelFieldMapper<>(clazz, Type.EXPORT, null, null);
        ExcelStyleFactory styleFactory = new ExcelStyleFactory();
        ExcelStatisticsTracker stats = new ExcelStatisticsTracker();
        ExcelImageHandler imageHandler = new ExcelImageHandler();
        this.exporter = new ExcelExporter<>(fieldMapper, styleFactory, stats, imageHandler);
        this.importer = new ExcelImporter<>(clazz, fieldMapper);
    }

    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName, String title) {
        exporter.exportTo(response, list, sheetName, title);
    }

    public List<T> importExcel(InputStream is, String sheetName, int titleRowCount) {
        return importer.importFrom(is, sheetName, titleRowCount);
    }
}
