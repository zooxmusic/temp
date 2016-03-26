package com.aeg.ims.transfer.protocol;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Util {
    /**
     * Get all file names
     * @param filePath
     * @return array of file names
     * @throws Exception
     */
    public static String[] getAllFileNames(String filePath) throws Exception {
        List fileNames = new ArrayList();
        File fileDir = new File(filePath);
        if (fileDir != null) {
            // get list of all files
            File[] aFiles = fileDir.listFiles();
            if( aFiles!= null )
                for (int x = 0; x < aFiles.length; x++) {
                    // ignore directories
                    if (aFiles[x].isDirectory())
                        continue;
                    fileNames.add(aFiles[x].getCanonicalPath());
                } // end for()
        }
        String[] ret = new String[0];
        return (String[]) fileNames.toArray(ret);
    }
}
