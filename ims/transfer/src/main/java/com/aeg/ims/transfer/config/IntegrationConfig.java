package com.aeg.ims.transfer.config;

import com.aeg.ims.transfer.model.Client;
import com.aeg.ims.transfer.repository.AppConfigRepository;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.ftp.Ftp;
import org.springframework.integration.file.remote.MessageSessionCallback;
import org.springframework.integration.file.remote.handler.FileTransferringMessageHandler;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.gateway.FtpOutboundGateway;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public IntegrationFlow ftpInboundFlow() {
        return IntegrationFlows
                .from(Ftp.inboundAdapter(this.ftpSessionFactory())
                                .preserveTimestamp(true)
                                .localDirectory(new File("tmp")),
                        e -> e.id("ftpInboundAdapter"))
                .channel(MessageChannels.queue("ftpInboundResultChannel"))
                .get();
    }

    @Bean
    public SessionFactory ftpSessionFactory() {
        DefaultFtpSessionFactory factory = new DefaultFtpSessionFactory();
        Client client = client();
        factory.setHost(client.getFtpHost());
        factory.setUsername(client.getFtpUsername());
        factory.setPassword(client.getFtpPassword());

        return factory;
    }

    private Client client() {
        Client client = new Client();
        client.setName("TRC");
        client.setFtpHost("ftp://anonymous:bszucs%40amersco.com@ftp.swfwmd.state.fl.us/pub/");
        return client;
        //return appConfigRepository.findOne(1l).getClients();
    }
    @Bean
    @InboundChannelAdapter("inboundChannel")
    @ServiceActivator
    public MessageHandler ftpInboundHandler(SessionFactory<FTPFile> sessionFactory) {
        FileTransferringMessageHandler handler = new FileTransferringMessageHandler(this.ftpSessionFactory());
        //handler.setRemoteDirectoryExpression(new LiteralExpression("/"))
        return handler;
    }

    @Bean
    @ServiceActivator(inputChannel = "outboundChannel")
    public MessageHandler ftpOutboundGateway(SessionFactory<FTPFile> sessionFactory) {
        /*FtpOutboundGateway gateway = new FtpOutboundGateway(sessionFactory, new MessageSessionCallback<FTPFile, Object>() {

            @Override
            public String doInSession(Session<FTPFile> session, Message<?> requestMessage) throws IOException {
                return requestMessage.getPayload().toString();
            }
        });*/
        return new FtpOutboundGateway(sessionFactory, (session, requestMessage) -> {
            return session.list(requestMessage.getPayload().toString());
        });
    }
}
