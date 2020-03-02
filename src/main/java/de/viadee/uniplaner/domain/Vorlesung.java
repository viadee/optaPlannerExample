package de.viadee.uniplaner.domain;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Vorlesung {

    private long id;
    private String name;
    private Studiengang studiengang;
    private Termin termin;
    private Raum raum;

    public Vorlesung() {
    }

    public Vorlesung(long id, String name, Studiengang studiengang) {
        this.id = id;
        this.name = name;
        this.studiengang = studiengang;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PlanningVariable(valueRangeProviderRefs = { "raumListe" })
    public Raum getRaum() {
        return raum;
    }

    @PlanningVariable(valueRangeProviderRefs = { "terminListe" })
    public Termin getTermin() {
        return termin;
    }

    public void setRaum(Raum raum) {
        this.raum = raum;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public Studiengang getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(Studiengang studiengang) {
        this.studiengang = studiengang;
    }

    public Wochentag getTag() {
        return termin != null ? termin.getTag() : null;
    }

    public int getIntervallIndex() {
        return termin != null ? termin.getIntervall().getId() : Integer.MIN_VALUE;
    }

    @Override
    public String toString() {
        return studiengang.getName() + " / " + name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vorlesung other = (Vorlesung) obj;
        return new EqualsBuilder().append(id, other.id).append(name, other.name).append(studiengang, other.studiengang)
                .append(termin, other.termin).append(raum, other.raum).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(studiengang).append(termin).append(raum)
                .toHashCode();
    }
}