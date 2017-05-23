package de.grue038.timestomper.classes;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Marsetex
 *
 */
public class ProgramStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("/gui.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("A SceneBuilder Example");
        stage.show();
    }
}
