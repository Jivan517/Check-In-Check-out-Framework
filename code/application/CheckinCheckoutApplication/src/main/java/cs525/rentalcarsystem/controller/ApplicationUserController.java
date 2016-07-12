/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.rentalcarsystem.backend.ApplicationUser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author paudelumesh
 *
 */
public class ApplicationUserController extends Application {
	@FXML
	private TextField firstNameTxt;
	@FXML
	private TextField middleNameTxt;
	@FXML
	private TextField lastNameTxt;
	@FXML
	private TextField usernameTxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private PasswordField confirmPasswordTxt;
	@FXML
	private TextField emailTxt;
	@FXML
	private TextField phoneTxt;

	private CommandManager command;

	/**
	 * 
	 */
	public ApplicationUserController() {
		command = new CommandManagerImpl();
	}

	@FXML

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	@FXML
	void onSaveBtnClicked() {
		String firstName = firstNameTxt.getText().toString(), middleName = middleNameTxt.getText().toString(),
				lastName = lastNameTxt.getText().toString(), userName = usernameTxt.getText().toString(),
				password = passwordTxt.getText().toString(), confirmPassword = confirmPasswordTxt.getText().toString(),
				email = emailTxt.getText().toString(), phone = phoneTxt.getText().toString();

		if (password.equals(confirmPassword)) {
			ApplicationUser user = new ApplicationUser();
			user.setFirstName(firstName);
			user.setMiddleName(middleName);
			user.setLastName(lastName);
			user.setUserName(userName);
			user.setPassword(password);
			user.setEmail(email);
			user.setPhoneNo(phone);

			command.saveSysUser(user);
		}

	}

}
