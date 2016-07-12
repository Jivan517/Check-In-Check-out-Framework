/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;
import cs525.project.fujframework.middleware.Command;
import cs525.project.fujframework.middleware.SaveCustomerCommand;
import cs525.rentalcarsystem.backend.Address;
import cs525.rentalcarsystem.backend.AppCustomer;
import cs525.rentalcarsystem.presentation.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is used to create new customers
 * 
 * @author Fish
 *
 */
public class AddCustomerController extends Application {

	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtiddleName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtZipCode;
	@FXML
	private TextField txtState;
	
	@FXML private Text txtErrorMessag;

	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	
	private Stage primaryStage;
	private Stage rootStage = new Stage();

	private Command command;
	public AddCustomerController() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.rootStage = primaryStage;
		this.primaryStage.setTitle("Add Customer Form");
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddCustomerForm.fxml"));
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

	/**
	 * This method adds application user customers by calling
	 * SaveCustomerCommand command
	 */
	@FXML
	protected void addCustomer() {
		if(!txtFirstName.getText().isEmpty()){
			AppCustomer customer = new AppCustomer();
			customer.setFirstName(txtFirstName.getText());
			customer.setMiddleName(txtiddleName.getText());
			customer.setLastName(txtLastName.getText());
			customer.setEmail(txtEmail.getText());
			customer.setPhoneNumber(txtPhoneNumber.getText());
			Address userAddress = new Address(txtStreet.getText(), txtCity.getText(),
					Integer.parseInt(txtZipCode.getText()), txtStreet.getText());
			customer.setAddress(userAddress);
			command = new SaveCustomerCommand(customer);
			command.execute();
		}
		else{
			
			txtErrorMessag.setText("All fields are Required");
			txtErrorMessag.setFill(Color.RED);
		}
		
	}

	/**
	 * Action will be performed when the user click cancel button
	 */
	@FXML
	protected void cancelHandler() {
       
	}
	

}