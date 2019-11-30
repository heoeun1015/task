package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test_1 extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
FXMLLoader loader = new FXMLLoader(getClass().getResource("test_1.fxml"));
		
		Parent root = loader.load();		// 인스턴스 객체에 대한 노드 메서드
		// 스태틱으로 하냐, 인스턴스로 하냐의 차이
		
//		loader.getController();	// 나중에 배우게 될 거
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("숙제");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
