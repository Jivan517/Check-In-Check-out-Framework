package cs525.rentalcarsystem.presentation;

import cs525.rentalcarsystem.controller.CheckoutController;
import javafx.application.Application;
import cs525.rentalcarsystem.controller.AddCustomerController;
import cs525.rentalcarsystem.controller.ApplicationUserController;
import cs525.rentalcarsystem.controller.CarController;
import cs525.rentalcarsystem.controller.CheckinFormController;
import cs525.rentalcarsystem.controller.CheckoutController;

import cs525.rentalcarsystem.controller.ManageCustomerController;
import cs525.rentalcarsystem.model.ApplicationUser;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
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
//		CheckoutController checkout = new CheckoutController();
//		checkout.start(primaryStage);
//		ApplicationUserController appuser = new ApplicationUserController();
//		appuser.start(primaryStage);
		/*CheckinFormController checkin = new CheckinFormController();
		checkin.start(primaryStage);*/
//		ManageCustomerController customer = new ManageCustomerController();
//		customer.start(primaryStage);
		/*AddCustomerController customer = new AddCustomerController();
		customer.start(primaryStage);*/
	}

	public static void main(String[] args) {
		launch(args);
	}

}
