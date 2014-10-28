package de.viadee.uniplaner.domain;

public enum Wochentag {

    MONTAG(1, "Montag"), DIENSTAG(2, "Dienstag"), MITTWOCH(3, "Mittwoch"), DONNERSTAG(4, "Donnerstag"), FREITAG(
            5,
            "Freitag");

    private int id;
    private String name;

    private Wochentag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}