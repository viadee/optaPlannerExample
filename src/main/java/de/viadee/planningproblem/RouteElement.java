package de.viadee.planningproblem;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class RouteElement {

    private Route route;

    // Brauche ich die  position wrklich?
    // Ich habe doch eigentlich durch TimeWindows die Reihenfolge automatisch gegeben oder?
    private int position;

    private TimeWindow timeWindow;

    private Request request;

    private RouteElement previosRouteElement;

    public RouteElement() {

    }

    public RouteElement(int route, int position, TimeWindow timeWindow, Request request) {

    }

    @PlanningVariable(graphType = PlanningVariableGraphType.CHAINED, valueRangeProviderRefs = { "routeList" })
    public RouteElement getPreviousRouteElement() {
        return previosRouteElement;
    }

    public void setPreviosRouteElement(RouteElement previousRouteElement) {
        this.previosRouteElement = previousRouteElement;
    }

    // Welcher Lieferwagen soll den REquest bedienen
    @PlanningVariable(valueRangeProviderRefs = { "routeList" })
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    // An welcher Stelle soll der Request bedient werden
    @PlanningVariable(valueRangeProviderRefs = { "positionList" })
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // zur welcher Zeit soll der Request bedient werden
    @PlanningVariable(valueRangeProviderRefs = { "timewindowList" })
    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    // Welcher Request soll bedient werden
    @PlanningVariable(valueRangeProviderRefs = { "requestList" })
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
