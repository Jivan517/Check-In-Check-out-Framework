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
import java.util.stream.Collectors;

import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
import cs525.rentalcarsystem.model.Address;
import cs525.rentalcarsystem.model.AppCustomer;
import cs525.rentalcarsystem.presentation.MainForm;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
	private Button btnDelete;
	@FXML
	private Button btnEdit;
	@FXML
	Button btnAddCustomer;
	@FXML
	Button btnSearch;
	@FXML
	TextField txtSearchCustomer;
	@FXML
	private Text txtErrorMessage;
	private Stage primaryStage;
	@FXML
	TableView<AppCustomer> tblView;
	@FXML
	TableColumn<AppCustomer, Integer> colCustomerId;
	@FXML
	TableColumn<AppCustomer, String> colCustomerName;
	@FXML
	TableColumn<AppCustomer, String> colEmail;
	@FXML
	TableColumn<AppCustomer, String> colPhoneNumber;
	/*
	 * @FXML TableColumn<Address, String> colAddress;
	 */

	ObservableList<AppCustomer> customerList = FXCollections.observableArrayList();

	// private AppCustomer selectedCustomer;

	public ManageCustomerController() {

	}

	private void populateCustomer() {
		try {
			CustomerFacade customerFacade = new CustomerFacadeImpl();
			ResultSet result = customerFacade.getAllCustomers(AppCustomer.class);
			while (result.next()) {
				AppCustomer cus = new AppCustomer();
				cus.setPersonId(result.getInt("customerId"));
				cus.setFirstName(result.getString("firstName"));
				cus.setMiddleName(result.getString("middleName"));
				cus.setLastName(result.getString("lastName"));
				cus.setEmail(result.getString("email"));
				cus.setPhone(result.getString("phone"));
				cus.setAddress(new cs525.project.fujframework.core.Address());
				ResultSet addressResult = customerFacade.getAddressByCustomerId(cus.getPersonId(), Address.class);
				while (addressResult.next()) {
					int addressId = addressResult.getInt("addressId");
					String street = addressResult.getString("streetAddress");
					String city = addressResult.getString("city");
					int zipCode = addressResult.getInt("zipCode");
					String state = addressResult.getString("state");
					Address address = new Address(street, city, zipCode, state);
					address.setAddressId(addressId);
					cus.setAddress(address);
				}

				customerList.add(cus);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void populateTable() {
		colCustomerId.setCellValueFactory(new PropertyValueFactory<AppCustomer, Integer>("personId"));
		colCustomerName.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("fullName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("email"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<AppCustomer, String>("phone"));
		tblView.setItems(customerList);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		populateCustomer();
		populateTable();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Add Customer Form");
		try {
			FXMLLoader loader = new FXMLLoader(MainForm.class.getResource("ManageCustomerForm.fxml"));
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

	@FXML
	protected void btnCancelAction(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	protected void AddCustomer(ActionEvent event) throws Exception {
		AddCustomerController addcustomer = new AddCustomerController(0, 0);
		Stage stage = new Stage();
		((Node) (event.getSource())).getScene().getWindow().hide();
		addcustomer.start(stage);
	}

	@FXML
	protected void editCustomer(ActionEvent event) throws Exception {

		ObservableList<AppCustomer> customers = tblView.getSelectionModel().getSelectedItems();
		System.out.println("list size: " + customers.size());
		if (customers.size() > 1) {
			DialogHelper.toast("Please, select only one record!", AlertType.WARNING);
			return;
		}
		if (customers.size() < 1) {
			DialogHelper.toast("Please, select a record!", AlertType.WARNING);
			return;
		}

		int customerId = customers.get(0).getPersonId();
		int addressId = customers.get(0).getAddress().getAddressId();
		AddCustomerController addCustomerController = new AddCustomerController(customerId, addressId);
		Stage stage = new Stage();
		((Node) (event.getSource())).getScene().getWindow().hide();
		addCustomerController.start(stage);

	}

	@FXML
	protected void deleteCustomer(ActionEvent event) {
		ObservableList<AppCustomer> customers = tblView.getSelectionModel().getSelectedItems();
		System.out.println("list size: " + customers.size());
		if (customers.size() > 1) {
			DialogHelper.toast("Please, select only one record!", AlertType.WARNING);
			return;
		}
		if (customers.size() < 1) {
			DialogHelper.toast("Please, select a record!", AlertType.WARNING);
			return;
		}

		AppCustomer customer = customers.get(0);
		CustomerFacade cutFacade = new CustomerFacadeImpl();
		cutFacade.removeCustomer(customer);
		txtErrorMessage.setText("Customer Successfully Deleted");
		txtErrorMessage.setFill(Color.GREEN);

	}

	@FXML
	protected void searchCustomer(ActionEvent event) {
		String searchText = txtSearchCustomer.getText();
		searchText.toLowerCase();
		customerList.clear();
		populateCustomer();
		if (!searchText.isEmpty()) {

			List<AppCustomer> appCustomerList = customerList.stream()
					.filter(m -> m.getFirstName().toLowerCase().contains(searchText)
							|| m.getMiddleName().toLowerCase().contains(searchText)
							|| m.getLastName().toLowerCase().contains(searchText))
					.collect(Collectors.toList());

			customerList.clear();
			for (AppCustomer cust : appCustomerList) {
				customerList.add(cust);
			}
			populateTable();
		} else {
			customerList.clear();
			populateCustomer();
			populateTable();
			/*
			 * txtErrorMessage.setText(txtSearchCustomer.getText() +
			 * " Customer Not Found"); txtErrorMessage.setFill(Color.RED);
			 */
		}

	}
	@FXML
	protected void cancelBtnAction(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

}
