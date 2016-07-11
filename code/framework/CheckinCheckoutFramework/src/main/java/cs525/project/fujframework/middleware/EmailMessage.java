/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

/**
 * Refined Abstraction of Message Class
 * 
 * @author Fish
 * @version 1.0.0
 */
public class EmailMessage extends Message {
	/**
	 * EmailMessage constructor with @{MessageSender} as a parameter
	 * 
	 * @param messageSender
	 */
	public EmailMessage(MessageSender messageSender) {
		super(messageSender);
	}

	@Override
	public void send() {
		messageSender.sendMessage(body,person);

	}

}
