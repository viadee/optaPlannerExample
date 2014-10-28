package de.viadee.uniplaner.domain;

public enum ZeitIntervall {

    ACHT_UHR(1, "08:00-10:00"), ZEHN_UHR(2, "10:00-12:00"), ZWOELF_UHR(3, "12:00-14:00"), VIERZEHN_UHR(4, "14:00-16:00"), SECHZEHN_UHR(
            5,
            "16:00-18:00"), ACHTZEHN_UHR(6, "18:00-20:00");

    private int id;
    private String name;

    private ZeitIntervall(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}