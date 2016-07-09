package cs525.project.fujframework.middleware;
/**
 * 
 * @author Fish
 *
 */
public class EmailMessage extends Message {

	public EmailMessage(MessageSender messageSender) {
		super(messageSender);		
	}

	@Override
	public void send() {
		messageSender.sendMessage();

	}

}
