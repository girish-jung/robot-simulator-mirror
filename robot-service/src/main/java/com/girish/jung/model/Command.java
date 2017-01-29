package com.girish.jung.model;

import com.girish.jung.service.ActionType;

import javax.swing.*;

/**
 * Created by girishjung on 1/29/17.
 */
public class Command {

    private Coordinate coordinate;
    private ActionType actionType;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
