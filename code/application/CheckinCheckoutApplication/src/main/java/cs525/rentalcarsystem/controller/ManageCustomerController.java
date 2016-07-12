/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import cs525.rentalcarsystem.backend.AppCustomer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class used to manage customers like edit and delete selected customer
 * form the grid
 * 
 * @author Fish
 *
 */
public class ManageCustomerController extends Application {

	@FXML
	private TableView<AppCustomer> taview = new TableView<>();
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnEdit;
	@FXML
	private Text txtErrorMessage;
	
	@FXML TableView tblView;
	@FXML TableColumn colCustomerName;
	@FXML TableColumn colEmail;
	@FXML TableColumn colPhoneNumber;
	@FXML TableColumn colAddress;
	
	private AppCustomer selectedCustomer;
	public ManageCustomerController() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = FXMLLoader.load(getClass().getResource("ManageCustomerForm.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		primaryStage.setTitle("Manage Customer ");
		Scene scene = new Scene(page);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@FXML
	protected void editCustomer() {
		selectedCustomer = taview.getSelectionModel().getSelectedItem();
		if (selectedCustomer != null) {

			
			
			txtErrorMessage.setText("Customer Successfully Updated!");
			txtErrorMessage.setFill(Color.GREEN);
		} else {
			txtErrorMessage.setText("Please Select Customer to Edit!");
			txtErrorMessage.setFill(Color.RED);
		}

	}

	@FXML
	protected void deleteCustomer() {
		selectedCustomer = taview.getSelectionModel().getSelectedItem();
		if (selectedCustomer != null) {
            
			
			txtErrorMessage.setText("Customer Successfully Deleted!");
			txtErrorMessage.setFill(Color.GREEN);
			
		} else {
			txtErrorMessage.setText("Please Select Customer to Delete!");
			txtErrorMessage.setFill(Color.RED);
		}

	}

}
