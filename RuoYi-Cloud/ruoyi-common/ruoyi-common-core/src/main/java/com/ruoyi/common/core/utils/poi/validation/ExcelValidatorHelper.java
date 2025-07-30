package com.ruoyi.common.core.utils.poi.validation;

import com.ruoyi.common.core.utils.string.StringUtils;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

public class ExcelValidatorHelper {

    /**
     * Aggiunge validazione con prompt o dropdown
     */
    public void addPromptOrValidation(Sheet sheet, String[] combo, String prompt, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();

        DataValidationConstraint constraint =
                combo.length > 0 ? helper.createExplicitListConstraint(combo)
                        : helper.createCustomConstraint("DD1");

        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidation validation = helper.createValidation(constraint, regions);

        if (StringUtils.isNotEmpty(prompt)) {
            validation.createPromptBox("", prompt);
            validation.setShowPromptBox(true);
        }

        if (validation instanceof XSSFDataValidation) {
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
        } else {
            validation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(validation);
    }
}
