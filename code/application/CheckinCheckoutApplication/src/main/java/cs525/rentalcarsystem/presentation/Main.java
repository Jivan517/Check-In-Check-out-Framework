package cs525.rentalcarsystem.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
		this.primaryStage = primaryStage;
		this.rootStage = primaryStage;
		this.primaryStage.setTitle("Rental System");
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("ApplicationUserForm.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
