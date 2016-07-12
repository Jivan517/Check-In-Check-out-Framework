/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;
import java.awt.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root= FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		primaryStage.setTitle("Login ");
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
	}
	
	public void login(){
		
		
	}
	
	
	

}
