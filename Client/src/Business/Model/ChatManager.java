package Business.Model;

import Business.Entity.ChatMessage;
import Business.Entity.User;
import Persistance.ChatConnectionDAO;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ChatManager implements NewMessageListener {
    private ConnectionDAO connectionDAO;
    private ConfigurationDAO configurationDAO;
    private ArrayList<User> chatListUsers;
    private ArrayList<ChatMessage> chatMessages;
    private ChatConnectionDAO chatDAO;

    public ChatManager(ConnectionDAO connectionDAO, ConfigurationDAO configurationDAO) {
        this.connectionDAO = connectionDAO;
        this.configurationDAO = configurationDAO;
        chatListUsers = new ArrayList<>();
        chatMessages = new ArrayList<>();
    }

    public ChatManager(){}

    public ArrayList<User> getChatList(){
        chatListUsers = null;
        chatListUsers = this.connectionDAO.getChatList(GlobalUser.getInstance().getMyUser());
        return this.chatListUsers;
    }

    public BufferedImage getImageFromFriend(User friend){
        BufferedImage image = this.connectionDAO.readImage(friend);
        return image;
    }

    public boolean insertNewMessage(String message, User destiny){
        ChatMessage chatMessage = new ChatMessage(GlobalUser.getInstance().getMyUser().getId(), destiny.getId(), message);
        if(this.connectionDAO.insertNewMessage(chatMessage)){
            chatMessages.add(chatMessage);
            return true;
        }
        return false;
    }

    public ArrayList<ChatMessage> getChatMessages(User destiny){
        chatMessages = null;
        chatMessages = this.connectionDAO.getChatMessages(GlobalUser.getInstance().getMyUser(), destiny);
        return  chatMessages;
    }

    public void launchChatThread(User destiny){
        chatDAO = new ChatConnectionDAO(configurationDAO, GlobalUser.getInstance().getMyUser(), destiny);
        chatDAO.start();
    }

    public void closeSocketAndThread(){
        try {
            chatDAO.setClosed();
            chatDAO.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void newMessage(ChatMessage message) {
        chatMessages.add(message);
    }
}
