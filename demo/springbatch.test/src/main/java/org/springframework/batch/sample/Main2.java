/**
 * 
 */
package org.springframework.batch.sample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SyncTaskExecutor;

/**
 * @author kunrey
 * 
 */
public class Main2 {

	public static final String RUN_MONTH_KEY = "run.month";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(
				"classpath:/org/springframework/batch/sample/billing_job.xml");
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository((JobRepository) c.getBean("jobRepository"));
		launcher.setTaskExecutor(new SyncTaskExecutor());
		try {
			Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
			parameters.put(RUN_MONTH_KEY, new JobParameter("2011-12"));
			JobExecution je = launcher.run((Job) c.getBean("billingJob"),
					new JobParameters(parameters));
			System.out.println(je);
			System.out.println(je.getJobInstance());
			System.out.println(je.getStepExecutions());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
