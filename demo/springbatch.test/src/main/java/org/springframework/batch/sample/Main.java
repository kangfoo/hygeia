package org.springframework.batch.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-10
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext c =
                new ClassPathXmlApplicationContext("classpath:/org/springframework/batch/sample/message_job.xml");
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository((JobRepository) c.getBean("jobRepository"));
        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        try {
            launcher.run((Job) c.getBean("messageJob"), new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
