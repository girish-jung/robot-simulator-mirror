package com.girish.jung.service;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;

import java.io.File;

/**
 * Created by girishjung on 1/29/17.
 */
public interface CommandFileReader {

    public File getFile(String filePath) throws Exception;
    public Coordinate readFileAndExecuteCommand(File file) throws Exception;
    public Command createCommand(String line);
}
