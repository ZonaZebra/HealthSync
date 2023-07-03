package com.healthsync.entities;

public class Pharmacy {

    private final int id;
    private final String name;
    private final String crossStreets;

    public Pharmacy(int id, String name, String crossStreets) {
        this.id = id;
        this.name = name;
        this.crossStreets = crossStreets;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCrossStreets() {
        return crossStreets;
    }
}
