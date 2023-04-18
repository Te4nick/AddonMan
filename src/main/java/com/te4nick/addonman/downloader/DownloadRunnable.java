package com.te4nick.addonman;

import com.mojang.logging.LogUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import org.slf4j.Logger;

public class DownloadThread implements Runnable{

    private final Logger LOGGER = LogUtils.getLogger();
    private final String FILE_URL;
    private final String DESTINATION_FILE;

    public DownloadThread(String FILE_URL) {
        this.FILE_URL = FILE_URL;
        this.DESTINATION_FILE = "mods/" + FILE_URL.substring(FILE_URL.lastIndexOf('/'));
    }
    @Override
    public void run() {
        File f = new File(DESTINATION_FILE);
        if(f.exists() && !f.isDirectory()) {
            LOGGER.info("File %s already exists".formatted(DESTINATION_FILE));
            return;
        }
        try {
            URL url = new URL(FILE_URL);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            try (FileOutputStream outputStream = new FileOutputStream(DESTINATION_FILE)) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

        } catch (IOException e) {
            LOGGER.error("URL %s cannot be downloaded".formatted(FILE_URL));
            LOGGER.error(e.toString());
        }
    }
}
