package cs525.project.fujframework.middleware;
/**
 * 
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
		messageSender.sendMessage();

	}

}
