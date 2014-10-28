package de.viadee.uniplaner.domain;

public class Studiengang {

    private long id;
    private String name;

    public Studiengang() {
    }

    public Studiengang(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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