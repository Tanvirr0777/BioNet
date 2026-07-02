package com.example.bionet.model;

import java.util.List;

public class Disease {

    private String name;
    private String category;
    private List<String> genes;
    private List<String> drugs;
    private String description;

    public Disease(String name,
                   String category,
                   List<String> genes,
                   List<String> drugs,
                   String description) {

        this.name = name;
        this.category = category;
        this.genes = genes;
        this.drugs = drugs;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getGenes() {
        return genes;
    }

    public List<String> getDrugs() {
        return drugs;
    }

    public String getDescription() {
        return description;
    }
}