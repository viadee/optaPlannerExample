package de.viadee;

import de.viadee.service.SolverService;
import de.viadee.service.entity.Request;
import de.viadee.service.entity.TimeWindowEntity;
import de.viadee.service.repository.RequestRepository;
import de.viadee.service.repository.TimeWindowRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.viadee.service.DataLoader;

import java.util.List;

@SpringBootApplication
public class SolverApp implements ApplicationRunner {

    private final DataLoader dataLoader;

    private final SolverService solverService;

    private  final RequestRepository requestRepository;
    private  final TimeWindowRepository timeWindowRepository;

    public SolverApp(DataLoader dataLoader, SolverService solverService, RequestRepository requestRepository,
            TimeWindowRepository timeWindowRepository) {
        this.dataLoader = dataLoader;
        this.solverService = solverService;
        this.requestRepository = requestRepository;
        this.timeWindowRepository = timeWindowRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SolverApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Request> requests = requestRepository.findAll();
       //dataLoader.loadAllTestData();
      //  solverService.solve();
        System.out.println("Test");
    }
}
