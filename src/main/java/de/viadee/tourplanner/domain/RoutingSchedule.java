package de.viadee.tourplanner.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@PlanningSolution
public class RoutingSchedule implements Solution<HardSoftScore> {

    private double depot_latitude;
    private double depot_longitude;
    private List<RouteElement> routeElements;
    private List<Request> requests;
    private List<Integer> routes;
    private List<TimeWindow> timeWindows;
    private List<Integer> positions;

    private HardSoftScore score;

    public double getDepot_latitude() {
        return depot_latitude;
    }

    public void setDepot_latitude(double depot_latitude) {
        this.depot_latitude = depot_latitude;
    }

    public double getDepot_longitude() {
        return depot_longitude;
    }

    public void setDepot_longitude(double depot_longitude) {
        this.depot_longitude = depot_longitude;
    }

    @PlanningEntityCollectionProperty
    public List<RouteElement> getRouteElements() {
        return routeElements;
    }

    public void setRouteElements(List<RouteElement> routeElements) {
        this.routeElements = routeElements;
    }

    @ValueRangeProvider(id = "requestList")
    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @ValueRangeProvider(id = "routeList")
    public List<Integer> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Integer> routes) {
        this.routes = routes;
    }

    @ValueRangeProvider(id = "timewindowList")
    public List<TimeWindow> getTimeWindows() {
        return timeWindows;
    }

    public void setTimeWindows(List<TimeWindow> timeWindows) {
        this.timeWindows = timeWindows;
    }

    @ValueRangeProvider(id = "positionList")
    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    @Override
    public HardSoftScore getScore() {
        return this.score;
    }

    @Override
    public void setScore(HardSoftScore score) {
        this.score=score;
    }

    @Override
    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<>();
        facts.addAll(this.timeWindows);
        facts.addAll(this.requests);
        facts.addAll(this.routes);
        //TODO: All facts? Positions?
        return facts;
    }
}
