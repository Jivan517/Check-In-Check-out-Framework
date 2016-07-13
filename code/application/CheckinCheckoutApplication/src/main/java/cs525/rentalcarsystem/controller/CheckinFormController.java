/**
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

import cs525.project.fujframework.core.Customer;
import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.project.fujframework.core.Product;
import cs525.project.fujframework.core.SysUserFacade;
import cs525.project.fujframework.core.SysUserFacadeImpl;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.rentalcarsystem.model.AppCustomer;
import cs525.rentalcarsystem.model.ApplicationUser;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author paudelumesh
 *
 */
public class CheckinFormController extends Application implements Initializable {
	@FXML
	private ComboBox<String> customerCombo;
	@FXML
	private TableView<Product> products;

	private CommandManager command;
	private CustomerFacade facade;

	/**
	 * 
	 */
	public CheckinFormController() {
		command = new CommandManagerImpl();
		facade = new CustomerFacadeImpl();
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> customers = new ArrayList<>();
		try {
			ResultSet rs = facade.getAllCustomers(AppCustomer.class);
			while (rs.next()) {
				System.out.println(
						rs.getInt("customerId") + "-" + rs.getString("firstName") + " " + rs.getString("lastName"));
				customers.add(
						rs.getInt("customerId") + "-" + rs.getString("firstName") + " " + rs.getString("lastName"));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		customerCombo.setItems(FXCollections.observableArrayList(customers));
	}
}
