package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.Peer;
import Business.Entity.User;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The interface Connection dao.
 */
public interface ConnectionDAO {

    /**
     * Register user boolean.
     *
     * @param user the user
     * @return the boolean
     */
//UserDAO
    boolean registerUser(User user);

    /**
     * Validate login boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean validateLogin(User user);

    /**
     * Checklogin int.
     *
     * @param user the user
     * @return the int
     */
    int checklogin(User user);

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean updateUser(User user);

    /**
     * Read user user.
     *
     * @param user the user
     * @return the user
     */
    User readUser(User user);

    /**
     * Delete user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean deleteUser(User user);

    /**
     * Count premium int.
     *
     * @param user the user
     * @return the int
     */
    int countPremium(User user);

    /**
     * Insert like boolean.
     *
     * @param peer the peer
     * @return the boolean
     */
//PeerDAO
    boolean insertLike(Peer peer);

    /**
     * Delete peer boolean.
     *
     * @param peer the peer
     * @return the boolean
     */
    boolean deletePeer(Peer peer);

    /**
     * Gets random users.
     *
     * @param user the user
     * @return the random users
     */
    ArrayList<User> getRandomUsers(User user);

    /**
     * Gets chat list.
     *
     * @param user the user
     * @return the chat list
     */
//ChatDAO
    ArrayList<User> getChatList(User user);

    /**
     * Gets chat messages.
     *
     * @param source  the source
     * @param destiny the destiny
     * @return the chat messages
     */
    ArrayList<ChatMessage> getChatMessages(User source, User destiny);

    /**
     * Insert new message boolean.
     *
     * @param message the message
     * @return the boolean
     */
    boolean insertNewMessage(ChatMessage message);

    /**
     * Read image buffered image.
     *
     * @param user the user
     * @return the buffered image
     */
//Images
    BufferedImage readImage(User user);

    /**
     * Send image boolean.
     *
     * @param user   the user
     * @param image2 the image 2
     * @return the boolean
     */
    boolean sendImage(User user, BufferedImage image2);

    /**
     * Disconnect from server.
     */
//Actions
    void disconnectFromServer();
}
