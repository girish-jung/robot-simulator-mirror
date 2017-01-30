package com.girish.jung.service;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;
import com.girish.jung.model.Robot;

/**
 * Created by girishjung on 1/28/17.
 */
public interface RobotService {

    public Robot findRobotById(String robotId);

    public Coordinate executeCommand(Robot robot, Command command);

}
