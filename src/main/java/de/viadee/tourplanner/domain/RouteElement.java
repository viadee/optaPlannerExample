package de.viadee.tourplanner.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class RouteElement {

    private int route;
    private int position;
    private TimeWindow timeWindow;
    private Request request;

    public RouteElement(){

    }

    public RouteElement(int route, int position, TimeWindow timeWindow, Request request){

    }

    @PlanningVariable(valueRangeProviderRefs = { "routeList" })
    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    @PlanningVariable(valueRangeProviderRefs = { "positionList" })
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @PlanningVariable(valueRangeProviderRefs = { "timewindowList" })
    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    @PlanningVariable(valueRangeProviderRefs = { "requestList" })
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
