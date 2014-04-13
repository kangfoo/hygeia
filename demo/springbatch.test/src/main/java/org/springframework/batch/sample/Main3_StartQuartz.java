package org.springframework.batch.sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main3_StartQuartz {
	
	public static void main(String[] args) {

        ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(
                "classpath:/org/springframework/batch/sample/job-quartz.xml");
	}
}
