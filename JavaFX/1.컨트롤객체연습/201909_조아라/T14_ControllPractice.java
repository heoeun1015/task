package kr.or.ddit.basic;


import java.util.ArrayList;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T14_ControllPractice extends Application {
	String gender;
	String hobby;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 전체 박스
		VBox root = new VBox();
		root.setPrefWidth(650);
		root.setPrefHeight(500);
		root.setAlignment(Pos.CENTER_LEFT);
		root.setPadding(new Insets(10, 30, 10, 30));
		root.setSpacing(20);
		
		// 첫번쨰줄
		HBox hbox1 = new HBox();
		hbox1.setSpacing(20);
		
		// 이름
		Label label1 = new Label();
		label1.setText("이름 : ");
		
		// 입력창
		TextField txtField = new TextField();
		txtField.setPrefWidth(200);
		
		hbox1.getChildren().add(label1);
		hbox1.getChildren().add(txtField);
		
		// 두번째 줄
		HBox hbox2 = new HBox();
		hbox2.setSpacing(20);
		
		Label label2 = new Label();
		label2.setText("성별 : ");
		
		// 라디오 버튼
		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("남");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("여");
		rb2.setToggleGroup(group);
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
            	RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                gender = chk.getText();
            }
        });
		
		
		hbox2.getChildren().addAll(label2, rb1, rb2);
		
		// 세번째 줄
		HBox hbox3 = new HBox();
		hbox3.setSpacing(10);
		
		Label label3 = new Label();
		Label label4 = new Label();
		label3.setText("취미 : ");
		
		String[] names = new String[] {"여행", "등산", "독서", "바둑", "장기", "게임", "테니스", "배드민턴"};
		CheckBox[] chkboxs = new CheckBox[names.length];

		
		for(int i = 0; i < names.length; i++) {
			final CheckBox cb = chkboxs[i] = new CheckBox(names[i]);
			
			cb.selectedProperty().addListener(new ChangeListener() {

				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue) {
					if(newValue != null) { // 체크를 하면
						hobby += cb.getText() + ",";
					} 
				}
				
			});

		}
		
		hbox3.getChildren().addAll(label3);
		for(CheckBox cb : chkboxs) {
			hbox3.getChildren().add(cb);
		}
		
		// 네번째 줄
		Button button = new Button("보기");
		
		// 다섯번째 줄
		TextArea textArea = new TextArea();
		textArea.setPrefWidth(100);
		textArea.setPrefHeight(200);
		
		button.setOnAction(e->{
			textArea.setText(txtField.getText() + "은/는 " + gender + "자이고 취미는 " + hobby.substring(4, hobby.length()-1) + "입니다.");
		});
		// 전체 vbox에 컨트롤 추가하기+
		root.getChildren().addAll(hbox1, hbox2, hbox3, button, textArea);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("자기소개");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
