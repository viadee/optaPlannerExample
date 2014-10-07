package de.viadee.uniplaner.domain;

public class Studiengang {

    private String name;

    public Studiengang() {
    }

    public Studiengang(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}