/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;
import java.awt.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class used to authenticate users based on their appropriate 
 * user name and password
 * @author Fish
 *
 */
public class LoginController extends Application {
  
	@FXML private TextField txtUserName;
	@FXML private TextField txtPassword;
	@FXML private Button btnLogin;
	@FXML private Text txtErrorMessage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader= FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		primaryStage.setTitle("Login ");
		Scene scene=new Scene(page);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	/**
	 * This method authenticates user if the user name and password are correct
	 * if not it displays appropriate message
	 */
	@FXML
	public void login(ActionEvent event){
		if(!txtUserName.getText().isEmpty()&& !txtPassword.getText().isEmpty()){
			
		}
		else{
			txtErrorMessage.setFill(Color.RED);
			txtErrorMessage.setText("Invalid User Name and Password!!");
		}
		
		
	}
	
	
	

}
