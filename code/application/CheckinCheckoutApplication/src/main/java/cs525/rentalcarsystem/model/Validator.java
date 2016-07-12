/**
 * Copyright 2016 the original author or authors.
 * 
 * Licensed under the MIT License (MIT);
 */
package cs525.rentalcarsystem.model;

import javafx.scene.control.TextField;

/**
 * @author paudelumesh
 *
 */
public class Validator {
	public static void validateNumeric(TextField field) throws FormException {
		if (!field.getText().toString().matches("^\\d+$"))
			throw new FormException(field.getId() + " field must be numeric");
	}

	public static void validateEmptiness(TextField field) throws FormException {
		if (field.getText().toString().isEmpty()) {
			throw new FormException("One or more fields are empty");
		}
	}
}
