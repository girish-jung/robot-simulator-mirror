package com.girish.jung;

import com.girish.jung.model.Coordinate;
import com.girish.jung.service.CommandFileReader;
import com.girish.jung.service.impl.CommandFileReaderImpl;
import com.girish.jung.service.impl.RobotServiceImpl;

import java.io.File;

/**
 * Created by girishjung on 1/29/17.
 */
public class RobotSimulationMain {

    private static final String DEFAULT_FILE_PATH = "/data/command.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Robot simulator...");
        CommandFileReader commandFileReader = new CommandFileReaderImpl(new RobotServiceImpl());

        String filePath = getCommandFilePath(args);
        System.out.println("Resolved FilePath: " +  filePath);

        File commandFile = commandFileReader.getFile(filePath);
        Coordinate finalPosition = commandFileReader.readFileAndExecuteCommand(commandFile);

        if (finalPosition != null) {
            System.out.println("\nFinal coordinate of the robot after file processing " + finalPosition);
        }

        System.out.println("Finished processing Robot simulator.");
    }

    public static String getCommandFilePath(String[] args) {
        if (args.length > 0) {
            return args[0];
        }
        return RobotSimulationMain.class.getResource(DEFAULT_FILE_PATH).getPath();
    }
}
