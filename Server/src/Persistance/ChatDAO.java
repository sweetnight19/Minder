package Persistance;

import Business.Entity.ChatMessage;

import java.util.ArrayList;

public interface ChatDAO {
    /**
     * Method to read all messages from two users.
     *
     * @param id1 The id of user 1 who wants the chat.
     * @param id2 The id of user 2 who wants the chat.
     *
     * @return A list of messages.
     */
    ArrayList<ChatMessage> getAllXats(int id1, int id2);

    /**
     * Method to add a message on Chat form the model by its id1 to id2.
     *
     * @param id1 The id of user 1 who send the message.
     * @param id2 The id of user 2 who receive the message.
     */
    void addMessage(int id1, int id2, String message);

    /**
     * Method to remove a Chat form the model by id1 & id2.
     *
     * @param id1 The id of user 1 to delete the chat.
     * @param id2 The id of user 2 to delete the chat.
     */
    void deleteChat(int id1, int id2);

    /*
     * Method to change a Peer form the model by id1 & id2.
     *
     * @param id1 The id of user 1 to change the status peer.
     * @param id2 The id of user 2 to change the status peer.
     */
    // void tooglePeer(int id1, int id2);
}
