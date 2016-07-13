package cs525.rentalcarsystem.presentation;

import cs525.rentalcarsystem.controller.CheckinFormController;
import cs525.rentalcarsystem.controller.CheckoutController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainJivan extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		CheckoutController checkout = new CheckoutController();
		checkout.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
