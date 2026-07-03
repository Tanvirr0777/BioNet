package com.example.bionet.builder;

import com.example.bionet.graph.Graph;
import com.example.bionet.graph.GraphNode;
import com.example.bionet.model.Disease;
import com.example.bionet.model.Drug;
import com.example.bionet.model.Gene;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    public Graph buildGraph(Disease disease) {

        Graph graph = new Graph();

        // ---------------- Disease ----------------

        GraphNode diseaseNode = new GraphNode(
                disease.getName(),
                "Disease"
        );

        graph.addNode(diseaseNode);

        // Store the created gene nodes
        List<GraphNode> geneNodes = new ArrayList<>();

        // ---------------- Genes ----------------

        for (Gene gene : disease.getGenes()) {

            GraphNode geneNode = new GraphNode(
                    gene.getSymbol(),
                    "Gene"
            );

            geneNodes.add(geneNode);

            graph.addEdge(
                    diseaseNode,
                    geneNode
            );

        }

        // ---------------- Drugs ----------------

        if (!disease.getDrugs().isEmpty()) {

            for (int i = 0; i < disease.getDrugs().size(); i++) {

                Drug drug = disease.getDrugs().get(i);

                GraphNode drugNode = new GraphNode(
                        drug.getName(),
                        "Drug"
                );

                // Reuse the existing gene node
                GraphNode connectedGene =
                        geneNodes.get(i % geneNodes.size());

                graph.addEdge(
                        connectedGene,
                        drugNode
                );

            }

        }

        return graph;

    }

}