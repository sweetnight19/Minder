package Business.Model;

import Business.Entity.ChatMessage;

public interface NewMessageListener {
    public void newMessage(ChatMessage message);
}
