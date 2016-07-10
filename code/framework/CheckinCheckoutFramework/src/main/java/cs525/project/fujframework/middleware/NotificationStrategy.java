/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 * 
 */

package cs525.project.fujframework.middleware;

/**
 * provides an interface for sending notification after the check out process to
 * a customer
 * 
 * @author Jivan Nepali
 * 
 * @version 1.0.0
 *
 */
public interface NotificationStrategy {

	/**
	 * sends notification to the customer - which can be an Email or SMS message
	 * 
	 * @param person
	 *            customer for which the notification has to be sent
	 */
	void sendNotification(Person person);
}
