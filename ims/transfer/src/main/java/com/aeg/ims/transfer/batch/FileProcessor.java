package com.aeg.ims.transfer.batch;

import org.springframework.batch.item.ItemProcessor;

import java.io.File;

public class FileProcessor implements ItemProcessor<File, File> {

    @Override
    public File process(File item) throws Exception {
        return null;
    }
}
