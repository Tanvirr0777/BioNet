package com.example.bionet.graph;

public class GraphNode {

    private String name;
    private String type;

    public GraphNode(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}