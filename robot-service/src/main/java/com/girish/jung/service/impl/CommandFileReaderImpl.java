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

import java.io.File;

/**
 * Created by girishjung on 1/29/17.
 */
public class CommandFileReaderImpl implements CommandFileReader {

    private static final String DEFAULT_ROBOT_ID = "rob-123";

    private RobotService robotService;

    public CommandFileReaderImpl(RobotService robotService) {
        this.robotService = robotService;
    }

    public RobotService getRobotService() {
        return robotService;
    }

    public void setRobotService(RobotService robotService) {
        this.robotService = robotService;
    }

    public File getFile(String filePath) throws Exception {

        System.out.println("Reading file " + filePath);

        File file = null;
        try {
            file = new File(filePath);

            if (!file.exists()) {
                String msg = "No file exists at location: " + file.getAbsolutePath();
                System.out.println(msg);
                throw new Exception(msg);
            }

        } catch (Exception e) {
            System.out.println("Error while reading the file");
            throw e;
        }

        return file;
    }

    public Coordinate readFileAndExecuteCommand(File file) throws Exception {
        if (file == null || !file.exists()) {
            System.out.println("Invalid command file. Aborting");
            throw new Exception("Invalid command file");
        }

        LineIterator lineIterator = null;
        Robot robot = robotService.findRobotById(DEFAULT_ROBOT_ID);
        try {

            lineIterator = FileUtils.lineIterator(file, "UTF-8");
            while (lineIterator.hasNext()) {
                String line = lineIterator.nextLine().trim();
                Command command = createCommand(line);

                robotService.executeCommand(robot, command);
            }

        } catch (Exception e) {
            System.out.println("Error while processing the file: " + file.getName());
            throw e;

        } finally {
            if (lineIterator != null) {
                LineIterator.closeQuietly(lineIterator);
            }
        }
        return robot.getPosition();
    }

    public Command createCommand(String line) {
        if (StringUtils.isBlank(line)) {
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
            System.out.println("Exception occurred while creating command. Command Line: " + line);
            System.out.println("Invalid command line : " + line);
        }

        return null;
    }

}
