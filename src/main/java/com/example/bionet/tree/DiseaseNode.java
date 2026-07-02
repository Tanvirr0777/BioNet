package com.example.bionet.tree;

import java.util.ArrayList;
import java.util.List;

public class DiseaseNode {

    private String name;
    private List<DiseaseNode> children;

    public DiseaseNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(DiseaseNode child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public List<DiseaseNode> getChildren() {
        return children;
    }
}