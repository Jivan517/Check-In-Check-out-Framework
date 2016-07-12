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
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;

	private Command command;

	public AddCustomerController() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("AddCustomerForm.fxml"));
		primaryStage.setTitle("Add Customer");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * This method adds application user customers by calling
	 * SaveCustomerCommand command
	 */
	@FXML
	public void addCustomer() {
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

	/**
	 * Action will be performed when the user click cance button
	 */
	@FXML
	public void cancelHandler() {

	}
	public static void main(String[] args) {
        Application.launch(args);
	}

}
