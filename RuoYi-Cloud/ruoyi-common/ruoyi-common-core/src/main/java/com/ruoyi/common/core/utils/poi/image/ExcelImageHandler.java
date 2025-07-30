package com.ruoyi.common.core.utils.poi.image;

import com.ruoyi.common.core.utils.string.StringUtils;
import com.ruoyi.common.core.utils.file.FileTypeUtils;
import com.ruoyi.common.core.utils.file.ImageUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;

public class ExcelImageHandler {

    /**
     * Inserisce un'immagine in una cella
     *
     * @param cell La cella target
     * @param imagePath Il percorso dell'immagine
     */
    public void insertImage(Cell cell, String imagePath) {
        if (StringUtils.isEmpty(imagePath)) return;

        try {
            byte[] data = ImageUtils.getImage(imagePath);
            if (data == null || data.length == 0) return;

            Sheet sheet = cell.getSheet();
            Workbook wb = sheet.getWorkbook();
            Drawing<?> drawing = getDrawing(sheet);

            int imageType = getImageType(data);
            int pictureIdx = wb.addPicture(data, imageType);

            ClientAnchor anchor = new XSSFClientAnchor(
                    0, 0, 0, 0,
                    cell.getColumnIndex(), cell.getRowIndex(),
                    cell.getColumnIndex() + 1, cell.getRowIndex() + 1
            );

            drawing.createPicture(anchor, pictureIdx);
        } catch (Exception e) {
            // Silenzia errori, loggabili se necessario
        }
    }

    /**
     * Determina il tipo di immagine da byte[]
     */
    private int getImageType(byte[] value) {
        String ext = FileTypeUtils.getFileExtendName(value);
        if ("JPG".equalsIgnoreCase(ext)) return Workbook.PICTURE_TYPE_JPEG;
        if ("PNG".equalsIgnoreCase(ext)) return Workbook.PICTURE_TYPE_PNG;
        if ("GIF".equalsIgnoreCase(ext)) return Workbook.PICTURE_TYPE_DIB;
        return Workbook.PICTURE_TYPE_JPEG;
    }

    /**
     * Ottiene il disegno per il foglio (o lo crea se assente)
     */
    private Drawing<?> getDrawing(Sheet sheet) {
        Drawing<?> drawing = sheet.getDrawingPatriarch();
        if (drawing == null) {
            drawing = sheet.createDrawingPatriarch();
        }
        return drawing;
    }
}
