package cs525.rentalcarsystem.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

		Parent root = FXMLLoader.load(getClass().getResource("CarForm.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("ApplicationUserForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("cs525/rentalcarsystem/presentation/rentalcarsystem.css");

		primaryStage.setResizable(false);
		primaryStage.setTitle("Checkout Form - Car List");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
