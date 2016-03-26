package com.aeg.ims.transfer.service;

import com.aeg.ims.transfer.repository.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AppConfigService {

    @Autowired
    private AppConfigRepository appConfigRepository;


}
