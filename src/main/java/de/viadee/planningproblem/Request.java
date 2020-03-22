package de.viadee.planningproblem;

import org.optaplanner.core.api.domain.entity.PlanningEntity;

import java.awt.geom.Point2D;
import java.util.List;

// Bestellung eines Kunden
public class Request {

    private int id;

    private int setId;

    private double basketValue;

    private Point2D position;

    private double serviceTime;

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

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }
}
