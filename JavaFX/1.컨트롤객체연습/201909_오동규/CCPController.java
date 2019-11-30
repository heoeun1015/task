package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CCPController implements Initializable{
	@FXML ToggleGroup gender;
	@FXML TextArea txtArea;
	@FXML TextField txtInput;
	@FXML RadioButton male;
	@FXML RadioButton female;
	@FXML CheckBox hb1;
	@FXML CheckBox hb2;
	@FXML CheckBox hb3;
	@FXML CheckBox hb4;
	@FXML CheckBox hb5;
	@FXML CheckBox hb6;
	@FXML CheckBox hb7;
	@FXML CheckBox hb8;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	@FXML
	void btnClick(ActionEvent e) {
		String name = txtInput.getText();
		
		String gend = "";
		if(male.isSelected()) {
			gend = male.getText();
		} else {
			gend = female.getText();
		}
		
		boolean ischecked = false;
		String hobby = "";
		if(hb1.isSelected()) {
			hobby += hb1.getText() + ",";
			ischecked = true;
		}
		if(hb2.isSelected()) {
			hobby += hb2.getText() + ",";
			ischecked = true;
		}
		if(hb3.isSelected()) {
			hobby += hb3.getText() + ","; 
			ischecked = true;
		}
		if(hb4.isSelected()) {
			hobby += hb4.getText() + ",";
			ischecked = true;
		}
		if(hb5.isSelected()) {
			hobby += hb5.getText() + ",";
			ischecked = true;
		}
		if(hb6.isSelected()) {
			hobby += hb6.getText() + ",";
			ischecked = true;
		}
		if(hb7.isSelected()) {
			hobby += hb7.getText() + ",";
			ischecked = true;
		}
		if(hb8.isSelected()) {
			hobby += hb8.getText() + ",";
			ischecked = true;
		}
		
		if(!ischecked) {
			hobby = "없습니다.";
		} else {
			hobby = hobby.substring(0, hobby.length() - 1) + " 입니다.";
		}
		txtArea.setText("이름은 " + name + "이고,\n성별은 " + gend +"자 이며,\n취미는 " + hobby);
	}

}
