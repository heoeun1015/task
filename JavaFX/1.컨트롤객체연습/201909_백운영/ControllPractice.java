package HomeWork;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllPractice extends Application implements Initializable {

	@FXML private TextField name;
	@FXML private RadioButton gendermale;
	@FXML private RadioButton genderfemale;
	@FXML private CheckBox chk1;
	@FXML private CheckBox chk2;
	@FXML private CheckBox chk3;
	@FXML private CheckBox chk4;
	@FXML private CheckBox chk5;
	@FXML private CheckBox chk6;
	@FXML private CheckBox chk7;
	@FXML private CheckBox chk8;
	@FXML private Button btn;
	@FXML private TextArea ta;

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = FXMLLoader.load(getClass().getResource("Homework.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("객체컨트롤연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gendermale.setSelected(true);

		ToggleGroup gender = new ToggleGroup();
		gendermale.setToggleGroup(gender);
		genderfemale.setToggleGroup(gender);

	}

	@FXML
	public void printbtn(ActionEvent event) {
		String strName = name.getText();
		String gender;

		if (gendermale.isSelected()) {
			gender = "남";
		} else {
			gender = "여";
		}

		String str = " " + strName + "님은\n" + gender + "성이시고\n";
		String chkstr = "";

		if (chk1.isSelected()) {
			chkstr += ", 여행";
		}

		if (chk2.isSelected()) {
			chkstr += ", 등산";
		}

		if (chk3.isSelected()) {
			chkstr += ", 독서";
		}

		if (chk4.isSelected()) {
			chkstr += ", 바둑";
		}

		if (chk5.isSelected()) {
			chkstr += ", 장기";
		}

		if (chk6.isSelected()) {
			chkstr += ", 게임";
		}

		if (chk7.isSelected()) {
			chkstr += ", 테니스";
		}

		if (chk8.isSelected()) {
			chkstr += ", 배드민턴";
		}

		chkstr += "이(가) 취미입니다.";

		if (chkstr.charAt(0) == ',') {
			chkstr = chkstr.substring(2);
		}

		str += chkstr;

		ta.setText(str);
	}

}