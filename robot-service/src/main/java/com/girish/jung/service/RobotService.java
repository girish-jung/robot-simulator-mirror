package com.girish.jung.service;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;

/**
 * Created by girishjung on 1/28/17.
 */
public interface RobotService {

    public Coordinate executeCommand(Command command);
}
