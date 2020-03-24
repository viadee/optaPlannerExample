package de.viadee;

import de.viadee.service.DataLoaderService;
import de.viadee.service.SolverService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolverApp implements ApplicationRunner {

private final DataLoaderService dataLoaderService;
    private final SolverService solverService;

    public SolverApp( DataLoaderService dataLoaderService, SolverService solverService) {
        this.dataLoaderService = dataLoaderService;
        this.solverService = solverService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SolverApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        solverService.solve(dataLoaderService.getRequestsWithAvailableTimeWindowsBySetId(1),dataLoaderService.getRoutes());

    }
}
