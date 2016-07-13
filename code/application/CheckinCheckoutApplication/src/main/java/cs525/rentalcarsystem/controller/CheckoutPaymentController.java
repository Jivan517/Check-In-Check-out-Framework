/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.middleware.Logger;
import cs525.project.fujframework.middleware.LoggerImpl;
import cs525.rentalcarsystem.model.Car;
import cs525.rentalcarsystem.model.CheckoutCart;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * @author Jivan Nepali
 *
 */
public class CheckoutPaymentController extends Application implements Initializable {

	@FXML
	private TableColumn<Car, String> name;

	@FXML
	private TableColumn<Car, String> model;

	@FXML
	private TableColumn<Car, Integer> year;

	@FXML
	private TableColumn<Car, Double> fee;

	@FXML
	private TableColumn<Car, Integer> quantity;

	@FXML
	private TableView<Car> carList;
	ObservableList<Car> carListObservable = FXCollections.observableArrayList();
	private Logger logger;
	public static CheckoutCart cart;

	public CheckoutPaymentController() {
		cart = null;
		logger = new ConsoleLogger(new LoggerImpl());
	}

	public CheckoutPaymentController(CheckoutCart cartItem) {
		cart = cartItem;
		logger = new ConsoleLogger(new LoggerImpl());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(
				getClass().getClassLoader().getResource("cs525/rentalcarsystem/presentation/CheckoutPaymentForm.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add("cs525/rentalcarsystem/presentation/rentalcarsystem.css");

		primaryStage.setResizable(true);
		primaryStage.setTitle("Rental Fee Payment");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	protected void btnCancelAction(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	protected void btnPayAction(ActionEvent event) throws Exception {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}
}
