package org.springframework.batch.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-10
 * Time: 上午11:20
 * Spring中线程池的应用参考 http://blog.csdn.net/shimiso/article/details/8964527
 */
public class Main {


    public static void main(String[] args) {
        // Main1.invoke();
        // Main2.invoke();
         Main3.invoke();

    }

    /**
     *
     * 运行时管理

     Spring Batch 提供了如 表 1 所示的类用于记录每个 Job 的运行信息：
     表 1. 运行时类信息
     类名 	描述
     JobInstance 	该类记录了 Job 的运行实例。举例：10 月和 11 月分别执行同一 Job，将生成两个 JobInstance。主要信息有：标识、版本、Job 名称、Job 参数
     JobExecution 	该类记录了 Job 的运行记录。如上面的示例，Job 第一次运行失败，第二次运行成功，那么将形成两条运行记录，但是对应的是同一个运行实例。主要信息有：Job 的运行时间、运行状态等。
     JobParameters 	该类记录了 Job 的运行参数
     ExecutionContext 	该类主要用于开发人员存储任务运行过程中的相关信息（以键值对形式），主要分为 Job 和 Step 两个范围
     StepExecution 	该类与 JobExecution 类似，主要记录了 Step 的运行记录。包括此次运行读取记录条数、输出记录条数、提交次数、回滚次数、读跳过条数、处理跳过条数、写跳过条数等信息
     *
     *
     */
    private static class Main3 {

        public static final String RUN_MONTH_KEY = "run.month";

        private static void invoke() {
            ClassPathXmlApplicationContext c =
                    new ClassPathXmlApplicationContext("classpath:/org/springframework/batch/sample/message_job.xml");

            SimpleJobLauncher launcher = new SimpleJobLauncher();
            launcher.setJobRepository((JobRepository) c.getBean("jobRepository"));
            launcher.setTaskExecutor(new SyncTaskExecutor());
            try {
                Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
                parameters.put(RUN_MONTH_KEY,new JobParameter("2011-10"));
                JobExecution je = launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));

                System.out.println("该类记录了 Job 的运行实例:\t" + je.getJobInstance());   // 该类记录了 Job 的运行实例。
                System.out.println("该类记录了 Job 的运行记录:\t" + je); // 该类记录了 Job 的运行记录。
                System.out.println("该类记录了 Job 的运行参数:\t" + je.getJobParameters()); // 该类记录了 Job 的运行参数

                System.out.println("该类主要用于开发人员存储任务运行过程中的相关信息（以键值对形式）: \t"+je.getExecutionContext()); // 该类主要用于开发人员存储任务运行过程中的相关信息（以键值对形式）.
                System.out.println("主要记录了 Step 的运行记录:\t" + je.getStepExecutions());  // 主要记录了 Step 的运行记录。
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     *  7.2
     *  考虑到有些数据不能重复执行，那么需要限制批处理的执行。在 springbatch 中可使用 JobParameters 进行限制。
     *
     *  重复执行后，异常信息
     *
     *  org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException:
     *  A job instance already exists and is complete for parameters={}.
     *  If you want to run this job again, change the parameters.
     *
     */
    private static class Main2 {

        public static final String RUN_MONTH_KEY = "run.month";

        private static void invoke() {
            ClassPathXmlApplicationContext c =
                    new ClassPathXmlApplicationContext("classpath:/org/springframework/batch/sample/message_job.xml");

            SimpleJobLauncher launcher = new SimpleJobLauncher();
            launcher.setJobRepository((JobRepository) c.getBean("jobRepository"));
            launcher.setTaskExecutor(new SyncTaskExecutor());
            try {
//                Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
//                parameters.put(RUN_MONTH_KEY,new JobParameter("2011-10"));
//                JobExecution je = launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));
//                System.out.println(je);
//                System.out.println(je.getJobInstance());
//                System.out.println(je.getStepExecutions());

                /* 任务重复执行，只能成功一次 */
//                Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
//                parameters.put(RUN_MONTH_KEY,new JobParameter("2011-10"));
//                launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));
//                parameters.put(RUN_MONTH_KEY,new JobParameter("2011-11"));
//                launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));

                Map<String,JobParameter> parameters = new HashMap<String,JobParameter>();
                parameters.put(RUN_MONTH_KEY,new JobParameter("2011-10"));
                launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));
                Thread.sleep(1000);
                launcher.run((Job) c.getBean("messageJob"),new JobParameters(parameters));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 7.1
     * 基本调用方式
     */
    private static class Main1 {
        private static void invoke() {
            ClassPathXmlApplicationContext c =
                    new ClassPathXmlApplicationContext("classpath:/org/springframework/batch/sample/message_job.xml");
            SimpleJobLauncher launcher = new SimpleJobLauncher();
            launcher.setJobRepository((JobRepository) c.getBean("jobRepository"));

            /*
              (1)SimpleAsyncTaskExecutor 类
            这个实现不重用任何线程，或者说它每次调用都启动一个新线程。但是，它还是支持对并发总数设限，当超过线程并发总数限制时，
            阻塞新的调用，直到有位置被释放。如果你需要真正的池，请继续往下看。
             */
              launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
            try {
                launcher.run((Job) c.getBean("messageJob"), new JobParameters());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
