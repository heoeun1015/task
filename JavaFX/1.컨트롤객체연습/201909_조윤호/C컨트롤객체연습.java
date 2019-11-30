package kr.or.ddit.basic;

import javafx.application.Application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class C컨트롤객체연습 extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox root = FXMLLoader.load(getClass().getResource("C컨트롤객체연습.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("컨트롤객체연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
