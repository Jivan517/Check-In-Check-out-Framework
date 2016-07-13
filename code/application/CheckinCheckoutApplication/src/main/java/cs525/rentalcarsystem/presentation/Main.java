package cs525.rentalcarsystem.presentation;

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
	private Stage primaryStage;
	private Stage rootStage = new Stage();
	private BorderPane rootLayout;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		/*CheckoutController checkout = new CheckoutController();
		checkout.start(primaryStage);*/
		ApplicationUserController appuser = new ApplicationUserController();
		appuser.start(primaryStage);
		/*CheckinFormController checkin = new CheckinFormController();
		checkin.start(primaryStage);*/
<<<<<<< HEAD
		ManageCustomerController customer = new ManageCustomerController();
		customer.start(primaryStage);
=======
		/*AddCustomerController customer = new AddCustomerController();
		customer.start(primaryStage);*/
>>>>>>> f9f11f0503429656f938931d7058b3e99931540d
	}

	public static void main(String[] args) {
		launch(args);
	}

}
