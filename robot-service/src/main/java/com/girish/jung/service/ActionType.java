package com.girish.jung.service;

/**
 * Created by girishjung on 1/28/17.
 */
public enum ActionType {

    MOVE, RIGHT, LEFT, REPORT, PLACE;

    public static ActionType getActionTypeFromString(String action) {
        switch(action.trim().toUpperCase()) {
            case "MOVE":
                return MOVE;
            case "RIGHT":
                return RIGHT;
            case "LEFT":
                return LEFT;
            case "REPORT":
                return REPORT;
            case "PLACE":
                return PLACE;
            default:
                return null;
        }
    }
}
