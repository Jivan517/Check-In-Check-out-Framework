/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

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
 * This class used to manage customers like edit and delete
 *  selected customer form the grid
 * @author Fish
 *
 */
public class ManageCustomerController extends Application {
	
	@FXML private Button btnDelete;
	@FXML private Button btnEdit;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root= FXMLLoader.load(getClass().getResource("ManageCustomerForm.fxml"));
		primaryStage.setTitle("Manage Customer ");
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
				
		
	}
	
	
	
	

}
