package com.example.bionet.visualization;

import com.example.bionet.model.Disease;
import com.example.bionet.model.Drug;
import com.example.bionet.model.Gene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.Map;



public class GraphDrawer {

    private Map<String, Circle> nodeMap = new HashMap<>();
    private Map<String, Color> nodeColorMap = new HashMap<>();

    public void drawDiseaseGraph(Pane pane,
                                 Disease disease,
                                 TextArea infoArea,
                                 String highlightedNode) {



        // Remove only dynamically drawn graph objects
        pane.getChildren().removeIf(node ->

                node.getUserData() != null &&
                        node.getUserData().equals("GRAPH")

        );

        nodeMap.clear();

        nodeColorMap.clear();

        //---------------- Disease ----------------//

        double diseaseX = 350;
        double diseaseY = 80;

        Circle diseaseCircle = new Circle(diseaseX, diseaseY, 25);
        diseaseCircle.setUserData("GRAPH");

        nodeMap.put(
                disease.getName(),
                diseaseCircle
        );

        nodeColorMap.put(
                disease.getName(),
                Color.ORANGE
        );

        if (disease.getName().equalsIgnoreCase(highlightedNode)) {

            diseaseCircle.setFill(Color.RED);

        } else {

            diseaseCircle.setFill(Color.ORANGE);

        }

        Text diseaseText = new Text(
                diseaseX - 45,
                diseaseY + 45,
                disease.getName()
        );

        diseaseText.setUserData("GRAPH");

        diseaseCircle.setOnMouseClicked(e -> {

            infoArea.setText(

                    "Disease : " + disease.getName()

                            + "\n\nCategory : " + disease.getCategory()

                            + "\n\nDescription :\n"

                            + disease.getDescription()

            );

        });

        pane.getChildren().addAll(
                diseaseCircle,
                diseaseText
        );


        //---------------- Genes ----------------//


        int totalGenes = disease.getGenes().size();

        double paneWidth = pane.getPrefWidth();

        double leftMargin = 70;
        double rightMargin = 70;

        double usableWidth = paneWidth - leftMargin - rightMargin;

        double gap = (totalGenes <= 1)
                ? 0
                : usableWidth / (totalGenes - 1);

        double geneY = 220;

        for (int i = 0; i < totalGenes; i++) {

            Gene gene = disease.getGenes().get(i);

            double x = leftMargin + i * gap;

            Circle geneCircle = new Circle(x, geneY, 20);
            geneCircle.setUserData("GRAPH");

            nodeMap.put(
                    gene.getSymbol(),
                    geneCircle
            );

            nodeColorMap.put(
                    gene.getSymbol(),
                    Color.LIGHTBLUE
            );

            if (gene.getSymbol().equalsIgnoreCase(highlightedNode)) {

                geneCircle.setFill(Color.RED);

            } else {

                geneCircle.setFill(Color.LIGHTBLUE);

            }

            Text geneText = new Text(
                    x - 20,
                    geneY + 35,
                    gene.getSymbol()
            );

            geneText.setUserData("GRAPH");

            Line line = new Line(
                    diseaseX,
                    diseaseY + 45,
                    x,
                    geneY - 20
            );

            line.setUserData("GRAPH");

            geneCircle.setOnMouseClicked(e -> {

                infoArea.setText(

                        "Gene Symbol : " + gene.getSymbol()

                                + "\n\nFull Name : "

                                + gene.getFullName()

                                + "\n\nChromosome : "

                                + gene.getChromosome()

                                + "\n\nDescription :\n"

                                + gene.getDescription()

                );

            });

            pane.getChildren().addAll(
                    line,
                    geneCircle,
                    geneText
            );

        }

        //---------------- Drugs ----------------//

        int totalDrugs = disease.getDrugs().size();

        if (totalDrugs == 0) {

            Text noDrug = new Text(
                    240,
                    430,
                    "No approved drug discovered yet."
            );

            noDrug.setFill(Color.RED);

            noDrug.setUserData("GRAPH");

            pane.getChildren().add(noDrug);

            return;
        }


        double drugY = 420;

        double drugGap = (totalDrugs <= 1)
                ? 0
                : usableWidth / (totalDrugs - 1);

        for (int i = 0; i < totalDrugs; i++) {

            Drug drug = disease.getDrugs().get(i);

            double drugX = leftMargin + i * drugGap;

            Circle drugCircle = new Circle(

                    drugX,
                    drugY,
                    20
            );

            drugCircle.setUserData("GRAPH");

            nodeMap.put(
                    drug.getName(),
                    drugCircle
            );

            nodeColorMap.put(
                    drug.getName(),
                    Color.LIGHTGREEN
            );

            if (drug.getName().equalsIgnoreCase(highlightedNode)) {

                drugCircle.setFill(Color.RED);

            } else {

                drugCircle.setFill(Color.LIGHTGREEN);

            }

            Text drugText = new Text(
                    drugX - 35,
                    drugY + 35,
                    drug.getName()
            );

            drugText.setUserData("GRAPH");

            drugCircle.setOnMouseClicked(e -> {

                infoArea.setText(

                        "Drug : " + drug.getName()

                                + "\n\nClass : "

                                + drug.getDrugClass()

                                + "\n\nMechanism : "

                                + drug.getMechanism()

                                + "\n\nDescription :\n"

                                + drug.getDescription()

                );

            });

            pane.getChildren().addAll(
                    drugCircle,
                    drugText
            );

            // Connect every gene to this drug
            for (int j = 0; j < totalGenes; j++) {

                double geneX = leftMargin + j * gap;

                Line line = new Line(
                        geneX,
                        geneY + 45,
                        drugX,
                        drugY - 20
                );

                line.setUserData("GRAPH");

                pane.getChildren().add(line);

            }

        }

    }

    public void highlightNode(String nodeName) {

        Circle circle = nodeMap.get(nodeName);

        if (circle != null) {

            circle.setFill(Color.RED);

        }

    }

    public void resetColors() {

        for (String nodeName : nodeMap.keySet()) {

            Circle circle = nodeMap.get(nodeName);

            Color color = nodeColorMap.get(nodeName);

            if (circle != null && color != null) {

                circle.setFill(color);

            }

        }
    }

}