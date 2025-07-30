package com.ruoyi.job.domain.dto;

import java.util.Date;

public class SysJobLogDTO {
    private Long jobLogId;
    private String jobName;
    private String jobGroup;
    private String invokeTarget;
    private String jobMessage;
    private String status;
    private String exceptionInfo;
    private Date startTime;
    private Date stopTime;

    // Getter e Setter
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
