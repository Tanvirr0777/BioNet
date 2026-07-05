package com.example.bionet.tree;

public class DiseaseTree {

    private DiseaseNode root;

    public DiseaseTree() {
        buildTree();
    }

    private void buildTree() {

        root = new DiseaseNode("Disease");

        DiseaseNode cancer = new DiseaseNode("Cancer");
        DiseaseNode viral = new DiseaseNode("Viral");
        DiseaseNode bacterial = new DiseaseNode("Bacterial");
        DiseaseNode genetic = new DiseaseNode("Genetic");

        root.addChild(cancer);
        root.addChild(viral);
        root.addChild(bacterial);
        root.addChild(genetic);

        cancer.addChild(new DiseaseNode("Breast Cancer"));
        cancer.addChild(new DiseaseNode("Lung Cancer"));
        cancer.addChild(new DiseaseNode("Colon Cancer"));

        viral.addChild(new DiseaseNode("COVID-19"));
        viral.addChild(new DiseaseNode("Dengue"));
        viral.addChild(new DiseaseNode("Influenza"));

        bacterial.addChild(new DiseaseNode("Tuberculosis"));
        bacterial.addChild(new DiseaseNode("Cholera"));

        genetic.addChild(new DiseaseNode("Thalassemia"));
        genetic.addChild(new DiseaseNode("Cystic Fibrosis"));
    }

    public DiseaseNode getRoot() {
        return root;
    }

}