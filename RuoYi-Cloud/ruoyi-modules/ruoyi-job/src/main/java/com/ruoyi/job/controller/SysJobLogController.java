package com.ruoyi.job.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.job.domain.dto.SysJobLogDTO;
import com.ruoyi.job.domain.export.SysJobLogExportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.job.service.ISysJobLogService;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/job/log")
public class SysJobLogController extends BaseController
{
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @RequiresPermissions("monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJobLogDTO sysJobLog)
    {
        startPage();
        List<SysJobLogDTO> list = jobLogService.selectJobLogList(sysJobLog);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度日志列表
     */
    @RequiresPermissions("monitor:job:export")
    @Log(title = "任务调度日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJobLogDTO sysJobLog)
    {
        List<SysJobLogDTO> list = jobLogService.selectJobLogList(sysJobLog);
        List<SysJobLogExportVO> exportList = list.stream()
                .map(this::toExportVO)
                .collect(Collectors.toList());

        ExcelUtil<SysJobLogExportVO> util = new ExcelUtil<>(SysJobLogExportVO.class);
        util.exportExcel(response, exportList, "调度日志","title");
    }

    /**
     * 根据调度编号获取详细信息
     */
    @RequiresPermissions("monitor:job:query")
    @GetMapping(value = "/{jobLogId}")
    public AjaxResult getInfo(@PathVariable Long jobLogId)
    {
        return success(jobLogService.selectJobLogById(jobLogId));
    }

    /**
     * 删除定时任务调度日志
     */
    @RequiresPermissions("monitor:job:remove")
    @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobLogIds}")
    public AjaxResult remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @RequiresPermissions("monitor:job:remove")
    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
    private SysJobLogExportVO toExportVO(SysJobLogDTO dto) {
        SysJobLogExportVO vo = new SysJobLogExportVO();
        vo.setJobLogId(dto.getJobLogId());
        vo.setJobName(dto.getJobName());
        vo.setJobGroup(dto.getJobGroup());
        vo.setInvokeTarget(dto.getInvokeTarget());
        vo.setJobMessage(dto.getJobMessage());
        vo.setStatus(dto.getStatus());
        vo.setExceptionInfo(dto.getExceptionInfo());
        vo.setStartTime(dto.getStartTime());
        vo.setStopTime(dto.getStopTime());
        return vo;
    }

}
