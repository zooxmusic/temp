package com.aeg.ims.service;

import com.aeg.ims.repository.MessageRepository;
import groovy.util.logging.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service("messageService")
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
}
