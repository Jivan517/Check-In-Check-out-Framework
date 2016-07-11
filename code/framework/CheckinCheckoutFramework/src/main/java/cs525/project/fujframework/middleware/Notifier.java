/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides context class for the notification strategy
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public class Notifier {

	private NotificationStrategy notificationStrategy;

	/**
	 * constructor
	 * 
	 * @param notificationStrategy
	 *            strategy to be selected for the notification to user
	 */
	public Notifier(NotificationStrategy notificationStrategy) {
		this.notificationStrategy = notificationStrategy;
	}

	/**
	 * notifies a customer with notification message about the checkout details
	 * and due date for checking back in
	 * 
	 * @param person
	 *            customer for which the notification needs to be sent
	 */
	public void notifyPerson(String message, Person person) {
		notificationStrategy.sendNotification(message, person);
	}
}
