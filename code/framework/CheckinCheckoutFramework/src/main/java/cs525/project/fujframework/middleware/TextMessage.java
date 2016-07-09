package cs525.project.fujframework.middleware;
/**
 * Refined Abstraction of Message Class
 * @author Fish
 * 
 * @since 1.0.0
 *
 */
public class TextMessage extends Message {

	public TextMessage(MessageSender messageSender) {
		super(messageSender);
	}

	@Override
	public void send() {
		messageSender.sendMessage(subject,body);

	}

}
