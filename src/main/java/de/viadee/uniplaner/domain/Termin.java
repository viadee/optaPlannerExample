package de.viadee.uniplaner.domain;

public class Termin {

    private Wochentag tag;
    private ZeitIntervall intervall;

    public Termin() {
    }

    public Termin(Wochentag tag, ZeitIntervall intervall) {
        this.tag = tag;
        this.intervall = intervall;
    }

    public Wochentag getTag() {
        return tag;
    }

    public void setTag(Wochentag tag) {
        this.tag = tag;
    }

    public ZeitIntervall getIntervall() {
        return intervall;
    }

    public void setIntervall(ZeitIntervall intervall) {
        this.intervall = intervall;
    }

    @Override
    public String toString() {
        return tag.toString() + ", " + intervall.toString();
    }
}