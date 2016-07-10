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
 * @since 1.0.0
 *
 */
public abstract class Message {

	MessageSender messageSender;
	String subject, body;

	public Message(MessageSender messageSender) {
		this.messageSender = messageSender;

	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public abstract void send();

}
