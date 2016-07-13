/**
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

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.core.CheckoutRecordFacade;
import cs525.project.fujframework.core.CheckoutRecordFacadeImpl;
import cs525.project.fujframework.core.Customer;
import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.project.fujframework.core.Product;
import cs525.project.fujframework.core.ProductFacade;
import cs525.project.fujframework.core.SysUserFacade;
import cs525.project.fujframework.core.SysUserFacadeImpl;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.rentalcarsystem.model.AppCustomer;
import cs525.rentalcarsystem.model.ApplicationUser;
import cs525.rentalcarsystem.model.Car;
import cs525.rentalcarsystem.model.CheckinData;
import cs525.rentalcarsystem.model.ComboBoxData;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author paudelumesh
 *
 */
public class CheckinFormController extends Application implements Initializable {
	@FXML
	private ComboBox<ComboBoxData<Integer, String>> customerCombo;
	@FXML
	private TableView<CheckinData> checkinRecordTable;
	@FXML
	private TableColumn<CheckinData, String> name;
	@FXML
	private TableColumn<CheckinData, String> model;
	@FXML
	private TableColumn<CheckinData, Double> rentalFeePerDay;
	@FXML
	private TableColumn<CheckinData, Double> rentalFinePerDay;
	@FXML
	private TableColumn<CheckinData, Double> rentalFee;
	@FXML
	private TableColumn<CheckinData, String> dueDate;

	private CommandManager command;
	private CustomerFacade facade;
	private CheckoutRecordFacade checkoutFacade;
	private ObservableList<ComboBoxData<Integer, String>> customerListObservable = FXCollections.observableArrayList();

	/**
	 * 
	 */
	public CheckinFormController() {
		command = new CommandManagerImpl();
		facade = new CustomerFacadeImpl();
		checkoutFacade = new CheckoutRecordFacadeImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader
				.load(getClass().getClassLoader().getResource("cs525/rentalcarsystem/presentation/CheckinForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("cs525/rentalcarsystem/presentation/rentalcarsystem.css");

		primaryStage.setResizable(true);
		primaryStage.setTitle("Checkin Form");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	void onCustomerSelected() {
		ObservableList<CheckinData> data = FXCollections.observableArrayList();
		// selected customer
		ComboBoxData<Integer, String> selectedCustomer = customerCombo.getSelectionModel().getSelectedItem();
		ResultSet recordEntries = checkoutFacade.getAllCheckoutRecordsByCustomer(selectedCustomer.getKey(),
				CheckoutRecordEntry.class, Car.class);
		try {
			while (recordEntries.next()) {
				CheckinData checkinData = new CheckinData();
				checkinData.setModel(recordEntries.getString("model"));
				checkinData.setName(recordEntries.getString("name"));
				checkinData.setRentalFee(recordEntries.getDouble("rentalFeePerDay"));
				checkinData.setRentalFinePerDay(recordEntries.getDouble("overDueFinePerDay"));
				checkinData.setRentalFee(recordEntries.getDouble("rentalFee"));
				checkinData.setDueDate(recordEntries.getString("dueDate"));

				data.add(checkinData);
				System.out.println(checkinData.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		populateTable(data);
	}

	public void populateTable(ObservableList<CheckinData> checkinRecords) {
		name.setCellValueFactory(new PropertyValueFactory<CheckinData, String>("name"));
		model.setCellValueFactory(new PropertyValueFactory<CheckinData, String>("model"));
		rentalFeePerDay.setCellValueFactory(new PropertyValueFactory<CheckinData, Double>("rentalFeePerDay"));
		rentalFinePerDay.setCellValueFactory(new PropertyValueFactory<CheckinData, Double>("rentalFinePerDay"));
		rentalFee.setCellValueFactory(new PropertyValueFactory<CheckinData, Double>("rentalFee"));
		dueDate.setCellValueFactory(new PropertyValueFactory<CheckinData, String>("dueDate"));

		if (checkinRecords != null)
			checkinRecordTable.setItems(checkinRecords);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Car> customers = FXCollections.observableArrayList();
		try {
			ResultSet rs = facade.getAllCustomers(AppCustomer.class);
			while (rs.next()) {
				int id = rs.getInt("customerId");
				String firstName = rs.getString("firstName");
				String middleName = rs.getString("middleName");
				String lName = rs.getString("lastName");

				String content = firstName + " " + middleName + " " + lName;

				ComboBoxData<Integer, String> data = new ComboBoxData<Integer, String>();
				data.setKey(id);
				data.setValue(content);
				customerListObservable.add(data);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.customerCombo.setPromptText("--Choose for checkin");
		this.customerCombo.setItems(customerListObservable);
	}

	@FXML
	protected void btnCancelAction(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
}
