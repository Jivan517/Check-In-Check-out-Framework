/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cs525.project.fujframework.core.Customer;
import cs525.project.fujframework.core.Product;
import cs525.project.fujframework.core.SysUserFacade;
import cs525.project.fujframework.core.SysUserFacadeImpl;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.rentalcarsystem.model.ApplicationUser;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author paudelumesh
 *
 */
public class CheckinFormController extends Application {
	@FXML
	private ComboBox<String> customerCombo;
	@FXML
	private TableView<Product> products;

	private CommandManager command;
	private SysUserFacade facade;

	/**
	 * 
	 */
	public CheckinFormController() {
		command = new CommandManagerImpl();
		facade = new SysUserFacadeImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// customerIdTxt.focusedProperty().addListener(new
		// ChangeListener<Boolean>() {
		// @Override
		// public void changed(ObservableValue<? extends Boolean> observable,
		// Boolean oldValue, Boolean newValue) {
		// if (newValue) {
		// System.out.println("Textfield on focus");
		// } else {
		// System.out.println("Textfield out focus");
		// }
		// }
		// });
	}

	@FXML
	void onCustomerSelected() {
		List<String> customers = new ArrayList<>();
		try {
			ResultSet rs = facade.getAllUsers(Customer.class);
			while (rs.next()) {
				customers.add(
						rs.getInt("customerId") + "-" + rs.getString("firstName") + " " + rs.getString("lastName"));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		customerCombo.setItems(FXCollections.observableArrayList(customers));
	}
}
