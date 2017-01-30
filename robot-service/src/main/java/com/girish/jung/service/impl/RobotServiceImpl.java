package com.girish.jung.service.impl;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;
import com.girish.jung.model.Robot;
import com.girish.jung.service.ActionType;
import com.girish.jung.service.RobotService;

/**
 * Created by girishjung on 1/29/17.
 */
public class RobotServiceImpl implements RobotService {

    @Override
    public Robot findRobotById(String robotId) {
        Robot robot = new Robot();
        robot.setId(robotId);
        robot.setName("Robocop");
        return new Robot();
    }

    @Override
    public Coordinate executeCommand(Robot robot, Command command) {
        if (robot != null && command != null && command.getActionType() != null) {

            if (!robot.isActive() && command.getActionType() != ActionType.PLACE) {
                System.out.println("IGNORING COMMAND: Robot is not yet placed on the board. Command Ignored: " + command.toString() + "\n");
                return robot.getPosition();
            }

            switch(command.getActionType()) {

                case PLACE:
                    if (command.getCoordinate().getX() < 5 && command.getCoordinate().getY() < 5) {
                        robot.setPosition(new Coordinate(command.getCoordinate().getX(), command.getCoordinate().getY(), command.getCoordinate().getDirection()));
                        robot.setActive(Boolean.TRUE);
                    } else {
                        System.out.println("IGNORING COMMAND: Invalid coordinate. Command Ignored:" + command.toString() + "\n");
                    }
                    break;

                case MOVE:
                    if (!robot.moveForward()) {
                        System.out.println("IGNORING COMMAND: Robot cannot go outside 5X5 grid. Command Ignored: " + command.toString() + "\n");
                    }
                    break;

                case RIGHT:
                    robot.turnRight();
                    break;

                case LEFT:
                    robot.turnLeft();
                    break;

                case REPORT:
                    System.out.println(robot.getPosition() + "\n");
                    break;
            }
        }

        return robot.getPosition();
    }

}
