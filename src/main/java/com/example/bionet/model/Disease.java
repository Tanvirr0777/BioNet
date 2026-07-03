package com.example.bionet.model;

import java.util.List;

public class Disease {

    private String name;
    private String category;
    private String description;

    private List<Gene> genes;
    private List<Drug> drugs;

    public Disease(String name,
                   String category,
                   List<Gene> genes,
                   List<Drug> drugs,
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

    public List<Gene> getGenes() {
        return genes;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGenes(List<Gene> genes) {
        this.genes = genes;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}