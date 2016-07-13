/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
import cs525.rentalcarsystem.model.Address;
import cs525.rentalcarsystem.model.AppCustomer;
import cs525.rentalcarsystem.model.Car;
import cs525.rentalcarsystem.presentation.Main;
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
import javafx.scene.control.Alert.AlertType;
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
	private Stage primaryStage;
	private Stage rootStage = new Stage();
	
	@FXML TableView<AppCustomer> tblView;
	//@FXML TableColumn<AppCustomer, String> colCustomerId;
	@FXML TableColumn<AppCustomer,String> colCustomerName;
	@FXML TableColumn<AppCustomer, String> colEmail;
	@FXML TableColumn<AppCustomer,String> colPhoneNumber;
	@FXML TableColumn<Address, String> colAddress;
	private ObservableList<AppCustomer> customerList= FXCollections.observableArrayList();
	
	
	private AppCustomer selectedCustomer;
	public ManageCustomerController() {

	}
	
	
	private void populateCustomer(){
		try{
			CustomerFacade customerFacade = new CustomerFacadeImpl();
			ResultSet result=customerFacade.getAllCustomers(AppCustomer.class);
			while(result.next()){
				AppCustomer cus=new AppCustomer();
				cus.setPersonId(result.getInt("customerId"));
				cus.setFirstName(result.getString("firstName"));
				cus.setEmail(result.getString("email"));
				cus.setPhone(result.getString("phone"));
				customerList.add(cus);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateCustomer();
		//colCustomerId.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("customerId"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("firstName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("email"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<AppCustomer,String>("phone"));
		tblView.setItems(customerList);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.rootStage = primaryStage;
		this.primaryStage.setTitle("Add Customer Form");
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("ManageCustomerForm.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Scene scene = new Scene(page);
			Stage ps = new Stage();
			ps.setScene(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	protected void editCustomer(ActionEvent event) {
		
		ObservableList<AppCustomer> customers = taview.getSelectionModel().getSelectedItems();
		if (customers.size() > 1) {
			DialogHelper.toast("Please, select only one record!", AlertType.WARNING);
			return;
		}
		if (customers.size() < 1) {
			DialogHelper.toast("Please, select a record!", AlertType.WARNING);
			return;
		}

		int customerId = customers.get(0).getPersonId();
		 AddCustomerController addCustomerController = new AddCustomerController(customerId);		
		Stage stage = new Stage();
		try {
			addCustomerController.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*	
			txtErrorMessage.setText("Customer Successfully Updated!");
			txtErrorMessage.setFill(Color.GREEN);
		} else {
			txtErrorMessage.setText("Please Select Customer to Edit!");
			txtErrorMessage.setFill(Color.RED);
		}*/

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
