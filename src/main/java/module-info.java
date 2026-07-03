module com.example.bionet {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.bionet to javafx.fxml;
    opens com.example.bionet.dialog to javafx.fxml;

    exports com.example.bionet;
    exports com.example.bionet.dialog;

}