package com.example.bionet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("Main.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(
                HelloApplication.class.getResource("style.css").toExternalForm());

        stage.setTitle("BioNet Explorer");

        stage.getIcons().add(
                new Image(HelloApplication.class.getResourceAsStream("/logo_f.png"))
        );

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}