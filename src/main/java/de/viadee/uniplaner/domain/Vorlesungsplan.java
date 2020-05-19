package de.viadee.uniplaner.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class Vorlesungsplan {

    @PlanningId
    private Long vorlesungsplanId;

    private List<Vorlesung> vorlesungListe;
    private List<Studiengang> studiengangListe;
    private List<Raum> raumListe;
    private List<Termin> terminListe;
    private List<Wochentag> tagListe;

    private HardSoftScore score;

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<>();
        facts.addAll(studiengangListe);
        facts.addAll(raumListe);
        facts.addAll(terminListe);
        facts.addAll(tagListe);
        return facts;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        List<Vorlesung> otherVorlesungListe = ((Vorlesungsplan) obj).getVorlesungListe();

        if (otherVorlesungListe.size() != vorlesungListe.size()) {
            return false;
        }

        for (int i = 0; i < vorlesungListe.size(); i++) {
            if (!vorlesungListe.get(i).equals(otherVorlesungListe.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        for (Vorlesung vorlesung : vorlesungListe) {
            builder.append(vorlesung);
        }
        return builder.toHashCode();
    }

    public Long getVorlesungsplanId() {
        return vorlesungsplanId;
    }

    public void setVorlesungsplanId(Long vorlesungsplanId) {
        this.vorlesungsplanId = vorlesungsplanId;
    }

    @ValueRangeProvider(id = "raumListe")
    @ProblemFactCollectionProperty
    public List<Raum> getRaumListe() {
        return raumListe;
    }

    @ValueRangeProvider(id = "terminListe")
    @ProblemFactCollectionProperty
    public List<Termin> getTerminListe() {
        return terminListe;
    }

    @PlanningEntityCollectionProperty
    public List<Vorlesung> getVorlesungListe() {
        return vorlesungListe;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @ProblemFactCollectionProperty
    public List<Studiengang> getStudiengangListe() {
        return studiengangListe;
    }

    public void setStudiengangListe(List<Studiengang> studiengangListe) {
        this.studiengangListe = studiengangListe;
    }

    public void setRaumListe(List<Raum> raumListe) {
        this.raumListe = raumListe;
    }

    public void setTerminListe(List<Termin> terminListe) {
        this.terminListe = terminListe;
    }

    public void setVorlesungListe(List<Vorlesung> vorlesungListe) {
        this.vorlesungListe = vorlesungListe;
    }

    @ProblemFactCollectionProperty
    public List<Wochentag> getTagListe() {
        return tagListe;
    }

    public void setTagListe(List<Wochentag> tagListe) {
        this.tagListe = tagListe;
    }
}