
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.swing.*;



public class JavaFXTemplate extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Read file fxml and draw interface.
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/WelcomePage.fxml"));

			primaryStage.setTitle("Vandan's Poker Game");
			Scene s1 = new Scene(root, 900,850);
			s1.getStylesheets().add("/styles/style1.css");
			primaryStage.setScene(s1);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}



	public static void main(String[] args) {
		launch(args);
	}
}



