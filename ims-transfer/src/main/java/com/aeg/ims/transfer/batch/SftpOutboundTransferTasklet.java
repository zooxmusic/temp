package com.aeg.ims.transfer.batch;

import java.io.File;

import com.aeg.ims.transfer.protocol.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;



@Log4j2
public class SftpOutboundTransferTasklet implements Tasklet {

    private String sourceDir;

    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

        ConfigurableApplicationContext context = null;

        try {
            // get the application context
            context = new ClassPathXmlApplicationContext("contextFromDW.xml");

            // create ftpChannel
            MessageChannel ftpChannel = context.getBean("ftpChannel", MessageChannel.class);

            // get the list of files that needs to be transfered
            String[] filesToTransfer = Util.getAllFileNames(sourceDir);

            Message message = null;

            // iterative the files and transfer
            for (String fileName : filesToTransfer) {
                File f = new File(fileName);
                // build message payload
                message = MessageBuilder.withPayload(f).build();
                // transfer the file
                ftpChannel.send(message);
            }
        } finally {
            if (context != null) {
                context.close();
            }
        }
        return RepeatStatus.FINISHED;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }
}
