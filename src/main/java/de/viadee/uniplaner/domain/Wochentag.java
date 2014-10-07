package de.viadee.uniplaner.domain;

public enum Wochentag {

    MONTAG("Montag"), DIENSTAG("Dienstag"), MITTWOCH("Mittwoch"), DONNERSTAG("Donnerstag"), FREITAG("Freitag");

    private String name;

    private Wochentag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}