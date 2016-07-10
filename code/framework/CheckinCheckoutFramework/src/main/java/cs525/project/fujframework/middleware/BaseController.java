/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

import java.util.Stack;

/**
 * base controller that invokes different commands
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class BaseController {

	/**
	 * stack for the recently executed operations which can be used during roll
	 * back process
	 */
	private Stack<Command> commands = new Stack<Command>();
}
