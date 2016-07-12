package cs525.rentalcarsystem.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		

	}

	

	@FXML
	protected void btnSaveCarAction(ActionEvent event) throws Exception {

	}

	/*
	 * @FXML protected void btnCancelAction(ActionEvent event) throws Exception
	 * {
	 * 
	 * }
	 */
}
