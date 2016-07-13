package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.Product;
import cs525.project.fujframework.core.ProductFacade;
import cs525.project.fujframework.core.ProductFacadeImpl;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.project.fujframework.middleware.ConsoleLogger;
import cs525.project.fujframework.middleware.Logger;
import cs525.project.fujframework.middleware.LoggerImpl;
import cs525.rentalcarsystem.controller.utils.DialogHelper;
import cs525.rentalcarsystem.controller.utils.Validator;
import cs525.rentalcarsystem.model.Car;
import cs525.rentalcarsystem.model.FormException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CarController extends Application implements Initializable {

	@FXML
	private TextField nameTxt;
	@FXML
	private TextField descriptionTxt;
	@FXML
	private TextField modelTxt;
	@FXML
	private TextField yearTxt;
	@FXML
	private TextField colorTxt;
	@FXML
	private TextField makeTxt;
	@FXML
	private TextField mileageTxt;
	@FXML
	private TextField rentalFeeTxt;
	@FXML
	private TextField overdueFineTxt;
	@FXML
	private TextField quantityTxt;
	@FXML
	private TextField plateTxt;

	private CommandManager command;
	public static int carId = 0;

	@FXML
	private Label lblCarId;

	private Logger logger;

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("stage " + carId);
		Parent root = FXMLLoader
				.load(getClass().getClassLoader().getResource("cs525/rentalcarsystem/presentation/CarForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("cs525/rentalcarsystem/presentation/rentalcarsystem.css");

		primaryStage.setResizable(true);
		primaryStage.setTitle("Add/Update Car Information");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public CarController() {
		System.out.println("without param: " + carId);
		this.command = new CommandManagerImpl();
		logger = new ConsoleLogger(new LoggerImpl());
	}

	public CarController(int carIdVal) {
		System.out.println("with param");
		carId = carIdVal;
		System.out.println("carID: " + carId);
		this.command = new CommandManagerImpl();
		logger = new ConsoleLogger(new LoggerImpl());
	}

	@FXML
	protected void btnSaveCarAction(ActionEvent event) throws Exception {

		try {

			Validator.validateEmptiness(nameTxt);
			Validator.validateEmptiness(modelTxt);
			Validator.validateEmptiness(plateTxt);
			Validator.validateEmptiness(makeTxt);
			Validator.validateNumeric(quantityTxt);
			Validator.validateNumeric(yearTxt);
			Validator.validateNumeric(quantityTxt);
			if (!mileageTxt.getText().isEmpty())
				Validator.validateNumeric(mileageTxt);
			Validator.validateNumeric(rentalFeeTxt);
			Validator.validateNumeric(overdueFineTxt);

			String name = nameTxt.getText(), description = descriptionTxt.getText(), model = modelTxt.getText(),
					plate = plateTxt.getText(), color = colorTxt.getText(), make = makeTxt.getText();
			int year = Integer.parseInt(yearTxt.getText()), quantity = Integer.parseInt(quantityTxt.getText()),
					mileage = Integer.parseInt(mileageTxt.getText() == "" ? "0" : mileageTxt.getText());

			double rentalFee = Double.parseDouble(rentalFeeTxt.getText()),
					overdueFine = Double.parseDouble(overdueFineTxt.getText());

			Product product = new Car(name, model, description, plate, color, make, quantity, year, overdueFine,
					rentalFee, mileage);
			if (carId > 0)
				product.setProductId(carId);
			command.saveProduct(product);

			DialogHelper.toast("The car information saved successfully!", AlertType.INFORMATION);
		} catch (FormException ex) {
			DialogHelper.toast(ex.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	protected void btnCancelAction(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	private void initCarInfo() {

		ProductFacade facade = new ProductFacadeImpl();
		ResultSet rs = facade.getProductById(carId, Car.class);
		logger.debug((rs == null) + "");

		try {
			while (rs.next()) {

				nameTxt.setText(rs.getString("name"));
				descriptionTxt.setText(rs.getString("description"));
				modelTxt.setText(rs.getString("model"));
				colorTxt.setText(rs.getString("color"));
				yearTxt.setText("" + rs.getInt("releaseyear"));
				makeTxt.setText(rs.getString("make"));
				quantityTxt.setText("" + rs.getInt("quantity"));

				mileageTxt.setText("" + rs.getInt("mileage"));
				plateTxt.setText(rs.getString("plate"));
				rentalFeeTxt.setText("" + rs.getDouble("rentalFeePerDay"));
				overdueFineTxt.setText("" + rs.getDouble("overdueFinePerDay"));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (carId > 0)
			initCarInfo();
	}

}
