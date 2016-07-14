/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.controller.utils;

import cs525.rentalcarsystem.model.FormException;
import javafx.scene.control.TextField;

/**
 * @author paudelumesh
 *
 */
public class Validator {
	public static void validateNumeric(TextField field) throws FormException {

		// ^[1-9]\d*(\.\d+)?$
		if (!field.getText().toString().matches("^[1-9]\\d*(\\.\\d+)?$"))
			throw new FormException(field.getId() + " field must be numeric");
	}

	public static void validateEmptiness(TextField field) throws FormException {
		if (field.getText().toString().isEmpty()) {
			throw new FormException("One or more fields are empty");
		}
	}

	public static void validateEmail(TextField field) throws FormException {
		if (!field.getText().toString().matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
			throw new FormException(field.getId() + " field must have valid email");
	}
}
