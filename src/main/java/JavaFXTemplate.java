import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;

import javax.swing.*;



public class JavaFXTemplate extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Read file fxml and draw interface.
			Parent root = FXMLLoader.load(getClass()
					.getResource("/FXML/Myfxml.fxml"));

			primaryStage.setTitle("My Application");
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


//public class JavaFXTemplate extends Application {
//
//	Player playerOne;
//	Player playerTwo;
//	Dealer theDealer;
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		launch(args);
//	}
//
//	//feel free to remove the starter code from this method
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		primaryStage.setTitle("Welcome to 3 Card Poker");
//
////		 Rectangle rect = new Rectangle (100, 40, 100, 100);
////
////	     rect.setArcHeight(50);
////	     rect.setArcWidth(50);
////	     rect.setFill(Color.VIOLET);
////
////	     RotateTransition rt = new RotateTransition(Duration.millis(5000), rect);
////	     rt.setByAngle(270);
////	     rt.setCycleCount(4);
////	     rt.setAutoReverse(true);
////	     SequentialTransition seqTransition = new SequentialTransition (
////	         new PauseTransition(Duration.millis(500)),
////	         rt
////	     );
////	     seqTransition.play();
////
////	     FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
////	     ft.setFromValue(1.0);
////	     ft.setToValue(0.3);
////	     ft.setCycleCount(4);
////	     ft.setAutoReverse(true);
////
////	     ft.play();
////	     BorderPane root = new BorderPane();
////	     root.setCenter(rect);
//
//		BorderPane root = new BorderPane();
//		root.setStyle("-fx-background-color: #006f0a;");
//
//
//		Label welcomeMessage = new Label ("Welcome to 3 Card Poker");
//
//		welcomeMessage.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 50px; -fx-label-padding: 50px;");
//
//		HBox topMenu = new HBox(welcomeMessage);
//		topMenu.setAlignment(Pos.TOP_CENTER);
//
//		root.setTop(topMenu);
//
//
//		VBox startMenu = getvBox(primaryStage); // calls method to keep main clean
//
//
//
//		root.setCenter(startMenu);
//
//
//	     Scene scene = new Scene(root, 1920,1080);
//			primaryStage.setScene(scene);
//			primaryStage.show();
//
//
//
//	}
//
//	private VBox getvBox(Stage primaryStage) {
//		Button Play = new Button("Play");
//		Play.setStyle("-fx-background-color: #00cb12; -fx-border-color: #000000; -fx-border-width: 2px; -fx-spacing: 5px; -fx-padding: 40px; -fx-font-size: 30px");
//		Play.setMinSize(100, 50);
//
//
//		Button Exit = new Button("Exit");
//		Exit.setStyle("-fx-background-color: #00cb12; -fx-border-color: #fe5656; -fx-border-width: 2px; -fx-spacing: 5px; -fx-padding: 40px; -fx-font-size: 30px ");
//		Exit.setMinSize(100, 50);
//		Exit.setOnAction(e -> primaryStage.close());
//
//
//		VBox startMenu = new VBox(10, Play, Exit);
//		startMenu.setAlignment(Pos.CENTER);
//		return startMenu;
//	}
//
//}
