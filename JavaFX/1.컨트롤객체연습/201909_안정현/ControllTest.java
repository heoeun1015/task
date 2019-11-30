package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setSpacing(10);

		TextArea textArea = new TextArea();

		HBox hbox1 = new HBox();

		Label nameLabel = new Label("이름 : ");

		TextField nametext = new TextField(); 

		hbox1.getChildren().addAll(nameLabel, nametext);

		// -----------------------------------------------------------------------

		HBox hbox2 = new HBox();

		Label gender = new Label("성별 : ");

		ToggleGroup group = new ToggleGroup();

		RadioButton radbtn1 = new RadioButton("남");
		radbtn1.setUserData("남성");

		RadioButton radbtn2 = new RadioButton("여");
		radbtn2.setUserData("여성");

		HBox hboxT = new HBox();
		hboxT.getChildren().addAll(radbtn1, radbtn2);
		hboxT.setSpacing(10);

		// -----------------------------------------------------------------------

		hbox2.getChildren().addAll(gender, hboxT);

		HBox hbox3 = new HBox();

		Label hobby = new Label("취미 : ");

		String[] chkhobby = new String[] { "여행", "등산", "독서", "바둑", "장기", "게임", "테니스", "배드민턴" };

		CheckBox[] chkbox = new CheckBox[chkhobby.length];

		for (int i = 0; i < chkbox.length; i++) {
			chkbox[i] = new CheckBox(chkhobby[i]);
		}

		HBox hchk = new HBox();
		hchk.getChildren().addAll(chkbox);
		hchk.setSpacing(10);

		hbox3.getChildren().addAll(hobby, hchk);

		Button btn = new Button("보 기");

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String str = "";
				if(nametext != null) {
					str += nametext.getText() + "님은 ";
				}
				
				if(radbtn1.isSelected() && !radbtn2.isSelected()) {
					str += radbtn1.getUserData() + "이고 ";
				}else if(radbtn2.isSelected() && !radbtn1.isSelected()) {
					str += radbtn2.getUserData() + "이고 ";
				}else {
					str += "성별판별불가이고 ";
				}
				
				for(int i = 0; i < chkbox.length; i++) {
					if(chkbox[i].isSelected()) {
						str += chkbox[i].getText() + ", ";
					}
				}
				
				str = str.substring(0, str.length()-2);
				
				str += "(이)가 취미입니다.";
				
				textArea.setText(str);
				
				
			}
		});

		// -----------------------------------------------------------------------

		root.getChildren().addAll(hbox1, hbox2, hbox3, btn, textArea);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Fx test");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
