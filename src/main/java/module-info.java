module com.example.bionet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bionet to javafx.fxml;
    exports com.example.bionet;
}