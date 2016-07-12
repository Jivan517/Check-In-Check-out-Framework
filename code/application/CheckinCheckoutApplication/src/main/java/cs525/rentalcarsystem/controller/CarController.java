package cs525.rentalcarsystem.controller;

import cs525.project.fujframework.core.Product;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.rentalcarsystem.backend.Car;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CarController extends Application {

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

	@Override
	public void start(Stage primaryStage) throws Exception {

	}

	public CarController() {
		this.command = new CommandManagerImpl();
	}

	@FXML
	protected void btnSaveCarAction(ActionEvent event) throws Exception {

		String name = nameTxt.getText(), description = descriptionTxt.getText(), model = modelTxt.getText(),
				plate = plateTxt.getText(), color = colorTxt.getText(), make = makeTxt.getText();
		int year = Integer.parseInt(yearTxt.getText()), quantity = Integer.parseInt(quantityTxt.getText()),
				mileage = Integer.parseInt(mileageTxt.getText());

		double rentalFee = Double.parseDouble(rentalFeeTxt.getText()),
				overdueFine = Double.parseDouble(overdueFineTxt.getText());

		Product product = new Car(name, model, description, plate, color, make, quantity, year, overdueFine, rentalFee,
				mileage);
		command.saveProduct(product);
	}

	/*
	 * @FXML protected void btnCancelAction(ActionEvent event) throws Exception
	 * {
	 * 
	 * }
	 */
}
