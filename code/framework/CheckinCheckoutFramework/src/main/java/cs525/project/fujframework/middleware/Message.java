package cs525.project.fujframework.middleware;
/**
 *  Abstract Message class for sending Messages 
 *  It could be text/Email Message.
 *  It's implemented using Bridge Pattern
 *  which has Instance of MessageSender as Implementer!
 * 
 * @author Fish
 * 
 * @since 1.0.0
 *
 */
public abstract class Message {
	
	MessageSender messageSender;
	public Message(MessageSender messageSender){
		this.messageSender = messageSender;
		
	}
	
	public abstract void send();

}
