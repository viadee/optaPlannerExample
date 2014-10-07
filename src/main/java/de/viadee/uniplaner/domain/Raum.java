package de.viadee.uniplaner.domain;

public class Raum {

    private String name;

    public Raum() {
    }

    public Raum(String name) {
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