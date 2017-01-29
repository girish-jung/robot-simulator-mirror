package com.girish.jung.service.impl;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;
import com.girish.jung.model.Robot;
import com.girish.jung.service.ActionType;
import com.girish.jung.service.CommandFileReader;
import com.girish.jung.service.RobotService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by girishjung on 1/29/17.
 */
public class CommandFileReaderImpl implements CommandFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandFileReaderImpl.class);
    private static final String DEFAULT_ROBOT_ID = "rob-123";

    private RobotService robotService;

    public RobotService getRobotService() {
        return robotService;
    }

    public void setRobotService(RobotService robotService) {
        this.robotService = robotService;
    }

    public File getFile(String filePath) throws Exception {

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
            throw e;
        }

        return file;
    }

    public Coordinate readFileAndExecuteCommand(File file) throws Exception {
        if (file == null || !file.exists()) {
            LOGGER.error("Invalid command file. Aborting");
            throw new Exception("Invalid command file");
        }

        LineIterator lineIterator = null;
        Coordinate finalCoordinate = null;
        try {

            lineIterator = FileUtils.lineIterator(file, "UTF-8");
            while (lineIterator.hasNext()) {
                String line = lineIterator.nextLine().trim();
                Command command = createCommand(line);
                finalCoordinate = robotService.executeCommand(DEFAULT_ROBOT_ID, command);
            }

        } catch (Exception e) {
            String msg = "Error while processing the file: " + file.getName();
            LOGGER.error(msg, e);
            throw e;

        } finally {
            if (lineIterator != null) {
                LineIterator.closeQuietly(lineIterator);
            }
        }
        return finalCoordinate;
    }

    public Command createCommand(String line) {
        if (StringUtils.isBlank(line)) {
            LOGGER.error("Invalid command");
            System.out.println("Invalid command. Command cannot be blank");
            return null;
        }

        try {
            String lineUpperCase = line.toUpperCase();

            // If clause to create a PLACE command
            if (lineUpperCase.startsWith(ActionType.PLACE.toString())) {
                String[] temp = lineUpperCase.split(" ", 2);

                if (temp.length != 2) {
                    System.out.println("Invalid command line");
                    return null;
                }

                String action = temp[0];
                ActionType actionType = ActionType.getActionTypeFromString(action);

                if (actionType == null) {
                    System.out.println("Invalid command line");
                    return null;
                }

                String str = temp[1];
                String[] arr = str.split(",");

                if (arr.length != 3) {
                    System.out.println("Invalid command line");
                    return null;
                }

                Integer x = Integer.parseInt(arr[0].trim());
                Integer y = Integer.parseInt(arr[1].trim());
                Coordinate.Direction direction = Coordinate.Direction.getDirectionFromString(arr[2].trim());

                if (x != null && y != null && direction != null) {
                    Coordinate coordinate = new Coordinate(x, y, direction);
                    return new Command(actionType, coordinate);
                }
            }

            // Creates all other commands - MOVE, RIGHT, LEFT, REPORT
            ActionType actionType = ActionType.getActionTypeFromString(line);
            if (actionType != null) {
                return new Command(actionType);
            }

        } catch (Exception e) {
            LOGGER.error("Exception occurred while creating command. Command Line: " + line, e);
            System.out.println("Invalid command line : " + line);
        }

        return null;
    }

}
