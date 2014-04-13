package org.springframework.batch.sample;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-12
 * Time: 下午10:28
 * To change this template use File | Settings | File Templates.
 */
public class MessagesDecider implements JobExecutionDecider {

    public FlowExecutionStatus decide(JobExecution jobExecution,
                                      StepExecution stepExecution) {
        String exitCode = stepExecution.getExitStatus().getExitCode();
        if (!exitCode.equals(ExitStatus.FAILED.getExitCode())
                && stepExecution.getSkipCount() > 0) {
            return new FlowExecutionStatus("COMPLETED WITH SKIPS");
        } else {
            return FlowExecutionStatus.COMPLETED;
        }
    }
}
