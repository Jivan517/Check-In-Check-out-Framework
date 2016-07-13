/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.rentalcarsystem.model.Address;
import cs525.rentalcarsystem.model.AppCustomer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ManageCustomerController extends Application implements Initializable {

	@FXML
	private TableView<AppCustomer> taview = new TableView<>();
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnEdit;
	@FXML
	private Text txtErrorMessage;
	
	@FXML TableView<AppCustomer> tblView;
	//@FXML TableColumn<AppCustomer, String> colCustomerId;
	@FXML TableColumn<AppCustomer,String> colCustomerName;
	@FXML TableColumn<AppCustomer, String> colEmail;
	@FXML TableColumn<AppCustomer,String> colPhoneNumber;
	@FXML TableColumn<Address, String> colAddress;
	
	
	private AppCustomer selectedCustomer;
	public ManageCustomerController() {

	}
	final ObservableList<AppCustomer> data= FXCollections.observableArrayList();
	private void populateCustomer(){
		CustomerFacade customerFacade = new CustomerFacadeImpl();
		//ResultSet result=customerFacade.getCustomerById(1);
		
		for(int i=0;i<10;i++){
		AppCustomer cus=new AppCustomer();
		cus.setPersonId(1);
		cus.setFirstName("Fisseha");
		cus.setEmail("abebfisseha5@gmail.com");
		cus.setPhone("24097652");
		data.add(cus);
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateCustomer();
		//colCustomerId.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("customerId"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("firstName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("email"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<AppCustomer,String>("phone"));
		tblView.setItems(data);
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
	protected void editCustomer(ActionEvent event) {
		selectedCustomer = taview.getSelectionModel().getSelectedItem();
		if (selectedCustomer != null) {
            /*AddCustomerController addCustomerController = new AddCustomerController();
            addCustomerController.addCustomer();*/
		
			
			txtErrorMessage.setText("Customer Successfully Updated!");
			txtErrorMessage.setFill(Color.GREEN);
		} else {
			txtErrorMessage.setText("Please Select Customer to Edit!");
			txtErrorMessage.setFill(Color.RED);
		}

	}

	@FXML
	protected void deleteCustomer(ActionEvent event) {
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
