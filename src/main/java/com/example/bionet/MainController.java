package com.example.bionet;
import com.example.bionet.dialog.AddDiseaseController;
import com.example.bionet.model.Disease;

import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import com.example.bionet.visualization.GraphDrawer;
import com.example.bionet.model.Gene;
import com.example.bionet.model.Drug;

import com.example.bionet.builder.GraphBuilder;
import com.example.bionet.graph.Graph;

import com.example.bionet.algorithm.BFS;
import com.example.bionet.graph.GraphNode;
import com.example.bionet.algorithm.DFS;

import javafx.scene.control.TextField;
import com.example.bionet.model.Gene;
import com.example.bionet.model.Drug;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.example.bionet.dialog.EditDiseaseController;

import java.util.List;


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

    private Disease currentDisease;

    private Graph currentGraph;

    @FXML
    private TextField searchField;

    private GraphDrawer graphDrawer = new GraphDrawer();


    @FXML
    public void initialize() {



        loadDiseases();

        loadDiseaseTree();

        initializeTreeEvents();

    }



    private void loadDiseases() {

        // ---------------- Cancer ----------------

        diseaseDatabase.put(

                "Breast Cancer",

                new Disease(

                        "Breast Cancer",

                        "Cancer",

                        List.of(

                                new Gene(
                                        "BRCA1",
                                        "Breast Cancer Type 1",
                                        "17",
                                        "DNA repair gene"
                                ),

                                new Gene(
                                        "BRCA2",
                                        "Breast Cancer Type 2",
                                        "13",
                                        "DNA repair gene"
                                ),

                                new Gene(
                                        "TP53",
                                        "Tumor Protein p53",
                                        "17",
                                        "Tumor suppressor gene"
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Tamoxifen",
                                        "Hormone Therapy",
                                        "Blocks estrogen receptor",
                                        "Used for hormone-positive breast cancer."
                                ),

                                new Drug(
                                        "Trastuzumab",
                                        "Monoclonal Antibody",
                                        "Targets HER2 receptor",
                                        "Used for HER2-positive breast cancer."
                                )

                        ),

                        "Breast cancer begins when abnormal breast cells grow uncontrollably."

                )

        );

        diseaseDatabase.put(

                "Lung Cancer",

                new Disease(

                        "Lung Cancer",

                        "Cancer",

                        List.of(

                                new Gene(
                                        "EGFR",
                                        "Epidermal Growth Factor Receptor",
                                        "7",
                                        "Controls cell growth and division."
                                ),

                                new Gene(
                                        "ALK",
                                        "Anaplastic Lymphoma Kinase",
                                        "2",
                                        "Receptor tyrosine kinase involved in cancer."
                                ),

                                new Gene(
                                        "KRAS",
                                        "Kirsten Rat Sarcoma Virus",
                                        "12",
                                        "Regulates cell signaling and growth."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Osimertinib",
                                        "Targeted Therapy",
                                        "EGFR inhibitor",
                                        "Used for EGFR-mutated lung cancer."
                                ),

                                new Drug(
                                        "Gefitinib",
                                        "Targeted Therapy",
                                        "EGFR inhibitor",
                                        "Treats non-small cell lung cancer."
                                )

                        ),

                        "Lung cancer develops in the tissues of the lungs."

                )

        );

        diseaseDatabase.put(

                "Colon Cancer",

                new Disease(

                        "Colon Cancer",

                        "Cancer",

                        List.of(

                                new Gene(
                                        "APC",
                                        "Adenomatous Polyposis Coli",
                                        "5",
                                        "Tumor suppressor gene."
                                ),

                                new Gene(
                                        "KRAS",
                                        "Kirsten Rat Sarcoma Virus",
                                        "12",
                                        "Frequently mutated in colon cancer."
                                ),

                                new Gene(
                                        "TP53",
                                        "Tumor Protein p53",
                                        "17",
                                        "Controls cell cycle."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Capecitabine",
                                        "Chemotherapy",
                                        "Antimetabolite",
                                        "Used to treat colorectal cancer."
                                ),

                                new Drug(
                                        "Oxaliplatin",
                                        "Chemotherapy",
                                        "DNA cross-linking agent",
                                        "Used with other drugs for colon cancer."
                                )

                        ),

                        "Colon cancer begins in the large intestine."

                )

        );

        // ---------------- Viral ----------------

        diseaseDatabase.put(

                "COVID-19",

                new Disease(

                        "COVID-19",

                        "Viral",

                        List.of(

                                new Gene(
                                        "ACE2",
                                        "Angiotensin-Converting Enzyme 2",
                                        "X",
                                        "Receptor used by SARS-CoV-2."
                                ),

                                new Gene(
                                        "TMPRSS2",
                                        "Transmembrane Protease Serine 2",
                                        "21",
                                        "Helps viral entry into host cells."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Remdesivir",
                                        "Antiviral",
                                        "RNA polymerase inhibitor",
                                        "Used for hospitalized COVID-19 patients."
                                ),

                                new Drug(
                                        "Paxlovid",
                                        "Antiviral",
                                        "Protease inhibitor",
                                        "Used for early COVID-19 treatment."
                                )

                        ),

                        "COVID-19 is caused by the SARS-CoV-2 virus."

                )

        );

        diseaseDatabase.put(

                "Dengue",

                new Disease(

                        "Dengue",

                        "Viral",

                        List.of(

                                new Gene(
                                        "IFITM3",
                                        "Interferon Induced Transmembrane Protein 3",
                                        "11",
                                        "Helps defend against viral infection."
                                ),

                                new Gene(
                                        "TNF",
                                        "Tumor Necrosis Factor",
                                        "6",
                                        "Inflammatory cytokine."
                                )

                        ),

                        List.of(),

                        "Dengue is a mosquito-borne viral disease."

                )

        );

        diseaseDatabase.put(

                "Influenza",

                new Disease(

                        "Influenza",

                        "Viral",

                        List.of(

                                new Gene(
                                        "IFITM3",
                                        "Interferon Induced Transmembrane Protein 3",
                                        "11",
                                        "Provides antiviral defense."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Oseltamivir",
                                        "Antiviral",
                                        "Neuraminidase inhibitor",
                                        "Used to treat influenza."
                                ),

                                new Drug(
                                        "Zanamivir",
                                        "Antiviral",
                                        "Neuraminidase inhibitor",
                                        "Treats influenza infection."
                                )

                        ),

                        "Influenza is a contagious respiratory viral disease."

                )

        );
        // ---------------- Bacterial ----------------

        diseaseDatabase.put(

                "Tuberculosis",

                new Disease(

                        "Tuberculosis",

                        "Bacterial",

                        List.of(

                                new Gene(
                                        "SLC11A1",
                                        "Solute Carrier Family 11 Member 1",
                                        "2",
                                        "Associated with immune response."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Isoniazid",
                                        "Antibiotic",
                                        "Inhibits mycolic acid synthesis",
                                        "First-line anti-tuberculosis drug."
                                ),

                                new Drug(
                                        "Rifampicin",
                                        "Antibiotic",
                                        "RNA polymerase inhibitor",
                                        "Essential drug for TB treatment."
                                )

                        ),

                        "Tuberculosis is caused by Mycobacterium tuberculosis."

                )

        );

        diseaseDatabase.put(

                "Cholera",

                new Disease(

                        "Cholera",

                        "Bacterial",

                        List.of(

                                new Gene(
                                        "CFTR",
                                        "Cystic Fibrosis Transmembrane Conductance Regulator",
                                        "7",
                                        "Plays a role in chloride transport."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Azithromycin",
                                        "Antibiotic",
                                        "Protein synthesis inhibitor",
                                        "Treats severe cholera."
                                ),

                                new Drug(
                                        "Doxycycline",
                                        "Antibiotic",
                                        "Protein synthesis inhibitor",
                                        "Alternative treatment for cholera."
                                )

                        ),

                        "Cholera is an acute diarrheal disease."

                )

        );

        // ---------------- Genetic ----------------

        diseaseDatabase.put(

                "Thalassemia",

                new Disease(

                        "Thalassemia",

                        "Genetic",

                        List.of(

                                new Gene(
                                        "HBB",
                                        "Hemoglobin Subunit Beta",
                                        "11",
                                        "Responsible for beta-globin production."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Deferasirox",
                                        "Iron Chelator",
                                        "Removes excess iron",
                                        "Used in transfusion-dependent thalassemia."
                                )

                        ),

                        "Thalassemia is an inherited blood disorder."

                )

        );

        diseaseDatabase.put(

                "Cystic Fibrosis",

                new Disease(

                        "Cystic Fibrosis",

                        "Genetic",

                        List.of(

                                new Gene(
                                        "CFTR",
                                        "Cystic Fibrosis Transmembrane Conductance Regulator",
                                        "7",
                                        "Controls salt and water transport."
                                )

                        ),

                        List.of(

                                new Drug(
                                        "Ivacaftor",
                                        "CFTR Modulator",
                                        "Improves CFTR protein function",
                                        "Used for cystic fibrosis."
                                ),

                                new Drug(
                                        "Lumacaftor",
                                        "CFTR Modulator",
                                        "Corrects defective CFTR protein",
                                        "Combined with Ivacaftor for treatment."
                                )

                        ),

                        "Cystic fibrosis is an inherited disorder affecting the lungs and digestive system."

                )

        );

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

                    currentDisease = disease;

                    GraphBuilder builder = new GraphBuilder();

                    currentGraph = builder.buildGraph(disease);

                    showDiseaseInformation(disease);

                    graphDrawer.drawDiseaseGraph(
                            graphPane,
                            disease,
                            infoArea,
                            disease.getName()
                    );

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

        for (Gene gene : disease.getGenes()) {

            information += "• " + gene.getSymbol() + "\n";

        }

        information += "\nDrugs :\n";

        if (disease.getDrugs().isEmpty()) {

            information += "No approved drug discovered yet.\n";

        } else {

            for (Drug drug : disease.getDrugs()) {

                information += "• " + drug.getName() + "\n";

            }

        }

        information += "\nDescription :\n";

        information += disease.getDescription();

        infoArea.setText(information);

    }


    private void loadDiseaseTree() {


        TreeItem<String> root = new TreeItem<>("Diseases");

        Map<String, TreeItem<String>> categoryMap = new HashMap<>();

        for (Disease disease : diseaseDatabase.values()) {

            TreeItem<String> categoryNode =
                    categoryMap.get(disease.getCategory());

            if (categoryNode == null) {

                categoryNode = new TreeItem<>(disease.getCategory());

                categoryMap.put(disease.getCategory(), categoryNode);

                root.getChildren().add(categoryNode);
            }

            categoryNode.getChildren().add(
                    new TreeItem<>(disease.getName())
            );
        }

        root.setExpanded(true);

        diseaseTreeView.setRoot(root);
    }

    @FXML
    private void handleBFS() {

        if (currentGraph == null || currentDisease == null) {

            infoArea.setText("Please select a disease first.");

            return;

        }

        GraphNode startNode =
                currentGraph.findNode(currentDisease.getName());

        if (startNode == null) {

            infoArea.setText("Start node not found.");

            return;

        }

        BFS bfs = new BFS();

        List<GraphNode> traversal =
                bfs.traverse(currentGraph, startNode);

        StringBuilder result = new StringBuilder();

        result.append("            BFS Traversal \n\n");

        int index = 1;

        for (GraphNode node : traversal) {

            result.append(index++)
                    .append(". ")
                    .append(node.getName())
                    .append(" (")
                    .append(node.getType())
                    .append(")\n");

        }

        infoArea.setText(result.toString());

        animateTraversal(traversal);

    }

    @FXML
    private void handleDFS() {

        if (currentGraph == null || currentDisease == null) {

            infoArea.setText("Please select a disease first.");

            return;

        }

        GraphNode startNode =
                currentGraph.findNode(currentDisease.getName());

        DFS dfs = new DFS();

        List<GraphNode> traversal =
                dfs.traverse(currentGraph, startNode);

        StringBuilder result = new StringBuilder();

        result.append("              DFS Traversal ===========\n\n");

        int index = 1;

        for (GraphNode node : traversal) {

            result.append(index++)
                    .append(". ")
                    .append(node.getName())
                    .append(" (")
                    .append(node.getType())
                    .append(")\n");

        }

        infoArea.setText(result.toString());
        animateTraversal(traversal);

    }

    @FXML
    private void handleSearch() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {

            infoArea.setText("Please enter a keyword.");

            return;

        }

        // -------- Search Disease --------

        Disease disease = diseaseDatabase.get(keyword);

        if (disease != null) {

            currentDisease = disease;

            GraphBuilder builder = new GraphBuilder();

            currentGraph = builder.buildGraph(disease);

            showDiseaseInformation(disease);

            graphDrawer.drawDiseaseGraph(
                    graphPane,
                    disease,
                    infoArea,
                    disease.getName()
            );
            return;

        }

        // -------- Search Gene --------

        for (Disease d : diseaseDatabase.values()) {

            for (Gene gene : d.getGenes()) {

                if (gene.getSymbol().equalsIgnoreCase(keyword)
                        || gene.getFullName().equalsIgnoreCase(keyword)) {

                    currentDisease = d;

                    GraphBuilder builder = new GraphBuilder();

                    currentGraph = builder.buildGraph(d);

                    graphDrawer.drawDiseaseGraph(
                            graphPane,
                            d,
                            infoArea,
                            gene.getSymbol()
                    );

                    infoArea.setText(

                            "Gene Symbol : " + gene.getSymbol()

                                    + "\n\nFull Name : "

                                    + gene.getFullName()

                                    + "\n\nChromosome : "

                                    + gene.getChromosome()

                                    + "\n\nDescription :\n"

                                    + gene.getDescription()

                    );

                    return;

                }

            }

        }
        // -------- Search Drug --------

        for (Disease d : diseaseDatabase.values()) {

            for (Drug drug : d.getDrugs()) {

                if (drug.getName().equalsIgnoreCase(keyword)) {

                    currentDisease = d;

                    GraphBuilder builder = new GraphBuilder();

                    currentGraph = builder.buildGraph(d);


                    graphDrawer.drawDiseaseGraph(
                            graphPane,
                            d,
                            infoArea,
                            drug.getName()
                    );

                    infoArea.setText(

                            "Drug : " + drug.getName()

                                    + "\n\nClass : "

                                    + drug.getDrugClass()

                                    + "\n\nMechanism : "

                                    + drug.getMechanism()

                                    + "\n\nDescription :\n"

                                    + drug.getDescription()

                    );

                    return;

                }

            }

        }

        infoArea.setText("No result found.");

    }


    private void animateTraversal(List<GraphNode> traversal) {

        graphDrawer.resetColors();

        Timeline timeline = new Timeline();

        for (int i = 0; i < traversal.size(); i++) {

            GraphNode node = traversal.get(i);

            KeyFrame keyFrame = new KeyFrame(

                    Duration.seconds(i),

                    e -> graphDrawer.highlightNode(node.getName())

            );

            timeline.getKeyFrames().add(keyFrame);

        }

        timeline.play();

    }


    @FXML
    private void handleAddDisease() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/bionet/AddDisease.fxml")
            );

            Parent root = loader.load();

            AddDiseaseController controller = loader.getController();

            Stage stage = new Stage();

            stage.setTitle("Add Disease");

            stage.getIcons().add(
                    new Image(HelloApplication.class.getResourceAsStream("/logo_f.png"))
            );

            stage.setScene(new Scene(root));

            stage.showAndWait();

            Disease disease = controller.getCreatedDisease();

            if (disease != null) {

                addDiseaseToSystem(disease);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void addDiseaseToSystem(Disease disease) {

        diseaseDatabase.put(
                disease.getName(),
                disease
        );

        loadDiseaseTree();

    }

    private Runnable refreshCallback;

    public void setRefreshCallback(Runnable refreshCallback) {

        this.refreshCallback = refreshCallback;

    }

    @FXML
    private void handleEditDisease() throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("EditDisease.fxml")
        );

        Scene scene = new Scene(loader.load());

        EditDiseaseController controller =
                loader.getController();

        controller.setDiseaseDatabase(diseaseDatabase);

        controller.setRefreshCallback(this::loadDiseaseTree);

        Stage stage = new Stage();

        stage.setTitle("Edit Disease");

        stage.getIcons().add(
                new Image(HelloApplication.class.getResourceAsStream("/logo_f.png"))
        );

        stage.setScene(scene);

        stage.show();

    }



}