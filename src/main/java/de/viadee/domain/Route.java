package de.viadee.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.awt.geom.Point2D;

@PlanningEntity
public class Route {

    private double capacity;
    private Point2D positionOfDepot;
    private Request nextRequest;
    public double getCapacity() {
        return capacity;
    }

    public Point2D getPositionOfDepot() {
        return positionOfDepot;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setPositionOfDepot(Point2D positionOfDepot) {
        this.positionOfDepot = positionOfDepot;
    }

    @InverseRelationShadowVariable(sourceVariableName = "previousRoutelement")
    Request getNextRequest() {return nextRequest;}

    void setNextRequest(Request nextRequest){this.nextRequest = nextRequest;}


}
