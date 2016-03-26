package com.aeg.ims.transfer.service;


import com.aeg.ims.transfer.model.Path;

import java.io.File;

public class PathTranslator {

    public static File toFile(Path path) {
        File directory = new File(path.getName());

        return directory;

    }
}
