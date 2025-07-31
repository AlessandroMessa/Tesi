package com.ruoyi.job.execution;

import com.ruoyi.job.util.jobinvoke.JobInvokeUtil;
import org.quartz.JobExecutionContext;

import com.ruoyi.job.domain.model.SysJob;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
