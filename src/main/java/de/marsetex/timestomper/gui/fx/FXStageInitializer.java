package de.marsetex.timestomper.gui.fx;

import java.io.IOException;

import de.marsetex.timestomper.gui.UIInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		Parent root = loadFXML();
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("TimeStomper");
		stage.show();
	}

	private Parent loadFXML() throws IOException {
		return (Parent) FXMLLoader.load(getClass().getResource("/FXMainStage.fxml"));
	}

}
