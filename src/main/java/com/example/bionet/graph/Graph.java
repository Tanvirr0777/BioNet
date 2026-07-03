package com.example.bionet.graph;

import java.util.*;

public class Graph {

    private final Map<GraphNode, List<GraphNode>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(GraphNode node) {

        adjacencyList.putIfAbsent(node, new ArrayList<>());

    }

    public void addEdge(GraphNode source, GraphNode destination) {

        addNode(source);
        addNode(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);

    }

    public List<GraphNode> getNeighbors(GraphNode node) {

        return adjacencyList.getOrDefault(node, new ArrayList<>());

    }

    public Set<GraphNode> getAllNodes() {

        return adjacencyList.keySet();

    }

    public Map<GraphNode, List<GraphNode>> getAdjacencyList() {

        return adjacencyList;

    }

    public GraphNode findNode(String name) {

        for (GraphNode node : adjacencyList.keySet()) {

            if (node.getName().equals(name)) {
                return node;
            }

        }

        return null;
    }

}