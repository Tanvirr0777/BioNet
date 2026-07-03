package com.example.bionet.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.example.bionet.model.Disease;
import com.example.bionet.model.Drug;
import com.example.bionet.model.Gene;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddDiseaseController {

    @FXML
    private TextField diseaseNameField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextArea geneArea;

    @FXML
    private TextArea drugArea;


    private Disease createdDisease;

    public Disease getCreatedDisease() {

        return createdDisease;

    }

    @FXML
    private void handleSave() {

        List<Gene> genes = new ArrayList<>();

        for (String line : geneArea.getText().split("\\R")) {

            line = line.trim();

            if (!line.isEmpty()) {

                genes.add(

                        new Gene(
                                line,
                                "",
                                "",
                                ""
                        )

                );

            }

        }

        List<Drug> drugs = new ArrayList<>();

        for (String line : drugArea.getText().split("\\R")) {

            line = line.trim();

            if (!line.isEmpty()) {

                drugs.add(

                        new Drug(
                                line,
                                "",
                                "",
                                ""
                        )

                );

            }

        }

        createdDisease = new Disease(

                diseaseNameField.getText(),

                categoryField.getText(),

                genes,

                drugs,

                descriptionArea.getText()

        );

        Stage stage = (Stage) diseaseNameField.getScene().getWindow();

        stage.close();

    }

}