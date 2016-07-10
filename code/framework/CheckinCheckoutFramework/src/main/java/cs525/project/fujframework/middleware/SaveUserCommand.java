/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * concrete command for user save operation
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class SaveUserCommand implements Command {

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public boolean undo() {
		return false;
	}

}
