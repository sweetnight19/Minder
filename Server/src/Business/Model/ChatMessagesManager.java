package Business.Model;

import Business.Entity.ChatMessage;

import java.util.ArrayList;

/**
 * The type Chat messages manager.
 */
public class ChatMessagesManager {

    /**
     * The constant messages.
     */
    public static ArrayList<ChatMessage> messages = new ArrayList<>();

    /**
     * Add message.
     *
     * @param message the message
     */
    public static void addMessage(ChatMessage message){
        messages.add(message);
    }

    /**
     * Get messages array list.
     *
     * @return the array list
     */
    public static ArrayList<ChatMessage> getMessages(){
        return messages;
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public static int getSize(){
        return messages.size();
    }
}
