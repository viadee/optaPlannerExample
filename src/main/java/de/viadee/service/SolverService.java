package de.viadee.service;

import de.viadee.domain.Request;
import de.viadee.domain.Route;
import de.viadee.domain.RoutingSchedule;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SolverService {

    private final ResourceLoader resourceLoader;

    private Logger logger = LoggerFactory.getLogger(getClass());
    public SolverService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public HardSoftScore solve(List<Request> requests, List<Route> routes) {
        RoutingSchedule planningProblem =createPlanningProblem(requests,routes);
        Solver solver = createSolver();
        solver.solve(planningProblem);
        RoutingSchedule solution = (RoutingSchedule) solver.getBestSolution();
      //  writeResulte(solution);
        return solution.getScore();
    }



    private Solver createSolver() {
        SolverFactory solverFactory = SolverFactory
                .createFromXmlResource("solverConfigurations/solverConfig_tourenplanung.xml");
      return solverFactory.buildSolver();
    }

    private RoutingSchedule createPlanningProblem(List<Request> requests, List<Route> routes) {
        RoutingSchedule planningProblem = new RoutingSchedule();
        planningProblem.setRequests(requests);
        planningProblem.setRoutes(  routes);
        return planningProblem;
    }
}
