package de.viadee.service;

import de.viadee.planningproblem.RoutingSchedule;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class SolverService {

    private final ResourceLoader resourceLoader;

    public SolverService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public HardSoftScore solve() {
        RoutingSchedule routingSchedule = constructOptaProblem();
        Resource resource = resourceLoader.getResource("classpath:optaplanner.xml");
        SolverFactory<RoutingSchedule> factory = SolverFactory.createFromXmlResource("optaplanner.xml");
        Solver<RoutingSchedule> solver = factory.buildSolver();
        RoutingSchedule solution = solver.solve(routingSchedule);
        return solution.getScore();
    }

    private RoutingSchedule constructOptaProblem() {
        RoutingSchedule routingSchedule = new RoutingSchedule();
        // routingSchedule.setRoutes();
        //routingSchedule.setRequests();
        // TODO: routingSchedule.setRouteElements();
        return routingSchedule;
    }
}
