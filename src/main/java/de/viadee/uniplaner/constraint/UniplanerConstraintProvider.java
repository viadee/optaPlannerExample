package de.viadee.uniplaner.constraint;

import de.viadee.uniplaner.domain.Raum;
import de.viadee.uniplaner.domain.Studiengang;
import de.viadee.uniplaner.domain.Vorlesung;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class UniplanerConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                raumKonflikt(constraintFactory),
                terminKonflikt(constraintFactory)
        };
    }

    private Constraint raumKonflikt(ConstraintFactory factory) {
        return factory.fromUniquePair(Vorlesung.class).
                filter((v1, v2) -> v1.getTermin() != null &&
                        v1.getRaum() != null &&
                        v1.getTermin() == v2.getTermin() &&
                        v1.getRaum() == v2.getRaum() &&
                        v1.getId() < v2.getId()).
                penalize("Raumkonflikt", SimpleScore.ONE);

    }

    private Constraint terminKonflikt(ConstraintFactory factory) {
        return factory.fromUniquePair(Vorlesung.class).
                filter((v1, v2) -> v1.getTermin() != null &&
                        v1.getId() < v1.getId() &&
                        v1.getTermin() == v2.getTermin() &&
                        v1.getStudiengang() == v1.getStudiengang()).
                penalize("Terminkonflikt", HardSoftScore.ONE_HARD);
    }

    /*private Constraint raumKontinuitaet(ConstraintFactory factory){
        return  factory.from(Studiengang.class).
                join(Raum.class).
                join(Vorlesung.class).
                filter((s,r,v) -> v.getStudiengang() !=s && v.getRaum() != r).
    }*/
}
