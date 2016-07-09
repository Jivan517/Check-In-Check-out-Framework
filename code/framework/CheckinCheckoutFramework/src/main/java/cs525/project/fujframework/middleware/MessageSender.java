/**
 * 
 */
package cs525.project.fujframework.middleware;

/**
 * MessageSender Interface is the Implementor of Bridge Pattern hierarchy
 * Clients can implement this interface for sending text/Email.... messages
 * 
 * @author Fish
 * 
 * @since 1.0.0
 *
 */
public interface MessageSender {
 
	public void sendMessage();
}