package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.User;

import java.util.ArrayList;

/**
 * The interface Chat dao.
 */
public interface ChatDAO {

    /**
     * Gets all xats messages that have been texted between the two users recieved on parameters.
     *
     * @param user1 the user 1
     * @param user2 the user 2
     * @return the all xats
     */
    ArrayList<ChatMessage> getAllXats(User user1, User user2);

    /**
     * Function that adds a new message into the database, the message is the object recieved by parameters
     * that has all the information related to the message
     *
     * @param chatMessage the chat message
     * @return boolean
     */
    boolean addMessage(ChatMessage chatMessage);

    /**
     * Delete all the chat messages between two users.
     *
     * @param user1 the user 1
     * @param user2 the user 2
     * @return the boolean
     */
    boolean deleteChat(User user1, User user2);

}
