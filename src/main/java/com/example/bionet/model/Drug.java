package com.example.bionet.model;

public class Drug {

    private String name;
    private String drugClass;
    private String mechanism;
    private String description;

    public Drug(String name,
                String drugClass,
                String mechanism,
                String description) {

        this.name = name;
        this.drugClass = drugClass;
        this.mechanism = mechanism;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDrugClass() {
        return drugClass;
    }

    public String getMechanism() {
        return mechanism;
    }

    public String getDescription() {
        return description;
    }
}