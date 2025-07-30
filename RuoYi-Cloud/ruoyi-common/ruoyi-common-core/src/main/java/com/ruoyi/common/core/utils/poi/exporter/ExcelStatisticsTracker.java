package com.ruoyi.common.core.utils.poi.exporter;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.text.Convert;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExcelStatisticsTracker {

    private final Map<Integer, Double> statistics = new HashMap<>();

    /**
     * Aggiunge un valore numerico per la colonna indicata
     */
    public void addValue(int columnIndex, String rawValue, Excel annotation) {
        if (!annotation.isStatistics()) return;

        Double current = statistics.getOrDefault(columnIndex, 0D);
        try {
            Double value = Double.valueOf(rawValue);
            statistics.put(columnIndex, current + value);
        } catch (NumberFormatException ignored) {
            // Ignora valori non numerici
        }
    }

    /**
     * Aggiunge una riga di somma in fondo al foglio
     */
    public void writeStatisticsRow(Sheet sheet, CellStyle totalStyle) {
        if (statistics.isEmpty()) return;

        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell labelCell = row.createCell(0);
        labelCell.setCellStyle(totalStyle);
        labelCell.setCellValue("合计"); // "Totale" in cinese

        Set<Map.Entry<Integer, Double>> entries = statistics.entrySet();
        for (Map.Entry<Integer, Double> entry : entries) {
            int column = entry.getKey();
            Double sum = entry.getValue();
            Cell cell = row.createCell(column);
            cell.setCellStyle(totalStyle);
            cell.setCellValue(sum);
        }

        statistics.clear(); // Reset dopo l'uso
    }
}
