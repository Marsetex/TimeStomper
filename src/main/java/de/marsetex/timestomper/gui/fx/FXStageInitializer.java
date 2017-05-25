package de.marsetex.timestomper.gui.fx;

import java.io.IOException;

import de.marsetex.timestomper.gui.UIInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Marcel Gruessinger
 *
 */
public class FXStageInitializer extends Application implements UIInitializer {

	@Override
	public void initUserInterface(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		Pane root = loadFXML();
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("TimeStomper");
		stage.show();
	}

	private Pane loadFXML() throws IOException {
		return (Pane) FXMLLoader.load(getClass().getResource("/FXMainStage.fxml"));
	}

}
