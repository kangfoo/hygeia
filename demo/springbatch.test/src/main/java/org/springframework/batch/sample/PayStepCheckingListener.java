/**
 * 
 */
package org.springframework.batch.sample;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

/**
 * @author kunrey
 * 
 */
public class PayStepCheckingListener extends StepExecutionListenerSupport {

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		String exitCode = stepExecution.getExitStatus().getExitCode();
		if (!exitCode.equals(ExitStatus.FAILED.getExitCode())
				&& stepExecution.getSkipCount() > 0) {
			return new ExitStatus("COMPLETED WITH SKIPS");
		} else {
			return null;
		}
	}

}
