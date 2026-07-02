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

public class GraphDrawer {

    public void drawDiseaseGraph(Pane pane,
                                 Disease disease,
                                 TextArea infoArea) {

        pane.getChildren().clear();

        //---------------- Disease ----------------//

        double diseaseX = 350;
        double diseaseY = 80;

        Circle diseaseCircle = new Circle(diseaseX, diseaseY, 25);
        diseaseCircle.setFill(Color.ORANGE);

        Text diseaseText = new Text(
                diseaseX - 45,
                diseaseY + 45,
                disease.getName()
        );

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

        double startX = 180;
        double gap = 170;
        double geneY = 220;

        for (int i = 0; i < totalGenes; i++) {

            Gene gene = disease.getGenes().get(i);

            double x = startX + i * gap;

            Circle geneCircle = new Circle(x, geneY, 20);
            geneCircle.setFill(Color.LIGHTBLUE);

            Text geneText = new Text(
                    x - 20,
                    geneY + 35,
                    gene.getSymbol()
            );

            Line line = new Line(
                    diseaseX,
                    diseaseY + 45,
                    x,
                    geneY - 20
            );

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

            pane.getChildren().add(noDrug);

            return;
        }

        double drugY = 420;

        for (int i = 0; i < totalDrugs; i++) {

            Drug drug = disease.getDrugs().get(i);

            double drugX = 200 + i * 180;

            Circle drugCircle = new Circle(
                    drugX,
                    drugY,
                    20
            );

            drugCircle.setFill(Color.LIGHTGREEN);

            Text drugText = new Text(
                    drugX - 35,
                    drugY + 35,
                    drug.getName()
            );

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