package de.viadee.uniplaner.constraint;

import com.google.common.base.Joiner;
import de.viadee.uniplaner.domain.Raum;
import de.viadee.uniplaner.domain.Studiengang;
import de.viadee.uniplaner.domain.Vorlesung;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import java.util.function.Function;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.*;
import static org.optaplanner.core.api.score.stream.Joiners.equal;

public class UniplanerConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                raumKonflikt(constraintFactory),
                terminKonflikt(constraintFactory),
                raumKontinuitaet(constraintFactory)
        };
    }

    // HARD CONSTRAINTS

    // Der Constraint "Jeder Vorlesung müssen ein Termin und ein Raum zugeordnet sein." wird implizit
    // durch OptaPlanner sichergestellt, da jeder Planungsvariable ein Wert zugewiesen sein muss.

    // Zwei Vorlesungen dürfen nicht zeitgleich im gleichen Raum stattfinden.
    // Jeder Raumkonflikt zählt als 1 Constraint-Match.

    private Constraint raumKonflikt(ConstraintFactory factory) {
        return factory.fromUniquePair(Vorlesung.class).
                filter((v1, v2) -> v1.getTermin() != null &&
                        v1.getRaum() != null &&
                        v1.getTermin() == v2.getTermin() &&
                        v1.getRaum() == v2.getRaum() &&
                        v1.getId() < v2.getId()).
                penalize("Raumkonflikt", HardSoftScore.ONE_HARD);

    }

    // Vorlesungen des gleichen Studiengangs dürfen nicht am gleichen Termin stattfinden.
    // Jeder Terminkonflikt zählt als 1 Constraint-Match.

    private Constraint terminKonflikt(ConstraintFactory factory) {
        return factory.fromUniquePair(Vorlesung.class).
                filter((v1, v2) -> v1.getTermin() != null &&
                        v1.getId() < v2.getId() &&
                        v1.getTermin() == v2.getTermin() &&
                        v1.getStudiengang() == v2.getStudiengang()).
                penalize("Terminkonflikt", HardSoftScore.ONE_HARD);
    }

    // SOFT CONSTRAINTS

    // Die Vorlesungen eines Studiengangs sollten im gleichen Raum stattfinden.
    // Jeder zusätzliche Raum, der für Vorlesungen eines Studiengangs verwendet wird, zählt als 1 Constraint-Match.

    private Constraint raumKontinuitaet(ConstraintFactory factory) {
        return factory.from(Vorlesung.class).
               groupBy(Vorlesung::getStudiengang, countDistinct(Vorlesung::getRaum)).
                penalize("Raumkontinuität",HardSoftScore.ONE_SOFT,(studiengang, roomCount)-> roomCount-1);
    }

    // Die Vorlesungen eines Studiengangs am gleichen Tag sollten aufeinander folgen; es sollte möglichst wenig Freistunden zwischen zwei Vorlesungen geben.
    // Jede "isoliert" stattfindende Vorlesung zählt als 1 Constraint-Match.
/*    private Constraint targesKompaktheit(ConstraintFactory factory){
        return factory.from(Vorlesung.class).
                join(Studiengang.class,).

    }*/
}
