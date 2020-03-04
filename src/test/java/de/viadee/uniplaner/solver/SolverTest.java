package de.viadee.uniplaner.solver;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.h2.tools.RunScript;
import org.junit.BeforeClass;
import org.junit.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.viadee.uniplaner.domain.Raum;
import de.viadee.uniplaner.domain.Studiengang;
import de.viadee.uniplaner.domain.Termin;
import de.viadee.uniplaner.domain.Vorlesung;
import de.viadee.uniplaner.domain.Vorlesungsplan;
import de.viadee.uniplaner.domain.Wochentag;
import de.viadee.uniplaner.domain.ZeitIntervall;

public class SolverTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass
    public static void setupDatabase() throws Exception {
        try (Connection conn = getH2Connection(); Statement statement = conn.createStatement()) {
            statement.execute("drop all objects");
            RunScript.execute(conn, new FileReader("./src/test/resources/db-setup.sql"));
        }
    }

    @Test
    public void testSolver() throws Exception {

        try (Connection conn = getH2Connection()) {

            QueryRunner queryRunner = new QueryRunner();

            // Laden der Ausgangsdaten

            List<Studiengang> studiengangListe = queryRunner.query(conn, "SELECT * FROM studiengang",
                    new BeanListHandler<>(Studiengang.class));

            List<Vorlesung> vorlesungListe = new ArrayList<>();
            for (Studiengang studiengang : studiengangListe) {
                List<Vorlesung> vorlesungenProStudiengang = queryRunner.query(conn,
                        "SELECT * FROM vorlesung WHERE studiengang_id = ?", new BeanListHandler<>(Vorlesung.class),
                        studiengang.getId());
                for (Vorlesung vorlesung : vorlesungenProStudiengang) {
                    vorlesung.setStudiengang(studiengang);
                    vorlesungListe.add(vorlesung);
                }
            }

            List<Raum> raumListe = queryRunner.query(conn, "SELECT * FROM raum", new BeanListHandler<>(Raum.class));

            List<Termin> terminListe = new ArrayList<>();
            for (Wochentag tag : Wochentag.values()) {
                for (ZeitIntervall intervall : ZeitIntervall.values()) {
                    terminListe.add(new Termin(tag, intervall));
                }
            }

            List<Wochentag> tagListe = Arrays.asList(Wochentag.values());

            // 1) Objekt der PlanningSolution-Klasse Vorlesungsplan erzeugen
            Vorlesungsplan planningProblem = new Vorlesungsplan();
            planningProblem.setStudiengangListe(studiengangListe);
            planningProblem.setVorlesungListe(vorlesungListe);
            planningProblem.setRaumListe(raumListe);
            planningProblem.setTerminListe(terminListe);
            planningProblem.setTagListe(tagListe);

            // 2) Solver-Objekt erzeugen
            SolverFactory solverFactory = SolverFactory
                    .createFromXmlResource("solverConfig.xml");
            Solver solver = solverFactory.buildSolver();

            // 3) Optimierung starten
            solver.solve(planningProblem);

            // 4) Beste gefundene Lösung abrufen
            Vorlesungsplan solution = (Vorlesungsplan) solver.getBestSolution();

            // Verarbeitung der Optimierungsergebnisse; in diesem einfachen Testbeispiel wird die Liste der Vorlesungen
            // nach Raum, Tag und Termin sortiert und ausgegeben

            List<Vorlesung> solutionVorlesungListe = solution.getVorlesungListe();
            Collections.sort(solutionVorlesungListe, new Comparator<Vorlesung>() {

                @Override
                public int compare(Vorlesung v1, Vorlesung v2) {
                    CompareToBuilder builder = new CompareToBuilder();
                    builder.append(v1.getRaum().getId(), v2.getRaum().getId())
                            .append(v1.getTag().getId(), v2.getTag().getId())
                            .append(v1.getTermin().getIntervall().getId(), v2.getTermin().getIntervall().getId());
                    return builder.toComparison();
                }
            });

            StringBuffer sb = new StringBuffer();
            sb.append("Optimierungsergebnis:\n");
            for (Vorlesung v : solutionVorlesungListe) {
                sb.append(v.getRaum() + ", " + v.getTag() + ", " + v.getTermin().getIntervall() + " -> " + v + "\n");
            }
            logger.info(sb.toString());
        }
    }

    private static Connection getH2Connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/uniplaner");
    }
}