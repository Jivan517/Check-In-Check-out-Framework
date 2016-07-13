package cs525.rentalcarsystem.presentation;

import cs525.rentalcarsystem.controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainForm extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		LoginController manageCustomer = new LoginController();
		manageCustomer.start(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
