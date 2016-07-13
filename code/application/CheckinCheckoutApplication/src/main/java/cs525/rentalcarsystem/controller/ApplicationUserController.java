/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cs525.project.fujframework.core.SysUserFacade;
import cs525.project.fujframework.core.SysUserFacadeImpl;
import cs525.project.fujframework.middleware.CommandManager;
import cs525.project.fujframework.middleware.CommandManagerImpl;
import cs525.rentalcarsystem.controller.utils.Validator;
import cs525.rentalcarsystem.model.ApplicationUser;
import cs525.rentalcarsystem.model.FormException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author paudelumesh
 *
 */
public class ApplicationUserController extends Application implements Initializable {
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
	@FXML
	private Label successMsgLabel;
	@FXML
	private TableView<ApplicationUser> userTable;
	@FXML
	private TableColumn<ApplicationUser, String> userName;
	@FXML
	private TableColumn<ApplicationUser, String> name;
	@FXML
	private TableColumn<ApplicationUser, String> userEmail;

	private CommandManager command;
	private SysUserFacade facade;
	private int userId;

	/**
	 * 
	 */
	public ApplicationUserController() {
		command = new CommandManagerImpl();
		facade = new SysUserFacadeImpl();
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

		try {
			Validator.validateEmptiness(firstNameTxt);
			Validator.validateEmptiness(lastNameTxt);
			Validator.validateEmptiness(passwordTxt);
			Validator.validateEmptiness(confirmPasswordTxt);
			Validator.validateNumeric(phoneTxt);

			String firstName = firstNameTxt.getText().toString(), middleName = middleNameTxt.getText().toString(),
					lastName = lastNameTxt.getText().toString(), userName = usernameTxt.getText().toString(),
					password = passwordTxt.getText().toString(),
					confirmPassword = confirmPasswordTxt.getText().toString(), email = emailTxt.getText().toString(),
					phone = phoneTxt.getText().toString();

			if (password.equals(confirmPassword)) {
				ApplicationUser user = new ApplicationUser();
				if (userId > 0)
					user.setSysuserId(userId);
				user.setFirstName(firstName);
				user.setMiddleName(middleName);
				user.setLastName(lastName);
				user.setUserName(userName);
				user.setPassword(password);
				user.setEmail(email);
				user.setPhone(phone);

				if (command.saveSysUser(user)) {
					successMsgLabel.setText("User added/updated successfully !!!");
					successMsgLabel.getStyleClass().add("color-success");
					clearFields();
					updateUserTable();
				} else {
					successMsgLabel.setText("There is an error while adding/updating user !!!");
					successMsgLabel.getStyleClass().add("color-error");
					clearFields();

				}
			} else {
				successMsgLabel.setText("Password Mismatch !!!");
				successMsgLabel.getStyleClass().add("color-error");
			}
		} catch (FormException e) {
			successMsgLabel.setText(e.getMessage());
			successMsgLabel.getStyleClass().add("color-error");
		}

	}

	public void populateTable(ObservableList<ApplicationUser> users) {
		userName.setCellValueFactory(new PropertyValueFactory<ApplicationUser, String>("userName"));
		name.setCellValueFactory(new PropertyValueFactory<ApplicationUser, String>("firstName"));
		userEmail.setCellValueFactory(new PropertyValueFactory<ApplicationUser, String>("email"));
		userTable.setItems(users);
	}

	void clearFields() {
		firstNameTxt.setText("");
		middleNameTxt.setText("");
		lastNameTxt.setText("");
		usernameTxt.setText("");
		passwordTxt.setText("");
		confirmPasswordTxt.setText("");
		emailTxt.setText("");
		phoneTxt.setText("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		updateUserTable();
	}

	public void updateUserTable() {
		ObservableList<ApplicationUser> users = FXCollections.observableArrayList();
		try {
			ResultSet rs = facade.getAllUsers(ApplicationUser.class);
			while (rs.next()) {
				ApplicationUser user = new ApplicationUser();
				user.setSysuserId(rs.getInt("sysuserId"));
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setMiddleName(rs.getString("middleName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setIsAdmin(rs.getBoolean("isAdmin"));
				user.setPhone(rs.getString("phone"));

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		populateTable(users);
		userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				userId = newSelection.getSysuserId();
				usernameTxt.setText(newSelection.getUserName());
				firstNameTxt.setText(newSelection.getFirstName());
				middleNameTxt.setText(newSelection.getMiddleName());
				lastNameTxt.setText(newSelection.getLastName());
				emailTxt.setText(newSelection.getEmail());
				phoneTxt.setText(newSelection.getPhone());
			}
		});
	}

}
