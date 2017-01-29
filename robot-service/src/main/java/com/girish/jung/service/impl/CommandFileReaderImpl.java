package com.girish.jung.service.impl;

import com.girish.jung.model.Command;
import com.girish.jung.service.CommandFileReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by girishjung on 1/29/17.
 */
public class CommandFileReaderImpl implements CommandFileReader {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommandFileReaderImpl.class);

    public File getFile(String filePath) {

        LOGGER.debug("Reading file {}", filePath);

        File file = null;
        try {
            file = new File(filePath);

            if (!file.exists()) {
                String msg = "No file exists at location: " + file.getAbsolutePath();
                LOGGER.error(msg);
                throw new Exception(msg);
            }

        } catch (Exception e) {
            LOGGER.error("Error while reading the file", e);
        }

        return file;
    }

    public void readFileAndExecuteCommand(File file) {
        if (file == null || !file.exists()) {
            LOGGER.error("Invalid command file. Aborting");
            return;
        }

        LineIterator lineIterator = null;
        try {

            lineIterator = FileUtils.lineIterator(file, "UTF-8");

            while (lineIterator.hasNext()) {
                String line = lineIterator.nextLine().trim();
            }

        } catch (Exception e) {
            String msg = "Error while processing the file: " + file.getName();
            LOGGER.error(msg, e);

        } finally {
            if (lineIterator != null) {
                LineIterator.closeQuietly(lineIterator);
            }
        }


    }

    public Command createCommand(String line) {
        return null;
    }
}
