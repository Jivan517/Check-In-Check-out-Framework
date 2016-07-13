package cs525.rentalcarsystem.presentation;

import cs525.rentalcarsystem.controller.CheckoutController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		CheckoutController checkout = new CheckoutController();
		checkout.start(primaryStage);
		/*
		 * CheckinFormController checkin = new CheckinFormController();
		 * checkin.start(primaryStage);
		 */
		// AddCustomerController customer = new AddCustomerController();
		// customer.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
