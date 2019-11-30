package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CCPMain extends Application {

		@Override
		public void start(Stage primaryStage) throws Exception {
			
			Pane root = FXMLLoader.load(getClass().getResource("CCPLayout.fxml"));
		
			Scene scene = new Scene(root);
			primaryStage.setTitle("컨트롤객체연습");
			primaryStage.setScene(scene);
			primaryStage.show();
		}

		public static void main(String[] args) {
			launch(args);
		}
}
