package com.epam.concurrency.homework6.workers;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class DirectoriesCreator {
    private static final String SEPARATOR = File.separator;
    private static final String TMP_DIRECTORY_NAME = "temp";
    private static final String IN_DIRECTORY_NAME = TMP_DIRECTORY_NAME + SEPARATOR + "in";
    private static final String OUT_DIRECTORY_NAME = TMP_DIRECTORY_NAME + SEPARATOR + "out";

    public static void createDirectories() {
        File tmp = new File(TMP_DIRECTORY_NAME);
        File in = new File(IN_DIRECTORY_NAME);
        File out = new File(OUT_DIRECTORY_NAME);
        List<File> directories = Arrays.asList(tmp, in, out);
        for (File file : directories) {
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
                file.setWritable(true);
            }
        }
    }

    public static String getInDirectoryName() {
        return IN_DIRECTORY_NAME;
    }

    public static String getOutDirectoryName() {
        return OUT_DIRECTORY_NAME;
    }
}
