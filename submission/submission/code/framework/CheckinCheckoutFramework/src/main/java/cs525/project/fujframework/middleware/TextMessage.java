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
 * 
 * @version 1.0.0
 *
 */
public class TextMessage extends Message {
	/**
	 * @{TextMessage} constructor which is implementation of
	 *                abstraction @{Message}
	 * @param messageSender
	 */
	public TextMessage(MessageSender messageSender) {
		super(messageSender);
	}

	@Override
	public void send() {
		messageSender.sendMessage(body, person);

	}

}
