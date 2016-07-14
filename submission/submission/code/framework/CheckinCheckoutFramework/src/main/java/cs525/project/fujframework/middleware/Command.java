/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides a high level interface for command(s)
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public interface Command {

	/**
	 * executes the command invoking an appropriate operation from the receiver
	 * 
	 * @return boolean
	 */
	boolean execute();

	/**
	 * roll-backs the recently executed operation
	 * 
	 * @return boolean
	 */
	boolean undo();
}
