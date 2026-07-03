package com.example.bionet.algorithm;

import com.example.bionet.graph.Graph;
import com.example.bionet.graph.GraphNode;

import java.util.*;

public class BFS {

    public List<GraphNode> traverse(Graph graph, GraphNode start) {

        List<GraphNode> result = new ArrayList<>();

        Queue<GraphNode> queue = new LinkedList<>();

        Set<GraphNode> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            GraphNode current = queue.poll();

            result.add(current);

            for (GraphNode neighbor : graph.getNeighbors(current)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.offer(neighbor);

                }

            }

        }

        return result;
    }

}