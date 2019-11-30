package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = 
				FXMLLoader.load(getClass().getResource("Fxml_radio1.fxml"));
	
	Scene scene = new Scene(root);
	primaryStage.setTitle("컨트롤객체연습");
	primaryStage.setScene(scene);
	primaryStage.show();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
