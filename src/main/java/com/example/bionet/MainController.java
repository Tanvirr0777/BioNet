package com.example.bionet;
import com.example.bionet.model.Disease;
import com.example.bionet.tree.DiseaseNode;
import com.example.bionet.tree.DiseaseTree;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import com.example.bionet.visualization.GraphDrawer;


public class MainController {

    @FXML
    private Label statusLabel;

    @FXML
    private TreeView<String> diseaseTreeView;

    @FXML
    private TextArea infoArea;
    private Map<String, Disease> diseaseDatabase = new HashMap<>();

    @FXML
    private Pane graphPane;


    @FXML
    public void initialize() {

        statusLabel.setText("Status : Ready");

        loadDiseases();

        initializeTree();

        initializeTreeEvents();

    }



    private void loadDiseases() {

        // ---------------- Cancer ----------------

        diseaseDatabase.put(
                "Breast Cancer",
                new Disease(
                        "Breast Cancer",
                        "Cancer",
                        List.of("BRCA1", "BRCA2", "TP53"),
                        List.of("Tamoxifen", "Trastuzumab"),
                        "Breast cancer begins when abnormal cells in the breast grow uncontrollably."
                )
        );

        diseaseDatabase.put(
                "Lung Cancer",
                new Disease(
                        "Lung Cancer",
                        "Cancer",
                        List.of("EGFR", "ALK", "KRAS"),
                        List.of("Osimertinib", "Gefitinib"),
                        "Lung cancer is one of the leading causes of cancer-related deaths."
                )
        );

        diseaseDatabase.put(
                "Colon Cancer",
                new Disease(
                        "Colon Cancer",
                        "Cancer",
                        List.of("APC", "KRAS", "TP53"),
                        List.of("Capecitabine", "Oxaliplatin"),
                        "Colon cancer develops in the large intestine."
                )
        );

        // ---------------- Viral ----------------

        diseaseDatabase.put(
                "COVID-19",
                new Disease(
                        "COVID-19",
                        "Viral",
                        List.of("ACE2", "TMPRSS2"),
                        List.of("Remdesivir", "Paxlovid"),
                        "COVID-19 is caused by the SARS-CoV-2 virus."
                )
        );

        diseaseDatabase.put(
                "Dengue",
                new Disease(
                        "Dengue",
                        "Viral",
                        List.of("IFITM3", "TNF"),
                        List.of("Supportive Therapy"),
                        "Dengue is a mosquito-borne viral disease."
                )
        );

        diseaseDatabase.put(
                "Influenza",
                new Disease(
                        "Influenza",
                        "Viral",
                        List.of("IFITM3"),
                        List.of("Oseltamivir", "Zanamivir"),
                        "Influenza is a contagious respiratory illness."
                )
        );

        // ---------------- Bacterial ----------------

        diseaseDatabase.put(
                "Tuberculosis",
                new Disease(
                        "Tuberculosis",
                        "Bacterial",
                        List.of("SLC11A1"),
                        List.of("Isoniazid", "Rifampicin"),
                        "Tuberculosis is caused by Mycobacterium tuberculosis."
                )
        );

        diseaseDatabase.put(
                "Cholera",
                new Disease(
                        "Cholera",
                        "Bacterial",
                        List.of("CFTR"),
                        List.of("Azithromycin", "Doxycycline"),
                        "Cholera causes severe watery diarrhea."
                )
        );

        // ---------------- Genetic ----------------

        diseaseDatabase.put(
                "Thalassemia",
                new Disease(
                        "Thalassemia",
                        "Genetic",
                        List.of("HBB"),
                        List.of("Iron Chelators"),
                        "Thalassemia is an inherited blood disorder."
                )
        );

        diseaseDatabase.put(
                "Cystic Fibrosis",
                new Disease(
                        "Cystic Fibrosis",
                        "Genetic",
                        List.of("CFTR"),
                        List.of("Ivacaftor", "Lumacaftor"),
                        "Cystic fibrosis affects the lungs and digestive system."
                )
        );

    }



    // ==========================
    // Build TreeView
    // ==========================

    private void initializeTree() {

        DiseaseTree tree = new DiseaseTree();

        TreeItem<String> rootItem = createTreeItem(tree.getRoot());

        diseaseTreeView.setRoot(rootItem);

        diseaseTreeView.setShowRoot(true);

    }


    // ==========================
    // Tree Click Event
    // ==========================

    private void initializeTreeEvents() {

        diseaseTreeView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    if (newValue == null)
                        return;

                    Disease disease =
                            diseaseDatabase.get(newValue.getValue());

                    if (disease != null) {

                        showDiseaseInformation(disease);
                    }

                    GraphDrawer drawer = new GraphDrawer();
                    drawer.drawDiseaseGraph(graphPane, disease);

                });
    }


    // ==========================
    // Display Information
    // ==========================

    private void showDiseaseInformation(Disease disease) {

        String information = "";

        information += "Disease : " + disease.getName();

        information += "\n\nCategory : " + disease.getCategory();

        information += "\n\nGenes :\n";

        for (String gene : disease.getGenes()) {

            information += "• " + gene + "\n";

        }

        information += "\nDrugs :\n";

        for (String drug : disease.getDrugs()) {

            information += "• " + drug + "\n";

        }

        information += "\nDescription :\n";

        information += disease.getDescription();

        infoArea.setText(information);

    }



    private TreeItem<String> createTreeItem(DiseaseNode node) {

        TreeItem<String> item = new TreeItem<>(node.getName());

        for (DiseaseNode child : node.getChildren()) {

            item.getChildren().add(createTreeItem(child));

        }

        return item;

    }



}