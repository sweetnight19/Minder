package Persistance;

import Business.Entity.ChatMessage;
import Business.Entity.Peer;
import Business.Entity.User;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface ConnectionDAO {

    //UserDAO
    boolean registerUser(User user);
    boolean validateLogin(User user);
    boolean checklogin(User user);
    boolean updateUser(User user);
    User readUser(User user);

    //PeerDAO
    boolean insertLike(Peer peer);
    boolean deletePeer(Peer peer);
    ArrayList<User> getRandomUsers(User user);

    //ChatDAO
    ArrayList<User> getChatList(User user);
    ArrayList<ChatMessage> getChatMessages(User source, User destiny);
    boolean insertNewMessage(ChatMessage message);

    //Images
    BufferedImage readImage(User user);
    boolean sendImage(User user);

    //Actions
    void disconnectFromServer();
}
