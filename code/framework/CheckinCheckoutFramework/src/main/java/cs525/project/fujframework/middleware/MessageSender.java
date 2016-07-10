/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

/**
 * MessageSender Interface is the Implementor of Bridge Pattern hierarchy
 * ('Bridge/Implementor' interface) Clients can implement this interface for
 * sending text/Email.... messages
 * 
 * @author Fish
 * 
 * @version 1.0.0
 *
 */

public interface MessageSender {
   /**
    * Method for sending message the implementation will be made by
    * application user
    * @param subject
    * @param body
    */
	public void sendMessage(String subject, String body);
}
