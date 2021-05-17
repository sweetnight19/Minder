package Presentation.Controller;

import Business.Entity.ChatMessage;
import Business.Entity.User;
import Business.Model.ChatManager;
import Business.Model.NewMessageListener;
import Presentation.View.ChatDirectView;
import Presentation.View.ChatListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static Presentation.View.ChatDirectView.BTN_SEND;

public class ChatController implements ActionListener, NewMessageListener, MouseListener {
    private ChatDirectView chatDirectView;
    private ChatListView chatListView;
    private ChatManager chatManager;

    public ChatController(ChatListView chatListView, ChatManager chatManager){
        this.chatListView = chatListView;
        this.chatManager = chatManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(BTN_SEND)){
            String message = this.chatDirectView.getTextFieldMessage();
            if(!message.isEmpty()){
                User destiny = null;    //falta implementar
                if(this.chatManager.insertNewMessage(message, destiny)){
                    this.chatDirectView.addOwnMessage(message);
                    this.chatDirectView.setTextFieldHint();
                }
            }
        }
    }

    public void loadListChat(){
        this.chatListView.removeChats();
        ArrayList<User> users = new ArrayList<>();
        users = this.chatManager.getChatList();
        for (int i = 0; i < users.size(); i++) {
            ArrayList<User> finalUsers = users;
            int finalI = i;
            SwingUtilities.invokeLater(() -> {
                this.chatListView.addUserChat(finalUsers.get(finalI), this.chatManager.getImageFromFriend(finalUsers.get(finalI)) );
            });
        }
    }

    @Override
    public void newMessage(ChatMessage message) {
        this.chatDirectView.addFriendMessage(message.getMessage());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //aqui
        System.out.println(e);
        System.out.println("hem clicat");
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
