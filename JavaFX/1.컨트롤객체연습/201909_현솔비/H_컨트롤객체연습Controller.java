package kr.or.ddit.basic;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;

public class H_컨트롤객체연습Controller implements Initializable {

	@FXML
	private Button btn;
	@FXML
	private TextField txtField;
	@FXML 
	private TextArea txtResult;
	
	@FXML 
	private RadioButton gender1;
	@FXML 
	private RadioButton gender2;
	@FXML 
	private ToggleGroup group;
	
	@FXML 
	private CheckBox h1;
	@FXML 
	private CheckBox h2;
	@FXML 
	private CheckBox h3;
	@FXML 
	private CheckBox h4;
	@FXML 
	private CheckBox h5;
	@FXML 
	private CheckBox h6;
	@FXML 
	private CheckBox h7;
	@FXML 
	private CheckBox h8;
	
	private String name;
	private String gender;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	// 확인 버튼이 클릭되었을때 처리하는 메서드
	@FXML
	void btnClick(ActionEvent e) {
		
		String hobby ="";
		
		name = txtField.getText();
		//txtField.setText("");
		
		if(gender1.isSelected()) {
			gender = "남자";
		}else {
			gender = "여자";
		}
		
		boolean chk = false;
		
		if(h1.isSelected()) {
			hobby += ", 여행";
			chk = true;
		}
		if(h2.isSelected()){
			hobby += ", 등산";
			chk = true;
		}
		if(h3.isSelected()){
			hobby += ", 독서";
			chk = true;
		}
		if(h4.isSelected()){
			hobby += ", 바둑";
			chk = true;
		}
		if(h5.isSelected()){
			hobby += ", 장기";
			chk = true;
		}
		if(h6.isSelected()){
			hobby += ", 게임";
			chk = true;
		}
		if(h7.isSelected()){
			hobby += ", 테니스";
			chk = true;
		}
		if(h8.isSelected()){
			hobby += ", 배드민턴";
			chk = true;
		}
		
		if(chk==false) {
			hobby = "선택하지 않았습니다.";
		}else {
			hobby = hobby.substring(1, hobby.length()) + " 입니다.";
		}
		
		txtResult.setText("이름은 "+name+"이고, 성별은 "+gender+"입니다.\n취미는 "+hobby);
	}
}
