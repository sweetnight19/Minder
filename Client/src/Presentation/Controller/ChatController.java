package Presentation.Controller;

import Presentation.View.ChatDirectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.View.ChatDirectView.BTN_SEND;


public class ChatController implements ActionListener {
    private ChatDirectView chatDirectView;

    public ChatController(ChatDirectView chatDirectView){
        this.chatDirectView = chatDirectView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(BTN_SEND)){
            String message = this.chatDirectView.getTextFieldMessage();
            if(!message.isEmpty()){
                this.chatDirectView.addOwnMessage(message);
                this.chatDirectView.setTextFieldHint();
            }
        }
    }
}
