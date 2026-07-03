package com.example.bionet.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.bionet.model.Disease;
import java.util.Map;
import java.util.Optional;

import javafx.stage.Stage;

import com.example.bionet.model.Gene;
import com.example.bionet.model.Drug;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;



public class EditDiseaseController {

    private Map<String, Disease> diseaseDatabase;

    @FXML
    private ComboBox<String> diseaseComboBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextArea geneArea;

    @FXML
    private TextArea drugArea;



    public void setDiseaseDatabase(Map<String, Disease> diseaseDatabase) {

        this.diseaseDatabase = diseaseDatabase;

        diseaseComboBox.getItems().clear();

        diseaseComboBox.getItems().addAll(diseaseDatabase.keySet());

    }


    @FXML
    public void initialize() {

        diseaseComboBox.setOnAction(e -> loadDisease());

    }

    private void loadDisease() {

        String diseaseName = diseaseComboBox.getValue();

        if (diseaseName == null)
            return;

        Disease disease = diseaseDatabase.get(diseaseName);

        if (disease == null)
            return;

        nameField.setText(disease.getName());

        categoryField.setText(disease.getCategory());

        descriptionArea.setText(disease.getDescription());

        geneArea.clear();

        for (Gene gene : disease.getGenes()) {

            geneArea.appendText(gene.getSymbol() + "\n");

        }

        drugArea.clear();

        for (Drug drug : disease.getDrugs()) {

            drugArea.appendText(drug.getName() + "\n");

        }

    }

    @FXML
    private void handleCancel() {

        Stage stage = (Stage) nameField.getScene().getWindow();

        stage.close();

    }

    @FXML
    private void handleSave() {

        String oldName = diseaseComboBox.getValue();

        if (oldName == null)
            return;

        Disease disease = diseaseDatabase.get(oldName);

        if (disease == null)
            return;

        List<Gene> genes = new ArrayList<>();

        for (String line : geneArea.getText().split("\\R")) {

            line = line.trim();

            if (!line.isEmpty()) {

                genes.add(new Gene(line, "", "", ""));

            }

        }

        List<Drug> drugs = new ArrayList<>();

        for (String line : drugArea.getText().split("\\R")) {

            line = line.trim();

            if (!line.isEmpty()) {

                drugs.add(new Drug(line, "", "", ""));

            }

        }

        disease.setName(nameField.getText());

        disease.setCategory(categoryField.getText());

        disease.setDescription(descriptionArea.getText());

        disease.setGenes(genes);

        disease.setDrugs(drugs);

        if (!oldName.equals(disease.getName())) {

            diseaseDatabase.remove(oldName);

            diseaseDatabase.put(disease.getName(), disease);

        }

        if (refreshCallback != null) {

            refreshCallback.run();

        }

        Stage stage = (Stage) nameField.getScene().getWindow();

        stage.close();

    }

    @FXML
    private void handleRemove() {

        String diseaseName = diseaseComboBox.getValue();

        if (diseaseName == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Remove Disease");

        alert.setHeaderText("Delete Disease");

        alert.setContentText(
                "Are you sure you want to remove \"" +
                        diseaseName +
                        "\" ?"
        );

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() &&
                result.get() == ButtonType.OK) {

            diseaseDatabase.remove(diseaseName);

            if (refreshCallback != null) {

                refreshCallback.run();

            }

            Stage stage =
                    (Stage) nameField.getScene().getWindow();

            stage.close();

        }

    }

    private Runnable refreshCallback;

    public void setRefreshCallback(Runnable refreshCallback) {

        this.refreshCallback = refreshCallback;

    }
}