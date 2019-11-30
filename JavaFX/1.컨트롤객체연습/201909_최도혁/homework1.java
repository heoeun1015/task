package homework1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class homework1 extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox root = FXMLLoader.load(getClass().getResource("ReportFx_1.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("과제1.컨트롤 객체 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
