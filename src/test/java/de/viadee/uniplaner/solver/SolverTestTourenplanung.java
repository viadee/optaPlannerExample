package de.viadee.uniplaner.solver;

import de.viadee.tourplanner.domain.Request;
import de.viadee.tourplanner.domain.RouteElement;
import de.viadee.tourplanner.domain.RoutingSchedule;
import de.viadee.tourplanner.domain.TimeWindow;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.h2.tools.RunScript;
import org.junit.BeforeClass;
import org.junit.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class SolverTestTourenplanung {

    Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass
    public static void setupDatabase() throws Exception {
        try (Connection conn = getH2Connection(); Statement statement = conn.createStatement()) {
            statement.execute("drop all objects");
            RunScript.execute(conn, new FileReader("./src/test/resources/db-setup-tourenplanung.sql"));
        }
    }

    @Test
    public void testSolver() throws Exception {

        try (Connection conn = getH2Connection()) {

            QueryRunner queryRunner = new QueryRunner();

            // Laden der Ausgangsdaten
            int setInFocus = 1;
            int numberOfRoutes =2;
            List<Request> requestList =queryRunner.query(conn,
                    "SELECT * FROM request WHERE setId = ?", new BeanListHandler<>(Request.class),
                    setInFocus);

            List<TimeWindow> timewindowList = queryRunner.query(conn, "SELECT * FROM timewindow",
                    new BeanListHandler<>(TimeWindow.class));

            for(Request request: requestList){
                System.out.println(request.getId());
                List<Integer> timeWindowIds = queryRunner.query(conn,
                        "SELECT timewindow_id FROM request_v_timewindow WHERE request_id = ?", new BeanListHandler<>(Integer.class),
                        request.getId());

                for(Integer twId : timeWindowIds) {
                    List<TimeWindow> timeWindowList = queryRunner.query(conn,
                            "SELECT * FROM timewindow WHERE id = ?", new BeanListHandler<>(TimeWindow.class),
                            request.getId());
                }
            }

            List<Integer> routeList = Arrays.asList(1,2);
            List<Integer> positionList = this.makeSequence(1,requestList.size());

            // 1) Objekt der PlanningSolution-Klasse RoutingSchedule erzeugen
            RoutingSchedule planningProblem = new RoutingSchedule();
            planningProblem.setRequests(requestList);
            planningProblem.setRoutes(routeList);
            planningProblem.setTimeWindows(timewindowList);
            planningProblem.setPositions(positionList);

            // 2) Solver-Objekt erzeugen
            SolverFactory solverFactory = SolverFactory
                    .createFromXmlResource("solverConfig_tourenplanung.xml");
            Solver solver = solverFactory.buildSolver();

            // 3) Optimierung starten
            solver.solve(planningProblem);

            // 4) Beste gefundene Lösung abrufen
            RoutingSchedule solution = (RoutingSchedule) solver.getBestSolution();

            // Verarbeitung der Optimierungsergebnisse

            List<RouteElement> routeELementList = solution.getRouteElements();
            Collections.sort(routeELementList, new Comparator<RouteElement>() {

                @Override
                public int compare(RouteElement r1, RouteElement r2) {
                    CompareToBuilder builder = new CompareToBuilder();
                    builder.append(r1.getRoute(), r2.getRoute())
                            .append(r1.getPosition(), r2.getPosition());
                    return builder.toComparison();
                }
            });

            StringBuffer sb = new StringBuffer();
            sb.append("Optimierungsergebnis:\n");
            for (RouteElement re : routeELementList) {
                sb.append("Route:"+re.getRoute()+" Position::"+re.getPosition()+" Request: "+re.getRequest().getId()+" Timewindow"+ re.getTimeWindow().getId()+"\n");
            }
            logger.info(sb.toString());
        }
    }

    private static Connection getH2Connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/tourenplaner");
    }


    private List<Integer> makeSequence(int begin, int end) {
        List<Integer> ret = new ArrayList<>(end - begin + 1);
        for (int i=begin; i<=end; i++) {
            ret.add(i);
        }
        return ret;
    }
}