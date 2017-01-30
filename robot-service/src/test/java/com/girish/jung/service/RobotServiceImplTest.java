package com.girish.jung.service;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;
import com.girish.jung.model.Robot;
import com.girish.jung.service.impl.RobotServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by girishjung on 1/29/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RobotServiceImplTest {

    private RobotService robotService;

    @Before
    public void setUp() {
        robotService = new RobotServiceImpl();
    }

    @Test
    public void executeCommandTest_PLACE_valid() {
        Command command = new Command(ActionType.PLACE, new Coordinate(1,2, Coordinate.Direction.SOUTH));
        Robot robot = robotService.findRobotById("1234");
        Coordinate finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertNotNull(finalCoordinate);
        Assert.assertEquals(finalCoordinate, robot.getPosition());
        Assert.assertEquals(finalCoordinate.getX().intValue(), 1);
        Assert.assertEquals(finalCoordinate.getY().intValue(), 2);
        Assert.assertEquals(finalCoordinate.getDirection(), Coordinate.Direction.SOUTH);
    }

    @Test
    public void executeCommandTest_PLACE_invalid() {
        Command command = new Command(ActionType.PLACE, new Coordinate(5,2, Coordinate.Direction.SOUTH));
        Robot robot = robotService.findRobotById("1234");
        Coordinate finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertNull(finalCoordinate);
        Assert.assertEquals(finalCoordinate, robot.getPosition());
    }

    @Test
    public void executeCommandTest_MOVE_valid() {
        Command command = new Command(ActionType.PLACE, new Coordinate(1,4, Coordinate.Direction.SOUTH));
        Robot robot = robotService.findRobotById("1234");
        Coordinate finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertNotNull(finalCoordinate);
        Assert.assertEquals(finalCoordinate, robot.getPosition());
        command = new Command(ActionType.MOVE);
        finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertEquals(finalCoordinate.getDirection(), Coordinate.Direction.SOUTH);
        Assert.assertEquals(finalCoordinate.getY().intValue(), 3);
    }

    @Test
    public void executeCommandTest_MOVE_invalid() {
        Command command = new Command(ActionType.PLACE, new Coordinate(1,0, Coordinate.Direction.SOUTH));
        Robot robot = robotService.findRobotById("1234");
        Coordinate finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertNotNull(finalCoordinate);
        Assert.assertEquals(finalCoordinate, robot.getPosition());
        command = new Command(ActionType.MOVE);
        finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertEquals(finalCoordinate.getDirection(), Coordinate.Direction.SOUTH);
        Assert.assertEquals(finalCoordinate.getY().intValue(), 0);
    }

    @Test
    public void executeCommandTest_RIGHT() {
        Command command = new Command(ActionType.PLACE, new Coordinate(1,0, Coordinate.Direction.SOUTH));
        Robot robot = robotService.findRobotById("1234");
        Coordinate finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertNotNull(finalCoordinate);
        Assert.assertEquals(finalCoordinate, robot.getPosition());
        command = new Command(ActionType.RIGHT);
        finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertEquals(finalCoordinate.getDirection(), Coordinate.Direction.WEST);
    }

    @Test
    public void executeCommandTest_LEFT() {
        Command command = new Command(ActionType.PLACE, new Coordinate(1,0, Coordinate.Direction.SOUTH));
        Robot robot = robotService.findRobotById("1234");
        Coordinate finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertNotNull(finalCoordinate);
        Assert.assertEquals(finalCoordinate, robot.getPosition());
        command = new Command(ActionType.LEFT);
        finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertEquals(finalCoordinate.getDirection(), Coordinate.Direction.EAST);
    }

    @Test
    public void executeCommandTest_REPORT() {
        Command command = new Command(ActionType.PLACE, new Coordinate(1,0, Coordinate.Direction.SOUTH));
        Robot robot = robotService.findRobotById("1234");
        Coordinate finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertNotNull(finalCoordinate);
        Assert.assertEquals(finalCoordinate, robot.getPosition());
        command = new Command(ActionType.REPORT);
        finalCoordinate = robotService.executeCommand(robot, command);
        Assert.assertEquals(finalCoordinate.getDirection(), Coordinate.Direction.SOUTH);
    }
}
