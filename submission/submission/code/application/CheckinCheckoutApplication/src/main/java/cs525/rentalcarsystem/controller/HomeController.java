/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */
package cs525.rentalcarsystem.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.CheckoutRecordEntry;
import cs525.project.fujframework.core.CheckoutRecordFacade;
import cs525.project.fujframework.core.CheckoutRecordProtectionProxy;
import cs525.rentalcarsystem.model.KeyValuePair;
import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.middleware.Logger;
import cs525.project.fujframework.middleware.LoggerImpl;
import cs525.project.fujframework.utils.BusinessConstants;
import cs525.project.fujframework.utils.SessionCache;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author OWNER
 *
 */

public class HomeController extends Application implements Initializable {

	private Logger logger;
	@FXML
	private MenuBar menubar;

	private CheckoutRecordFacade facade;

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private CategoryAxis xAxis;
	
	@FXML
	private Menu carMenu;

	@FXML
	private ImageView imageView;

	private ObservableList<String> monthNames = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cs525/rentalcarsystem/presentation/MainForm.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add("cs525/rentalcarsystem/presentation/background.css");
		primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Home - Car Rental System [V1.0.0]");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public HomeController() {
		this.logger = new ConsoleLogger(new LoggerImpl());
		this.facade = new CheckoutRecordProtectionProxy();
	}

	@FXML
	protected void listUsersMenuAction(ActionEvent event) throws Exception {

		Stage stage = new Stage();
		ApplicationUserController controller = new ApplicationUserController();
		controller.start(stage);
	}

	@FXML
	protected void addUserMenuAction(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		ApplicationUserController controller = new ApplicationUserController();
		controller.start(stage);
	}

	@FXML
	protected void listCustomersMenuAction(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		ManageCustomerController controller = new ManageCustomerController();
		controller.start(stage);
	}

	@FXML
	protected void addCustomerMenuAction(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		AddCustomerController controller = new AddCustomerController();
		controller.start(stage);
	}

	@FXML
	protected void listCarsMenuAction(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		CheckoutController controller = new CheckoutController("Car List - Car Rental System");
		controller.start(stage);
	}

	@FXML
	protected void addCarMenuAction(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		CarController controller = new CarController();
		controller.start(stage);
	}

	@FXML
	protected void checkoutMenuAction(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		CheckoutController controller = new CheckoutController("Checkout Car(s) - Car Rental System");
		controller.start(stage);
	}

	@FXML
	protected void checkinMenuAction(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		CheckinFormController controller = new CheckinFormController();
		controller.start(stage);
	}

	@FXML
	protected void logoutMenuAction(ActionEvent event) throws Exception {

		SessionCache session = SessionCache.getInstance();
		try {
			session.remove(BusinessConstants.ADMIN);
			session.remove(BusinessConstants.STAFF);
			session.remove(BusinessConstants.LOGGED_IN);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		menubar.getScene().getWindow().hide();

		Stage stage = new Stage();
		LoginController controller = new LoginController();
		controller.start(stage);

	}

	@FXML
	protected void aboutMenuAction(ActionEvent event) throws Exception {

		DialogHelper.toast(
				"Rental Car System\nVersion: 1.0.0\nCopyright 2015\n\n\nDEVELOPERS\nFisseha Abebe Chari\nJivan Nepali\nUmesh Paudyal\n\n",
				AlertType.INFORMATION);
	}

	@FXML
	protected void exitMenuAction(ActionEvent event) throws Exception {

		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Get an array with the English month names.
		String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
		// Convert it to a list and add it to our ObservableList of months.
		monthNames.addAll(Arrays.asList(months));

		xAxis.setCategories(monthNames);

		renderGraph();
		//carMenu.setDisable(true);
		carMenu.hide();
		imageView.setImage(new Image("file:resources/images/car.jpg"));
	}

	/**
	 * Sets the persons to show the statistics for.
	 * 
	 * @param persons
	 */
	private void renderGraph() {
		int[] monthCounter = new int[12];
		List<KeyValuePair<LocalDate, Integer>> dates = getCheckoutDates();

		for (KeyValuePair<LocalDate, Integer> date : dates) {
			int month = date.getKey().getMonthValue();
			monthCounter[month] += date.getValue();
		}

		XYChart.Series<String, Integer> series = createMonthDataSeries(monthCounter);
		barChart.getData().add(series);
	}

	/**
	 * Creates a XYChart.Data object for each month. All month data is then
	 * returned as a series.
	 * 
	 * @param monthCounter
	 *            Array with a number for each month. Must be of length 12!
	 * @return
	 */
	private XYChart.Series<String, Integer> createMonthDataSeries(int[] monthCounter) {
		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();

		for (int i = 0; i < monthCounter.length; i++) {
			XYChart.Data<String, Integer> monthData = new XYChart.Data<String, Integer>(monthNames.get(i),
					monthCounter[i]);
			series.getData().add(monthData);
		}

		return series;
	}

	public List<KeyValuePair<LocalDate, Integer>> getCheckoutDates() {

		List<KeyValuePair<LocalDate, Integer>> dates = new ArrayList<>();

		ResultSet rs = facade.getAllCheckoutRecords(CheckoutRecordEntry.class);
		try {
			while (rs.next()) {

				dates.add(new KeyValuePair<>(rs.getDate("checkoutDate").toLocalDate(), rs.getInt("quantity")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return dates;

	}
}
