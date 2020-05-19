package de.viadee.uniplaner.constraint;

import de.viadee.uniplaner.domain.Studiengang;
import de.viadee.uniplaner.domain.Vorlesung;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;


import static org.optaplanner.core.api.score.stream.ConstraintCollectors.*;
import static org.optaplanner.core.api.score.stream.Joiners.equal;

public class UniplanerConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                raumKonflikt(constraintFactory),
                terminKonflikt(constraintFactory),
                raumKontinuitaet(constraintFactory),
                wochenKompaktheit(constraintFactory)
        };
    }

    // HARD CONSTRAINTS

    // Der Constraint "Jeder Vorlesung müssen ein Termin und ein Raum zugeordnet sein." wird implizit
    // durch OptaPlanner sichergestellt, da jeder Planungsvariable ein Wert zugewiesen sein muss.

    // Zwei Vorlesungen dürfen nicht zeitgleich im gleichen Raum stattfinden.
    // Jeder Raumkonflikt zählt als 1 Constraint-Match.
    private Constraint raumKonflikt(ConstraintFactory factory) {

      // Planning id wird implizit verglichen, es werden nur voll initialisierte Planning Entities verglichen (Planning Variables gesetzt)
        return factory.fromUniquePair(Vorlesung.class, Joiners.equal(v1 -> v1.getTermin(), v2 -> v2.getTermin()), Joiners.equal(v1 -> v1.getRaum(), v2 -> v2.getRaum()))
                .penalize("Raumkonflikt", HardSoftScore.ONE_HARD);

    }

    // Vorlesungen des gleichen Studiengangs dürfen nicht am gleichen Termin stattfinden.
    // Jeder Terminkonflikt zählt als 1 Constraint-Match.
    private Constraint terminKonflikt(ConstraintFactory factory) {

        // Planning id wird implizit verglichen, es werden nur voll initialisierte Planning Entities verglichen (Planning Variables gesetzt)
        return factory.fromUniquePair(Vorlesung.class, Joiners.equal(v1 -> v1.getTermin(), v2 -> v2.getTermin()), Joiners.equal(v1 -> v1.getStudiengang(), v2 -> v2.getStudiengang()))
                .penalize("Terminkonflikt", HardSoftScore.ONE_HARD);
    }

    // SOFT CONSTRAINTS

    // Die Vorlesungen eines Studiengangs sollten im gleichen Raum stattfinden.
    // Jeder zusätzliche Raum, der für Vorlesungen eines Studiengangs verwendet wird, zählt als 1 Constraint-Match.
    private Constraint raumKontinuitaet(ConstraintFactory factory) {
        return factory.from(Vorlesung.class).filter(v -> v.getRaum()!=null).
                groupBy(v1 -> v1.getStudiengang(), countDistinct(v2 -> v2.getRaum())).
                penalize("Raumkontinuität", HardSoftScore.ONE_SOFT, (studiengang, raumAnzahl) -> raumAnzahl - 1);
    }

    // Die Vorlesungen eines Studiengangs sollten an möglichst wenigen Wochentagen stattfinden.
    // Jeder zusätzliche Tag zählt als 1 Constraint-Match.

    private Constraint wochenKompaktheit(ConstraintFactory factory) {
        return factory.from(Vorlesung.class).filter(v -> v.getTag()!=null).
                groupBy(v1 -> v1.getStudiengang(), countDistinct(v2 -> v2.getTag())).
                penalize("Wochenkompaktheit", HardSoftScore.ONE_SOFT, (studiengang, tagAnzahl) -> tagAnzahl - 1);
    }

    // Die Vorlesungen eines Studiengangs am gleichen Tag sollten aufeinander folgen; es sollte möglichst wenig Freistunden zwischen zwei Vorlesungen geben.
    // Jede "isoliert" stattfindende Vorlesung zählt als 1 Constraint-Match.
    private Constraint tagKompaktheit(ConstraintFactory factory) {

        return factory.from(Vorlesung.class)
                .ifExistsOther(Vorlesung.class, Joiners.equal(v1 -> v1.getTag(), v2 -> v2.getTag()))
                .ifNotExistsOther(Vorlesung.class, Joiners.equal(v1 -> v1.getTag(), v2 -> v2.getTag()),Joiners.equal(v1 -> v1.getIntervallIndex()+1, v2 -> v2.getIntervallIndex()))
                .ifNotExistsOther(Vorlesung.class, Joiners.equal(v1 -> v1.getTag(), v2 -> v2.getTag()),Joiners.equal(v1 -> v1.getIntervallIndex()-1, v2 -> v2.getIntervallIndex()))
                .penalize("TagKompaktheit",
                        HardSoftScore.ONE_SOFT);

    }


}
