package com.girish.jung.model;

/**
 * Created by girishjung on 1/29/17.
 */
public class Coordinate {

    public static enum Direction {
        EAST, WEST, NORTH, SOUTH;
    }

    private int x;
    private int y;
    private Direction direction;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
