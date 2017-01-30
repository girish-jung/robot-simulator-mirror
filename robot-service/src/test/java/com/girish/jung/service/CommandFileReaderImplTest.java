package com.girish.jung.service;

import com.girish.jung.model.Command;
import com.girish.jung.model.Coordinate;
import com.girish.jung.service.impl.CommandFileReaderImpl;
import com.girish.jung.service.impl.RobotServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

/**
 * Created by girishjung on 1/29/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandFileReaderImplTest {

    private CommandFileReader commandFileReader;

    @Before
    public void setUp() {
        RobotService robotService = new RobotServiceImpl();
        commandFileReader = new CommandFileReaderImpl(robotService);
    }

    @Test
    public void getFileTest_validFilePath() throws Exception {
        File file = commandFileReader.getFile("src/test/resources/data/command-test.txt");
        Assert.assertNotNull(file);
    }

    @Test(expected = Exception.class)
    public void getFileTest_invalidFilePath() throws Exception {
        File file = commandFileReader.getFile("Invalid file path");
        Assert.assertNull(file);
    }

    @Test
    public void readFileAndExecuteCommandTest_validFile() throws Exception {
        File file = commandFileReader.getFile("src/test/resources/data/command-test.txt");
        Coordinate coordinate = commandFileReader.readFileAndExecuteCommand(file);

        Assert.assertNotNull(coordinate);
        Assert.assertEquals(coordinate.getX().intValue(), 3);
        Assert.assertEquals(coordinate.getY().intValue(), 3);
        Assert.assertEquals(coordinate.getDirection(), Coordinate.Direction.NORTH);
    }

    @Test(expected = Exception.class)
    public void readFileAndExecuteCommandTest_invalidFile() throws Exception {
        Coordinate coordinate = commandFileReader.readFileAndExecuteCommand(null);
        Assert.assertNull(coordinate);
    }

    @Test
    public void createCommandTest_PLACE() {
        String line  = "PLACE 1,2, East";
        Command command  = commandFileReader.createCommand(line);
        Assert.assertNotNull(command);
        Assert.assertNotNull(command.getCoordinate());
        Assert.assertEquals(command.getActionType(), ActionType.PLACE);
        Assert.assertEquals(command.getCoordinate().getDirection(), Coordinate.Direction.EAST);
        Assert.assertEquals(command.getCoordinate().getX().intValue(), 1);
        Assert.assertEquals(command.getCoordinate().getY().intValue(), 2);
    }

    @Test
    public void createCommandTest_MOVE() {
        String line  = "MOVE ";
        Command command  = commandFileReader.createCommand(line);
        Assert.assertNotNull(command);
        Assert.assertNull(command.getCoordinate());
        Assert.assertEquals(command.getActionType(), ActionType.MOVE);
    }

    @Test
    public void createCommandTest_RIGHT() {
        String line  = " right ";
        Command command  = commandFileReader.createCommand(line);
        Assert.assertNotNull(command);
        Assert.assertNull(command.getCoordinate());
        Assert.assertEquals(command.getActionType(), ActionType.RIGHT);
    }

    @Test
    public void createCommandTest_LEFT() {
        String line  = "LEFT";
        Command command  = commandFileReader.createCommand(line);
        Assert.assertNotNull(command);
        Assert.assertNull(command.getCoordinate());
        Assert.assertEquals(command.getActionType(), ActionType.LEFT);
    }

    @Test
    public void createCommandTest_REPORT() {
        String line  = "report";
        Command command  = commandFileReader.createCommand(line);
        Assert.assertNotNull(command);
        Assert.assertNull(command.getCoordinate());
        Assert.assertEquals(command.getActionType(), ActionType.REPORT);
    }

    @Test
    public void createCommandTest_invalidCommand() {
        String line  = "PLACE 2,3,DELL";
        Command command  = commandFileReader.createCommand(line);
        Assert.assertNull(command);
    }
}
