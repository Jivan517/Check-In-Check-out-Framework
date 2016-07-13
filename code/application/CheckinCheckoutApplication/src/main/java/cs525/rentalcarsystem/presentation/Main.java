package cs525.rentalcarsystem.presentation;

import cs525.rentalcarsystem.controller.AddCustomerController;
import cs525.rentalcarsystem.controller.ManageCustomerController;
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
//		Parent root = FXMLLoader.load(getClass().getResource("ManageCustomerForm.fxml"));
//		Scene scene = new Scene(root);
//
//		primaryStage.setResizable(false);
//		primaryStage.setTitle("Checkout Form - Car List");
//		primaryStage.setScene(scene);
//		primaryStage.show();
		// CarController car = new CarController();
		// car.start(primaryStage);

		ManageCustomerController manageCust = new ManageCustomerController();
		manageCust.start(primaryStage);

		/*
		 * Parent root =
		 * FXMLLoader.load(getClass().getResource("ApplicationUserForm.fxml"));
		 * Scene scene = new Scene(root); scene.getStylesheets().add(
		 * "cs525/rentalcarsystem/presentation/rentalcarsystem.css");
		 * 
		 * primaryStage.setResizable(true); primaryStage.setTitle(
		 * "Checkout Form - Car List"); primaryStage.setScene(scene);
		 * primaryStage.show();
		 */

/*
		CheckoutController checkout = new CheckoutController();
		checkout.start(primaryStage);*/
	}

	public static void main(String[] args) {
		launch(args);
	}

}
