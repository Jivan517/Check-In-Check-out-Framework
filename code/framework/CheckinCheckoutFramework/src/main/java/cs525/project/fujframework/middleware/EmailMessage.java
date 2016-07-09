package cs525.project.fujframework.middleware;
/**
 * Refined Abstraction of Message Class 
 * @author Fish
 *
 */
public class EmailMessage extends Message {

	public EmailMessage(MessageSender messageSender) {
		super(messageSender);		
	}

	@Override
	public void send() {
		messageSender.sendMessage(subject,body);

	}

}
