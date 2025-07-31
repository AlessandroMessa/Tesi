package com.ruoyi.common.core.web.controller.response;

import com.ruoyi.common.core.web.domain.AjaxResult;

public class AjaxResponseBuilder {

    public AjaxResult success() {
        return AjaxResult.success();
    }

    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    public AjaxResult error() {
        return AjaxResult.error();
    }

    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    public AjaxResult warn(String message) {
        return AjaxResult.warn(message);
    }

    public AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    public AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }
}
