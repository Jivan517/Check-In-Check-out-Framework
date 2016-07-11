/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

/**
 * 
 * 
 * Abstraction Message class for sending Messages It could be text/Email
 * Message. It's implemented using Bridge Pattern which has Instance of
 * MessageSender as Implementer!
 * 
 * @author Fish
 * 
 * @version 1.0.0
 *
 */
public abstract class Message {

	MessageSender messageSender;
	String body;
	Person person;

	/**
	 * @{Message} constructor with @{MessageSender} as a parameter
	 * @param messageSender
	 */
	public Message(MessageSender messageSender) {
		this.messageSender = messageSender;

	}

	/**
	 * Setter for message Person which is set by user application
	 * 
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Setter for message body which will be set by user application
	 * 
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * This method sends the message based on user preference of concrete
	 * message sender
	 */
	public abstract void send();

}
