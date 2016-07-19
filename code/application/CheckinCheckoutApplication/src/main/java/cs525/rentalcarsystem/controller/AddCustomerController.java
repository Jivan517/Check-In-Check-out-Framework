/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
import cs525.rentalcarsystem.controller.utils.Validator;
import cs525.rentalcarsystem.model.Address;
import cs525.rentalcarsystem.model.AppCustomer;
import cs525.rentalcarsystem.model.FormException;
import cs525.rentalcarsystem.presentation.MainForm;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is used to create new customers
 * 
 * @author Fish
 *
 */
public class AddCustomerController extends Application implements Initializable {

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
	private Text txtErrorMessag;

	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;

	private Stage primaryStage;
	private Stage rootStage = new Stage();

	private CommandManager command;
	public static int customerId;
	public static int addressId ;
	private ManageCustomerController manageController;

	public AddCustomerController() {

		this.command = new CommandManagerImpl();
	}

	public AddCustomerController(int customerId,int addressId) {
		this.customerId = customerId;
		this.addressId = addressId;

		this.command = new CommandManagerImpl();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.rootStage = primaryStage;
		this.primaryStage.setTitle("Add Customer Form");
		try {
			FXMLLoader loader = new FXMLLoader(MainForm.class.getResource("AddCustomerForm.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
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
	protected void addCustomer(ActionEvent event) throws Exception {

		try {
			Validator.validateEmptiness(txtFirstName);
			Validator.validateEmptiness(txtLastName);
			Validator.validateEmptiness(txtStreet);
			Validator.validateNumeric(txtZipCode);

			AppCustomer customer = new AppCustomer();
			customer.setFirstName(txtFirstName.getText());
			customer.setMiddleName(txtiddleName.getText());
			customer.setLastName(txtLastName.getText());
			customer.setEmail(txtEmail.getText());
			customer.setPhone(txtPhoneNumber.getText());
			Address userAddress = new Address(txtStreet.getText(), txtCity.getText(),
					Integer.parseInt(txtZipCode.getText().trim()), txtStreet.getText());
			
			if (customerId > 0){
                userAddress.setAddressId(addressId);
				customer.setPersonId(customerId);
				
			}			
			customer.setAddress(userAddress);
			this.command.saveCustomer(customer);
			((Node) (event.getSource())).getScene().getWindow().hide();
			backToMaageController();
		} catch (FormException ex) {
			DialogHelper.toast(ex.getMessage(), AlertType.ERROR);
		}

	}

	/**
	 * Action will be performed when the user click cancel button
	 * 
	 * @throws Exception
	 */
	@FXML
	protected void cancelHandler(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		//backToMaageController();

	}

	private void initCustomerInfo() {

		try {
			CustomerFacade custFacade = new CustomerFacadeImpl();
			ResultSet result = custFacade.getCustomerById(customerId);
			while (result.next()) {
				txtFirstName.setText(result.getString("firstName"));
				txtiddleName.setText(result.getString("middleName"));
				txtLastName.setText(result.getString("lastName"));
				txtEmail.setText(result.getString("email"));
				txtPhoneNumber.setText(result.getString("phone"));
				ResultSet address = custFacade.getAddressByCustomerId(customerId, Address.class);
				while (address.next()) {
					addressId = address.getInt("addressId");
					txtStreet.setText(address.getString("streetAddress"));
					txtCity.setText(address.getString("city"));
					txtZipCode.setText("" + address.getInt("zipCode"));
					txtState.setText(address.getString("state"));

				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (customerId > 0)
			initCustomerInfo();

	}

	private void backToMaageController() throws Exception {
		manageController = new ManageCustomerController();
		Stage stage = new Stage();
		manageController.start(stage);
	}

}
