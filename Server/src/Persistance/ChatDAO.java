package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.User;

import java.util.ArrayList;

/**
 * The interface Chat dao.
 */
public interface ChatDAO {

    /**
     * Gets all xats.
     *
     * @param user1 the user 1
     * @param user2 the user 2
     * @return the all xats
     */
    ArrayList<ChatMessage> getAllXats(User user1, User user2);

    boolean addMessage(ChatMessage chatMessage);

    /**
     * Delete chat boolean.
     *
     * @param user1 the user 1
     * @param user2 the user 2
     * @return the boolean
     */
    boolean deleteChat(User user1, User user2);

}
