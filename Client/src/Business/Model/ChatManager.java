package Business.Model;

import Business.Entity.ChatMessage;
import Business.Entity.Peer;
import Business.Entity.User;
import Persistance.ChatConnectionDAO;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Presentation.Controller.ChatController;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The type Chat manager.
 */
public class ChatManager {
    private final ConnectionDAO connectionDAO;
    private final ConfigurationDAO configurationDAO;
    private ArrayList<User> chatListUsers;
    private ArrayList<ChatMessage> chatMessages;
    private ChatConnectionDAO chatDAO;

    /**
     * Instantiates a new Chat manager.
     *
     * @param connectionDAO    the connection dao
     * @param configurationDAO the configuration dao
     */
    public ChatManager(ConnectionDAO connectionDAO, ConfigurationDAO configurationDAO) {
        this.connectionDAO = connectionDAO;
        this.configurationDAO = configurationDAO;
        chatListUsers = new ArrayList<>();
        chatMessages = new ArrayList<>();
    }

    /**
     * Get chat list.
     *
     * @return the array list
     */
    public ArrayList<User> getChatList() {
        chatListUsers = null;
        chatListUsers = this.connectionDAO.getChatList(GlobalUser.getInstance().getMyUser());
        return this.chatListUsers;
    }

    /**
     * Get image from friend.
     *
     * @param friend the friend
     * @return the buffered image
     */
    public BufferedImage getImageFromFriend(User friend) {
        return this.connectionDAO.readImage(friend);
    }

    /**
     * Insert new message.
     *
     * @param message the message
     * @param destiny the destiny
     * @return the boolean
     */
    public boolean insertNewMessage(String message, User destiny) {
        ChatMessage chatMessage = new ChatMessage(GlobalUser.getInstance().getMyUser().getId(), destiny.getId(), message);
        if (this.connectionDAO.insertNewMessage(chatMessage)) {
            chatMessages.add(chatMessage);
            return true;
        }
        return false;
    }

    /**
     * Get chat messages.
     *
     * @param destiny the destiny
     * @return the array list
     */
    public ArrayList<ChatMessage> getChatMessages(User destiny) {
        chatMessages = null;
        chatMessages = this.connectionDAO.getChatMessages(GlobalUser.getInstance().getMyUser(), destiny);
        return chatMessages;
    }

    public boolean deletePeer(int idSource, int idDestiny) {
        Peer peer = new Peer(idSource, idDestiny, true);
        return this.connectionDAO.deletePeer(peer);
    }

    /**
     * Launch chat thread.
     *
     * @param destiny    the destiny
     * @param controller the controller
     */
    public void launchChatThread(User destiny, ChatController controller) {
        chatDAO = new ChatConnectionDAO(configurationDAO, GlobalUser.getInstance().getMyUser(), destiny, controller);
        chatDAO.start();
    }

    /**
     * Close socket and thread.
     */
    public void closeSocketAndThread() {
        chatDAO.setClosed();
    }
}
