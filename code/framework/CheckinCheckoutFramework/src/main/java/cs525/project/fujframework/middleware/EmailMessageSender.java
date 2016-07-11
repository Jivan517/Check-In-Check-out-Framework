/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the MIT License (MIT);
 */
package cs525.project.fujframework.middleware;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cs525.project.fujframework.utils.ConfigProperties;
import cs525.project.fujframework.utils.ConfigPropertiesImpl;

/**
 * @author Fish
 *
 */
public class EmailMessageSender implements MessageSender {

	private ConfigProperties config;

	public EmailMessageSender() {
		this.config = new ConfigPropertiesImpl("mail.properties");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.middleware.MessageSender#sendMessage(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void sendMessage(String body, Person person) {
		final String username = config.readProperty("username");
		final String password = config.readProperty("password");
		final String to = person.getEmail();

		Properties props = new Properties();
		props.put(config.readProperty("user"), username);
		props.put(config.readProperty("auth"), "true");
		props.put(config.readProperty("starttls"), "true");
		props.put(config.readProperty("portname"), config.readProperty("port"));
		props.put(config.readProperty("enablessl"), "true");
		props.put(config.readProperty("hostname"), config.readProperty("host"));

		props.setProperty(config.readProperty("socketfactoryname"), config.readProperty("socketfactory"));
		props.setProperty(config.readProperty("fallback"), "false");
		props.setProperty(config.readProperty("smtpportname"), config.readProperty("smtpport"));
		props.setProperty(config.readProperty("socketfacprtname"), config.readProperty("socketfacport"));

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Item(s) Checkout Notification");
			message.setText(body);

			Transport.send(message);

			System.out.println("Email Successfully Sent!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
