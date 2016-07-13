/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.middleware.CheckinTransactionManager;
import cs525.project.fujframework.middleware.TransactionManager;
import cs525.project.fujframework.utils.BusinessConstants;
import cs525.project.fujframework.utils.SessionCache;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
import cs525.rentalcarsystem.model.Car;
import cs525.rentalcarsystem.model.CheckinData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author paudelumesh
 *
 */
public class CheckinPaymentController extends Application implements Initializable {
	@FXML
	private Label overDueFineAmountLabel;
	public static List<CheckoutRecordEntry> entries = null;
	private TransactionManager checkinTransactionManager;

	/**
	 * 
	 */
	public CheckinPaymentController() {
		checkinTransactionManager = new CheckinTransactionManager();
	}

	/**
	 * 
	 */
	public CheckinPaymentController(List<CheckoutRecordEntry> entries) {
		this.entries = entries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(
				getClass().getClassLoader().getResource("cs525/rentalcarsystem/presentation/CheckinPaymentForm.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add("cs525/rentalcarsystem/presentation/rentalcarsystem.css");
		primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		primaryStage.setResizable(true);
		primaryStage.setTitle("Overdue Fee Payment");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	protected void onPaymentCancel(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	protected void onPayment(ActionEvent event) throws Exception {
		SessionCache session = SessionCache.getInstance();
		session.add(BusinessConstants.STAFF, BusinessConstants.STAFF);
		checkinTransactionManager.proceedTransaction(entries, Car.class);
		DialogHelper.toast("Checkin record saved successfully!", AlertType.INFORMATION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		double totalFine = 0.0;
		for (CheckoutRecordEntry entry : entries) {
			totalFine += entry.getRentalFine();
			System.out.println("CALCULATING TOTAL: " + totalFine);
		}

		overDueFineAmountLabel.setText("" + totalFine);
	}

}
