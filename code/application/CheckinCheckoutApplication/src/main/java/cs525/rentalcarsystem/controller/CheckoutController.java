package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.CustomerFacade;
import cs525.project.fujframework.core.CustomerFacadeImpl;
import cs525.project.fujframework.core.ProductFacade;
import cs525.project.fujframework.core.ProductFacadeImpl;
import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.middleware.Logger;
import cs525.project.fujframework.middleware.LoggerImpl;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
import cs525.rentalcarsystem.model.AppCustomer;
import cs525.rentalcarsystem.model.Car;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CheckoutController extends Application implements Initializable {

	@FXML
	private ComboBox<String> customerListComboBox;
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

	ObservableList<String> customerListObservable = FXCollections.observableArrayList();

	public CheckoutController() {
		logger = new ConsoleLogger(new LoggerImpl());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ProductFacade facade = new ProductFacadeImpl();

		ResultSet resultSet = facade.getAllProduct(Car.class);
		try {
			while (resultSet.next()) {
				Car car = new Car();
				car.setProductId(resultSet.getInt("productId"));
				car.setProductName(resultSet.getString("name"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("releaseYear"));
				car.setRentalFeePerDay(resultSet.getDouble("rentalFeePerDay"));
				car.setQuantity(resultSet.getInt("quantity"));

				carListObservable.add(car);

				System.out.println("INsdie");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		populateTable();
	};

	private void populateTable() {

		name.setCellValueFactory(new PropertyValueFactory<Car, String>("productName"));
		model.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
		year.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
		fee.setCellValueFactory(new PropertyValueFactory<Car, Double>("rentalFeePerDay"));
		quantity.setCellValueFactory(new PropertyValueFactory<Car, Integer>("quantity"));

		carList.setItems(carListObservable);

		populateComboBox();
	}

	private void populateComboBox() {

		CustomerFacade facade = new CustomerFacadeImpl();
		ResultSet rs = facade.getAllCustomers(AppCustomer.class);

		try {
			while (rs.next()) {

				int id = rs.getInt("customerId");
				String firstName = rs.getString("firstName");
				String middleName = rs.getString("middleName");
				String lName = rs.getString("lastName");

				String content = firstName + " " + middleName + " " + lName + "[" + id + "]";

				customerListObservable.add(content);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		this.customerListComboBox.setPromptText("--Choose");
		this.customerListComboBox.setItems(customerListObservable);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader
				.load(getClass().getClassLoader().getResource("cs525/rentalcarsystem/presentation/CheckoutForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("cs525/rentalcarsystem/presentation/rentalcarsystem.css");

		primaryStage.setResizable(true);
		primaryStage.setTitle("Checkout Form - Car List");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	protected void btnCancelAction(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	protected void btnUpdateAction(ActionEvent event) throws Exception {

		ObservableList<Car> cars = carList.getSelectionModel().getSelectedItems();
		if (cars.size() > 1) {
			DialogHelper.toast("Please, select only one record!", AlertType.WARNING);
			return;
		}
		if (cars.size() < 1) {
			DialogHelper.toast("Please, select a record!", AlertType.WARNING);
			return;
		}

		int carId = cars.get(0).getProductId();
		System.out.println("carID: " + carId);
		CarController carController = new CarController(carId);
		Stage stage = new Stage();
		carController.start(stage);

	}

}
