/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.middleware.CheckoutTransactionManager;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.middleware.Logger;
import cs525.project.fujframework.middleware.LoggerImpl;
import cs525.project.fujframework.middleware.TransactionManager;
import cs525.project.fujframework.utils.BusinessConstants;
import cs525.project.fujframework.utils.SessionCache;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Jivan Nepali
 *
 */
public class CheckoutPaymentController extends Application implements Initializable {

	@FXML
	private Label lblCustomerName;

	@FXML
	private TextField totalRentalFeeTxt;

	@FXML
	private TextField dueDateTxt;

	@FXML
	private VBox dueDateVBox;

	@FXML
	private TableColumn<Car, String> nameCol;

	@FXML
	private TableColumn<Car, String> modelCol;

	@FXML
	private TableColumn<Car, Integer> yearCol;

	@FXML
	private TableColumn<Car, Double> feeCol;

	@FXML
	private TableColumn<Car, Number> quantityCol;

	@FXML
	private TableView<Car> cartTable;
	ObservableList<Car> cartObservable = FXCollections.observableArrayList();
	private Logger logger;
	public static CheckoutCart cart = null;

	public CheckoutPaymentController() {
		logger = new ConsoleLogger(new LoggerImpl());
	}

	public CheckoutPaymentController(CheckoutCart cartCar) {
		cart = cartCar;
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

		SessionCache session = SessionCache.getInstance();
		session.add(BusinessConstants.STAFF, BusinessConstants.STAFF);

		ObservableList<Car> cars = cartTable.getItems();
		LocalDate dueDate = ((DatePicker) dueDateVBox.getChildren().get(0)).getValue();
		int customerId = cart.getCustomerId();

		List<CheckoutRecordEntry> entries = new ArrayList<>();
		long diff = getTotalDays(LocalDate.now(), dueDate);
		if (diff < 0) {
			DialogHelper.toast("Due date can not be past date", AlertType.WARNING);
			// return;
		} else if (diff > 0) {
			for (Car car : cars) {

				CheckoutRecordEntry entry = new CheckoutRecordEntry();
				entry.setCheckoutDate(LocalDate.now());
				entry.setDueDate(dueDate);
				entry.setCustomerRefId(customerId);
				entry.setProductRefId(car.getProductId());
				entry.setQuantity(car.getQuantity());
				double fee = car.getRentalFeePerDay() * car.getQuantity() * diff;
				entry.setRentalFee(fee);
				entries.add(entry);
			}

			TransactionManager txn = new CheckoutTransactionManager();
			txn.proceedTransaction(entries, Car.class);
			DialogHelper.toast("Checkout record saved successfully!", AlertType.INFORMATION);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cartTable.setEditable(true);
		if (cart != null) {

			DatePicker checkInDatePicker = new DatePicker();
			checkInDatePicker.setValue(LocalDate.now());
			dueDateVBox.getChildren().add(checkInDatePicker);

			lblCustomerName.setText(cart.getCustomerName());
			getTableData();
			totalRentalFeeTxt.setText("" + getTotalFee());
		}

	}

	private void getTableData() {

		cartTable.getItems().clear();
		cart.getCars().forEach(t -> t.setQuantity(1));
		cartObservable.addAll(cart.getCars());

		populateTable();
	}

	private void populateTable() {

		nameCol.setCellValueFactory(new PropertyValueFactory<Car, String>("productName"));
		nameCol.setSortType(SortType.ASCENDING);
		modelCol.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
		yearCol.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
		feeCol.setCellValueFactory(new PropertyValueFactory<Car, Double>("rentalFeePerDay"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<Car, Number>("quantity"));

		quantityCol.setCellFactory(cell -> new IntegerEditingCell());
		cartTable.setItems(cartObservable);

	}

	public class IntegerEditingCell extends TableCell<Car, Number> {

		private final TextField textField = new TextField();
		private final Pattern intPattern = Pattern.compile("-?\\d+");

		public IntegerEditingCell() {
			textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
				if (!isNowFocused) {
					processEdit();
				}
			});
			textField.setOnAction(event -> processEdit());
		}

		private void processEdit() {
			String text = textField.getText();
			if (intPattern.matcher(text).matches() && Integer.parseInt(text) > 0) {
				commitEdit(Integer.parseInt(text));
			} else {
				cancelEdit();
			}
		}

		@Override
		public void updateItem(Number value, boolean empty) {
			super.updateItem(value, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else if (isEditing()) {
				setText(null);
				textField.setText(value.toString());
				setGraphic(textField);
			} else {
				setText(value.toString());
				setGraphic(null);
			}
		}

		@Override
		public void startEdit() {
			super.startEdit();
			Number value = getItem();
			if (value != null) {
				textField.setText(value.toString());
				setGraphic(textField);
				setText(null);
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			setText(getItem().toString());
			setGraphic(null);
		}

		@Override
		public void commitEdit(Number value) {

			super.commitEdit(value);
			((Car) this.getTableRow().getItem()).setQuantity(value.intValue());
			totalRentalFeeTxt.setText("" + getTotalFee());
		}
	}

	private double getTotalFee() {

		ObservableList<Car> cars = cartTable.getItems();
		double totalFee = 0.0;
		for (Car car : cars) {
			totalFee += car.getRentalFeePerDay() * car.getQuantity();
		}

		LocalDate dueDate = ((DatePicker) dueDateVBox.getChildren().get(0)).getValue();

		return totalFee * getTotalDays(LocalDate.now(), dueDate);
	}

	private long getTotalDays(LocalDate start, LocalDate end) {

		long diff = ChronoUnit.DAYS.between(start, end) + 1;

		if (diff < 0) {
			DialogHelper.toast("Due date can not be past date", AlertType.WARNING);
			return 0;
		}
		return diff;
	}

}
