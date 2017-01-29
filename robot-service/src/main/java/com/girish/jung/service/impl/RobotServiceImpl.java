package com.girish.jung.service.impl;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;
import com.girish.jung.model.Robot;
import com.girish.jung.service.RobotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by girishjung on 1/29/17.
 */
public class RobotServiceImpl implements RobotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotServiceImpl.class);

    @Override
    public Robot findRobotById(String robotId) {
        return new Robot();
    }

    @Override
    public Coordinate executeCommand(String robotId, Command command) {
        return new Coordinate(3,3, Coordinate.Direction.NORTH);
    }
}
