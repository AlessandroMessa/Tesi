package com.ruoyi.common.core.web.controller.binding;

import com.ruoyi.common.core.utils.date.DateUtils;
import org.springframework.web.bind.WebDataBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

public class DateBinderConfigurator {

    public void configure(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }
}
