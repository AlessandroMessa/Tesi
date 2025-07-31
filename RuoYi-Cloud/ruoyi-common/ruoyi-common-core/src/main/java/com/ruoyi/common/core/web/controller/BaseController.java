package com.ruoyi.common.core.web.controller;

import com.ruoyi.common.core.web.controller.binding.DateBinderConfigurator;
import com.ruoyi.common.core.web.controller.pagination.PaginationSupport;
import com.ruoyi.common.core.web.controller.response.AjaxResponseBuilder;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

/**
 * web层通用数据处理
 * 
 * @author ruoyi
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final PaginationSupport paginationSupport = new PaginationSupport();
    protected final AjaxResponseBuilder responseBuilder = new AjaxResponseBuilder();
    protected final DateBinderConfigurator dateBinderConfigurator = new DateBinderConfigurator();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        dateBinderConfigurator.configure(binder);
    }

    protected void startPage() {
        paginationSupport.startPage();
    }

    protected void startOrderBy() {
        paginationSupport.startOrderBy();
    }

    protected void clearPage() {
        paginationSupport.clearPage();
    }

    protected TableDataInfo getDataTable(List<?> list) {
        return paginationSupport.getDataTable(list);
    }

    public AjaxResult success() {
        return responseBuilder.success();
    }

    public AjaxResult success(String message) {
        return responseBuilder.success(message);
    }

    public AjaxResult success(Object data) {
        return responseBuilder.success(data);
    }

    public AjaxResult error() {
        return responseBuilder.error();
    }

    public AjaxResult error(String message) {
        return responseBuilder.error(message);
    }

    public AjaxResult warn(String message) {
        return responseBuilder.warn(message);
    }

    protected AjaxResult toAjax(int rows) {
        return responseBuilder.toAjax(rows);
    }

    protected AjaxResult toAjax(boolean result) {
        return responseBuilder.toAjax(result);
    }
}
