package Business.Model;

import Business.Entity.ChatMessage;

/**
 * The interface New message listener.
 */
public interface NewMessageListener {
    /**
     * New message.
     *
     * @param message the message
     */
    public void newMessage(ChatMessage message);
}
