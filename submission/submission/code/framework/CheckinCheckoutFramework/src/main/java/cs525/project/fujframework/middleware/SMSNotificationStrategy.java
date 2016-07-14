/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides a concrete implementation for SMS notification strategy
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class SMSNotificationStrategy implements NotificationStrategy {

	private Message message;

	public SMSNotificationStrategy() {
		this.message = new TextMessage(new TextMessageSender());
	}

	@Override
	public void sendNotification(String message, Person person) {

		this.message.setBody(message);
		this.message.setPerson(person);
		this.message.send();
	}

}
