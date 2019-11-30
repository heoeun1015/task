package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;

public class CheckController implements Initializable{


	@FXML
	private TextArea txtResult;
	@FXML
	private TextArea name;
	@FXML
	private ToggleGroup group;
	@FXML
	private RadioButton male, female;
	@FXML 
	private CheckBox chk1, chk2,chk3,chk4,chk5,chk6,chk7,chk8;
	@FXML 
	private Button btn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//실행되면 제일 먼저 호출됨
		
	}

	//버튼이 클릭됐을 때 실행
	@FXML
	void btnClick(ActionEvent e) {
		String rst = "";
		rst += name.getText() + "님은";
		
		if(male.isSelected()) {
			rst+=" 남자이고 ";
		}
		if(female.isSelected()) {
			rst+=" 여자이고 ";
		}
		
		rst+="취미는 ";
		
		if(chk1.isSelected()) {
			rst+= chk1.getText() + ",";
		}
		if(chk2.isSelected()) {
			rst+= chk2.getText() + ",";		
		}
		if(chk3.isSelected()) {
			rst+= chk3.getText() + ",";
		}
		if(chk4.isSelected()) {
			rst+= chk4.getText() + ",";
		}
		if(chk5.isSelected()) {
			rst+= chk5.getText() + ",";
		}
		if(chk6.isSelected()) {
			rst+= chk6.getText() + ",";
		}
		if(chk7.isSelected()) {
			rst+= chk7.getText() + ",";
		}
		if(chk8.isSelected()) {
			rst+= chk8.getText() + ",";
		}
		
		rst+="입니다.";
		
		txtResult.setText(rst);
		
	}
	
	
}
