package de.viadee.planningproblem;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class RoutingSchedule {

    private List<RouteElement> routeElements;

    private List<Request> requests;

    private List<Route> routes;

    private HardSoftScore score;

    @PlanningEntityCollectionProperty
    public List<RouteElement> getRouteElements() {
        return routeElements;
    }

    public void setRouteElements(List<RouteElement> routeElements) {
        this.routeElements = routeElements;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return this.score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @ProblemFactCollectionProperty
    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @ProblemFactCollectionProperty
    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

}

