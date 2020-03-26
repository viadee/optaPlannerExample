package de.viadee.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

import java.awt.geom.Point2D;
import java.util.List;

@PlanningEntity
public class Request {

    private int id;

    private double basketValue;

    private Point2D position;

    private double serviceTime;

    private Route previousRoute;

    public Request(int id, double basketValue, Point2D position){
        this.id = id;
        this.basketValue = basketValue;
        this.position = position;
    }

    // In diesen Zeitfenstern kann der Kunde beliefert werden
    private List<TimeWindow> availableTimeWindows;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBasketValue() {
        return basketValue;
    }

    public void setBasketValue(double basketValue) {
        this.basketValue = basketValue;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getPosition() {
        return position;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public List<TimeWindow> getAvailableTimeWindows() {
        return availableTimeWindows;
    }

    public void setAvailableTimeWindows(List<TimeWindow> availableTimeWindows) {
        this.availableTimeWindows = availableTimeWindows;
    }

    @PlanningVariable(valueRangeProviderRefs = {"routeRange", "requestRange"},
            graphType = PlanningVariableGraphType.CHAINED)
    public Route getPreviousRoute() {
        return previousRoute;
    }

}
