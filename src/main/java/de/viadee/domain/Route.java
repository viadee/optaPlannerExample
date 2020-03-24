package de.viadee.domain;

import java.awt.geom.Point2D;

public class Route {
    private int capacity;
    private Point2D positionOfDepot;

    public int getCapacity() {
        return capacity;
    }

    public Point2D getPositionOfDepot() {
        return positionOfDepot;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPositionOfDepot(Point2D positionOfDepot) {
        this.positionOfDepot = positionOfDepot;
    }


}
