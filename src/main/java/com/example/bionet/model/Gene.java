package com.example.bionet.model;

public class Gene {

    private String symbol;
    private String fullName;
    private String chromosome;
    private String description;

    public Gene(String symbol,
                String fullName,
                String chromosome,
                String description) {

        this.symbol = symbol;
        this.fullName = fullName;
        this.chromosome = chromosome;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFullName() {
        return fullName;
    }

    public String getChromosome() {
        return chromosome;
    }

    public String getDescription() {
        return description;
    }

}