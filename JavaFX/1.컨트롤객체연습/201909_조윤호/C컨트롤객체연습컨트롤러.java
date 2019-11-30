package kr.or.ddit.basic;


import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class C컨트롤객체연습컨트롤러 implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}	
	
	@FXML 
	private TextField txt;	

	@FXML 
	private RadioButton male;
	@FXML 
	private RadioButton female;

	@FXML
	private CheckBox a;
	@FXML 
	private CheckBox b;
	@FXML 
	private CheckBox c;
	@FXML 
	private CheckBox d;
	@FXML 
	private CheckBox q;
	@FXML 
	private CheckBox f;
	@FXML 
	private CheckBox g;
	@FXML 
	private CheckBox h;

	@FXML
	private Button btnDan;

	@FXML 
	private TextArea txtResult;



	
	@FXML
	void btnDanclick(ActionEvent e) {
		String s = "";
		if(male.isSelected()) {
			s = male.getText();
		}else if(female.isSelected()) {
			s = female.getText();
		}
		
		
		String x = "";
		if(a.isSelected()) {
			x = a.getText();
		}else if(b.isSelected()) {
			x = b.getText();
		}else if(c.isSelected()) {
			x = c.getText();
		}else if(d.isSelected()) {
			x = d.getText();
		}else if(q.isSelected()) {
			x = q.getText();
		}else if(f.isSelected()) {
			x = f.getText();
		}else if(g.isSelected()) {
			x = g.getText();
		}else if(h.isSelected()) {
			x = h.getText();
		}
		
		

		txtResult.setText("이름 : " + txt.getText() + "\n" 
						  + "성별 : " + s + "\n"
						  + "취미 : " + x);
	}
}

