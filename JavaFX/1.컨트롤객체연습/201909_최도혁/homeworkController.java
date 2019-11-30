package homework1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class homeworkController implements Initializable{

	@FXML TextField userName;
	@FXML RadioButton genderMale;
	@FXML RadioButton genderFemale;
	@FXML CheckBox travel;
	@FXML CheckBox climb;
	@FXML CheckBox read;
	@FXML CheckBox baduk;
	@FXML CheckBox jangi;
	@FXML CheckBox game;
	@FXML CheckBox tennis;
	@FXML CheckBox badminton;
	@FXML Button print;
	@FXML TextArea textArea;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		genderMale.setSelected(true);
		
		ToggleGroup genderGroup = new ToggleGroup();
		
		genderMale.setToggleGroup(genderGroup);
		genderFemale.setToggleGroup(genderGroup);
		
	}
	
	@FXML public void printButton(ActionEvent event) {
		String name = userName.getText();
		String gender;
		if(genderMale.isSelected()) {
			gender = "남";
		} else
		{
			gender = "여";
		}
		
		String str = " "+name+"님은\n"+gender+" 성이고\n";
		String chkstr = "";
		
		if(travel.isSelected())
		{
			chkstr += ", 여행";
		}
		
		if(climb.isSelected())
		{
			chkstr += ", 등산";
		}
		
		if(read.isSelected())
		{
			chkstr += ", 독서";
		}
		
		if(baduk.isSelected())
		{
			chkstr += ", 바둑";
		}
		
		if(jangi.isSelected())
		{
			chkstr += ", 장기";
		}
		
		if(game.isSelected())
		{
			chkstr += ", 게임";
		}
		
		if(tennis.isSelected())
		{
			chkstr += ", 테니스";
		}
		
		if(badminton.isSelected())
		{
			chkstr += ", 배드민턴";
		}
		
		chkstr += "이(가) 취미입니다.";
		
		if(chkstr.charAt(0)==',')
		{
			chkstr = chkstr.substring(2);
		}
		
		str += chkstr;
		
		textArea.setText(str);
	}
	
	
	

}
