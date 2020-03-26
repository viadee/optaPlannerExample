package de.viadee.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class RoutingSchedule {

    private List<Request> requests;

    private List<Route> routes;

    private HardSoftScore score;

    @PlanningScore
    public HardSoftScore getScore() {
        return this.score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "routeList")
    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "requestRange")
    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

}

