package com.aeg.ims.transfer.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.aeg.ims.transfer.model.Person;
import com.aeg.ims.transfer.service.MailService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Log4j2
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Autowired
    private MailService mailService;

    @Autowired
    public JobCompletionNotificationListener() {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            List<Person> results = new LinkedList<Person>();
            /*List<Person> results = jdbcTemplate.query("SELECT first_name, last_name FROM people", new RowMapper<Person>() {
                @Override
                public Person mapRow(ResultSet rs, int row) throws SQLException {
                    return new Person(rs.getString(1), rs.getString(2));
                }
            });*/

            for (Person person : results) {
                log.info("Found <" + person + "> in the database.");
            }
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("brian@109forest.com");
            mailMessage.setReplyTo("transfer@ims.com");
            mailMessage.setSubject("Transfer COMPLETE");
            mailMessage.setText("Yes yes Its completed");
            mailService.send(mailMessage);
        }
    }
}