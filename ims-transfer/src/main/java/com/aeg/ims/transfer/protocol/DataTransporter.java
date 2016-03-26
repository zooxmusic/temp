package com.aeg.ims.transfer.protocol;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.JvmSystemExiter;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.launch.support.SystemExiter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Log4j2
public class DataTransporter {

    // package private for unit test
    private static SystemExiter systemExiter = new JvmSystemExiter();
    private ExitCodeMapper exitCodeMapper = new SimpleJvmExitCodeMapper();

    /**
     * start the job execution
     * @return
     */
    int start() {
        ConfigurableApplicationContext context = null;
        try {
            // build a standard ApplicationContext
            context = new ClassPathXmlApplicationContext("contextFromDW.xml");

            // launch the job
            JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
            Job job = (Job) context.getBean("dataTransporter");

            JobExecution jobExecution = jobLauncher.run(job,    new JobParameters());

            return exitCodeMapper.intValue(jobExecution.getExitStatus().getExitCode());

        } catch (Throwable e) {
            String message = "Job Terminated in error: " + e.getMessage();
            log.error(message, e);
            return exitCodeMapper.intValue(ExitStatus.FAILED.getExitCode());
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }

    /**
     * Delegate to the exiter to (possibly) exit the VM gracefully.
     *
     * @param status
     */
    public void exit(int status) {
        systemExiter.exit(status);
    }

    /**
     * main class for starting the batch and initiating the transfer
     * @param args
     */
    public static void main(String[] args) {
        DataTransporter command = new DataTransporter();
        int result = command.start();
        command.exit(result);
    }
}
