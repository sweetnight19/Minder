package Business.Model;

import Business.Entity.ChatMessage;
import Business.Entity.User;
import Persistance.ConnectionDAO;

import java.util.ArrayList;

public class ChatManager {
    private ConnectionDAO connectionDAO;
    private ArrayList<User> chatListUsers;
    private ArrayList<ChatMessage> chatMessages;

    public ChatManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
        chatListUsers = new ArrayList<>();
        chatMessages = new ArrayList<>();
    }

    public ArrayList<User> getChatList(){
        chatListUsers = null;
        chatListUsers = this.connectionDAO.getChatList(Singleton.getInstance().getMyUser());
        return this.chatListUsers;
    }

    public boolean insertNewMessage(String message, User destiny){
        ChatMessage chatMessage = new ChatMessage(Singleton.getInstance().getMyUser().getId(), destiny.getId(), message);
        if(this.connectionDAO.insertNewMessage(chatMessage)){
            return true;
        }
        return false;
    }

    public ArrayList<ChatMessage> getChatMessages(User destiny){
        chatMessages = null;
        chatMessages = this.connectionDAO.getChatMessages(Singleton.getInstance().getMyUser(), destiny);
        return  chatMessages;
    }
}
