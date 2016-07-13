/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import cs525.project.fujframework.core.SysUserFacade;
import cs525.project.fujframework.core.SysUserFacadeImpl;
import cs525.project.fujframework.utils.BusinessConstants;
import cs525.project.fujframework.utils.SessionCache;
import cs525.rentalcarsystem.model.ApplicationUser;
import cs525.rentalcarsystem.presentation.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class used to authenticate users based on their appropriate user name
 * and password
 * 
 * @author Fish
 *
 */
public class LoginController extends Application {

	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Text txtErrorMessage;
	private Stage primaryStage;

	public LoginController() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Login - Car Rental System [V1.0.0]");
			primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
			AnchorPane page = (AnchorPane) loader.load();
			Scene scene = new Scene(page, 400, 400);
			Stage ps = new Stage();
			ps.setScene(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * This method authenticates user if the user name and password are correct
	 * if not it displays appropriate message
	 * 
	 * @throws Exception
	 */
	@FXML
	public void login(ActionEvent event) throws Exception {
		if (!txtUserName.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()) {
			String userName = txtUserName.getText();
			String password = txtPassword.getText();
			ApplicationUser user = authenticateUser(userName, password);
			if (user != null) {
				SessionCache session = SessionCache.getInstance();
				if (user.isAdmin())
					session.add(BusinessConstants.ADMIN, BusinessConstants.ADMIN);
				else
					session.add(BusinessConstants.STAFF, BusinessConstants.STAFF);

				((Node) (event.getSource())).getScene().getWindow().hide();
				ManageCustomerController manageCustomer = new ManageCustomerController();
				Stage stage = new Stage();
				manageCustomer.start(stage);
			} else {
				txtErrorMessage.setFill(Color.RED);
				txtErrorMessage.setText("Invalid User Name and Password!");
			}

		} else {
			txtErrorMessage.setFill(Color.RED);
			txtErrorMessage.setText("Please specify User Name and Password!");
		}

	}

	private ApplicationUser authenticateUser(String userName, String password) throws SQLException {
		SysUserFacade userFacade = new SysUserFacadeImpl();
		ResultSet result = userFacade.getUserByUserNameAndPassword(userName, password, ApplicationUser.class);
		ApplicationUser user = null;
		while (result.next()) {
			user = new ApplicationUser();
			user.setIsAdmin(result.getBoolean("isAdmin"));
			user.setFirstName(result.getString("firstName"));
			user.setLastName(result.getString("lastName"));

		}
		return user;
	}

}
