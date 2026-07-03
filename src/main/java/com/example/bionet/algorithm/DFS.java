package com.example.bionet.algorithm;

import com.example.bionet.graph.Graph;
import com.example.bionet.graph.GraphNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {

    public List<GraphNode> traverse(Graph graph, GraphNode start) {

        List<GraphNode> result = new ArrayList<>();

        Set<GraphNode> visited = new HashSet<>();

        dfs(graph, start, visited, result);

        return result;

    }

    private void dfs(Graph graph,
                     GraphNode current,
                     Set<GraphNode> visited,
                     List<GraphNode> result) {

        visited.add(current);

        result.add(current);

        for (GraphNode neighbor : graph.getNeighbors(current)) {

            if (!visited.contains(neighbor)) {

                dfs(graph, neighbor, visited, result);

            }

        }

    }

}