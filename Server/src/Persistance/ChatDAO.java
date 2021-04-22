package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.User;

import java.util.ArrayList;

public interface ChatDAO {
    /**
     * Method to read all messages from two users.
     *
     * @param user1 The user 1 who wants the chat.
     * @param user2 The user 2 who wants the chat.
     *
     * @return A list of messages.
     */
    ArrayList<ChatMessage> getAllXats(User user1, User user2);

    /**
     * Method to add a message on Chat form the model by ChatMessage Object.
     *
     * @param chatMessage Object type chatMessage to add to bbdd.
     */
    boolean addMessage(ChatMessage chatMessage);

    /**
     * Method to remove a Chat form the model.
     *
     * @param user1 The user 1 who wants the chat.
     * @param user2 The user 2 who wants the chat.
     */
    boolean deleteChat(User user1, User user2);

    /*
     * Method to change a Peer form the model by id1 & id2.
     *
     * @param id1 The id of user 1 to change the status peer.
     * @param id2 The id of user 2 to change the status peer.
     */
    // void tooglePeer(int id1, int id2);
}
