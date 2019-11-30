package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;

public class H_Controller implements Initializable {

	@FXML Button btn;
	@FXML TextField txt;
	@FXML TextArea txtresult;
	@FXML RadioButton male;
	@FXML RadioButton female;
	@FXML ToggleGroup toggle;
	@FXML CheckBox h1;
	@FXML CheckBox h2;
	@FXML CheckBox h3;
	@FXML CheckBox h4;
	@FXML CheckBox h5;
	@FXML CheckBox h6;
	@FXML CheckBox h7;
	@FXML CheckBox h8;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//male.setToggleGroup(toggle);
		//female.setToggleGroup(toggle);
	}
	
	/**
	 * 단 버튼이 클릭되었을 때 처리하는 메서드
	 * @param e
	 */
	@FXML
	void btnClick(ActionEvent e) {
		String name = txt.getText();
		
		String gender;
		if(male.isSelected()) {
			gender = "남자";
		}else {
			gender = "여자";
		}
		String str = name + "님의 성별은 " + gender +"이고, ";
		String hobby ="";
		//여행, 등산,독석,바둑,장기,게임,테니스,배드민턴
		if(h1.isSelected()) {
			hobby += "여행 ";
		}
		if(h2.isSelected()) {
			hobby += "등산 ";
		}
		if(h3.isSelected()) {
			hobby += "독서 ";
		}
		if(h4.isSelected()) {
			hobby += "바둑 ";
		}
		if(h5.isSelected()) {
			hobby += "장기 ";
		}
		if(h6.isSelected()) {
			hobby += "게임 ";
		}
		if(h7.isSelected()) {
			hobby += "테니스 ";
		}
		if(h8.isSelected()) {
			hobby += "배드민턴 ";
		}
		str += hobby + "입니다.";
		txtresult.setText(str);
	}
}
