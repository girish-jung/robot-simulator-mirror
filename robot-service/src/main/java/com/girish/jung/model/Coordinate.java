package com.girish.jung.model;

/**
 * Created by girishjung on 1/29/17.
 */
public class Coordinate {

    public static enum Direction {
        EAST, WEST, NORTH, SOUTH;

        public static Direction getDirectionFromString(String f) {
            switch (f.trim().toUpperCase()) {
                case "EAST":
                    return EAST;
                case "WEST":
                    return WEST;
                case "NORTH":
                    return NORTH;
                case "SOUTH":
                    return SOUTH;
                default:
                    return null;
            }
        }
    }

    private Integer x;
    private Integer y;
    private Direction direction;

    public Coordinate(Integer x, Integer y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
