package com.example.bionet.graph;

import java.util.*;

public class Graph {

    private Map<GraphNode, List<GraphNode>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(GraphNode node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(GraphNode source, GraphNode destination) {

        adjacencyList.get(source).add(destination);

        adjacencyList.get(destination).add(source);

    }

    public Map<GraphNode, List<GraphNode>> getAdjacencyList() {
        return adjacencyList;
    }

}