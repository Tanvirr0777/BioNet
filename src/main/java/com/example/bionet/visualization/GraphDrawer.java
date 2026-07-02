package com.example.bionet.visualization;

import com.example.bionet.model.Disease;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphDrawer {

    public void drawDiseaseGraph(Pane pane, Disease disease) {

        pane.getChildren().clear();

        // Disease Node
        double diseaseX = 350;
        double diseaseY = 80;

        Circle diseaseCircle = new Circle(diseaseX, diseaseY, 25);
        diseaseCircle.setFill(Color.ORANGE);

        Text diseaseText = new Text(diseaseX - 40, diseaseY + 40, disease.getName());

        pane.getChildren().addAll(diseaseCircle, diseaseText);

        // ------------------ Genes ------------------

        int totalGenes = disease.getGenes().size();

        double startX = 180;
        double gap = 170;
        double geneY = 220;

        for (int i = 0; i < totalGenes; i++) {

            double x = startX + i * gap;

            Circle geneCircle = new Circle(x, geneY, 20);
            geneCircle.setFill(Color.LIGHTBLUE);

            Text geneText = new Text(x - 20, geneY + 35, disease.getGenes().get(i));

            Line line = new Line(diseaseX, diseaseY + 45, x, geneY - 20);

            pane.getChildren().addAll(line, geneCircle, geneText);

        }

        // ------------------ Drugs ------------------

        int totalDrugs = disease.getDrugs().size();

        if (totalDrugs == 0) {

            Text noDrugText = new Text(
                    250,
                    420,
                    "No approved drug discovered yet."
            );

            noDrugText.setFill(Color.RED);

            pane.getChildren().add(noDrugText);

            return;
        }

        double drugY = 420;

        for (int i = 0; i < totalDrugs; i++) {

            double drugX = 200 + i * 180;

            Circle drugCircle = new Circle(drugX, drugY, 20);
            drugCircle.setFill(Color.LIGHTGREEN);

            Text drugText = new Text(drugX - 35, drugY + 35, disease.getDrugs().get(i));

            pane.getChildren().addAll(drugCircle, drugText);

            // Connect this drug to ALL genes
            for (int j = 0; j < totalGenes; j++) {

                double geneX = startX + j * gap;

                Line line = new Line(
                        geneX,
                        geneY + 45,
                        drugX,
                        drugY - 20
                );

                pane.getChildren().add(line);
            }
        }
    }

}