package com.ruoyi.job.domain.export;

import com.ruoyi.common.core.annotation.Excel;
import java.util.Date;

public class SysJobLogExportVO {

    @Excel(name = "日志序号")
    private Long jobLogId;

    @Excel(name = "任务名称")
    private String jobName;

    @Excel(name = "任务组名")
    private String jobGroup;

    @Excel(name = "调用目标字符串")
    private String invokeTarget;

    @Excel(name = "日志信息")
    private String jobMessage;

    @Excel(name = "执行状态", readConverterExp = "0=正常,1=失败")
    private String status;

    @Excel(name = "异常信息")
    private String exceptionInfo;

    @Excel(name = "开始时间")
    private Date startTime;

    @Excel(name = "结束时间")
    private Date stopTime;

    // getter/setter
    public Long getJobLogId() { return jobLogId; }
    public void setJobLogId(Long jobLogId) { this.jobLogId = jobLogId; }

    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getJobGroup() { return jobGroup; }
    public void setJobGroup(String jobGroup) { this.jobGroup = jobGroup; }

    public String getInvokeTarget() { return invokeTarget; }
    public void setInvokeTarget(String invokeTarget) { this.invokeTarget = invokeTarget; }

    public String getJobMessage() { return jobMessage; }
    public void setJobMessage(String jobMessage) { this.jobMessage = jobMessage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getExceptionInfo() { return exceptionInfo; }
    public void setExceptionInfo(String exceptionInfo) { this.exceptionInfo = exceptionInfo; }

    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getStopTime() { return stopTime; }
    public void setStopTime(Date stopTime) { this.stopTime = stopTime; }
}
