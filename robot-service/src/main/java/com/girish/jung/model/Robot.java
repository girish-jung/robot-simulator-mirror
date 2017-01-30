package com.girish.jung.model;

import com.girish.jung.service.ActionType;

/**
 * Created by girishjung on 1/29/17.
 */
public class Robot {

    private String id;
    private String name;
    private Coordinate position;
    private boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void turnRight() {
        System.out.println("EXECUTING COMMAND:\t " + ActionType.RIGHT);
        switch(position.getDirection()) {
            case NORTH:
                position.setDirection(Coordinate.Direction.EAST);
                break;

            case SOUTH:
                position.setDirection(Coordinate.Direction.WEST);
                break;

            case EAST:
                position.setDirection(Coordinate.Direction.SOUTH);
                break;

            case WEST:
                position.setDirection(Coordinate.Direction.NORTH);
                break;
        }
    }

    public void turnLeft() {
        System.out.println("EXECUTING COMMAND:\t " + ActionType.LEFT);
        switch(position.getDirection()) {
            case NORTH:
                position.setDirection(Coordinate.Direction.WEST);
                break;

            case SOUTH:
                position.setDirection(Coordinate.Direction.EAST);
                break;

            case EAST:
                position.setDirection(Coordinate.Direction.NORTH);
                break;

            case WEST:
                position.setDirection(Coordinate.Direction.SOUTH);
                break;
        }
    }

    public boolean moveForward() {
        switch (position.getDirection()) {
            case NORTH:
                if (position.getY() < 4) {
                    position.setY(position.getY() + 1);
                    return true;
                }
                break;

            case SOUTH:
                if (position.getY() > 0) {
                    position.setY(position.getY() - 1);
                    return true;
                }
                break;

            case EAST:
                if (position.getX() < 4) {
                    position.setX(position.getX() + 1);
                    return true;
                }
                break;

            case WEST:
                if (position.getX() > 0) {
                    position.setX(position.getX() - 1);
                    return true;
                }
                break;
        }
        return false;
    }
}
