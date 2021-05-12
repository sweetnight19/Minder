package Presentation.Controller;

import Business.Entity.User;
import Business.Model.ChatManager;
import Presentation.View.ChatDirectView;
import Presentation.View.ChatListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.View.ChatDirectView.BTN_SEND;

public class ChatController implements ActionListener {
    private ChatDirectView chatDirectView;
    private ChatListView chatListView;
    private ChatManager chatManager;

    public ChatController(ChatDirectView chatDirectView, ChatListView chatListView, ChatManager chatManager){
        this.chatListView = chatListView;
        this.chatDirectView = chatDirectView;
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
}
