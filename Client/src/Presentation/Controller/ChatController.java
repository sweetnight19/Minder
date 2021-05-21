package Presentation.Controller;

import Business.Entity.ChatMessage;
import Business.Entity.User;
import Business.Model.ChatManager;
import Business.Model.GlobalUser;
import Business.Model.NewMessageListener;
import Presentation.View.ChatDirectView;
import Presentation.View.ChatListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static Presentation.View.ChatDirectView.BTN_DISLIKE;
import static Presentation.View.ChatDirectView.BTN_SEND;

public class ChatController implements ActionListener, NewMessageListener, MouseListener, WindowListener {
    private ChatDirectView chatDirectView;
    private ChatListView chatListView;
    private ChatManager chatManager;
    private User destiny;
    private ArrayList<User> users;

    public ChatController(ChatListView chatListView, ChatManager chatManager){
        this.chatListView = chatListView;
        this.chatManager = chatManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case BTN_SEND:
                String message = this.chatDirectView.getTextFieldMessage();
                if (!message.isEmpty()) {
                    if (this.chatManager.insertNewMessage(message, destiny)) {
                        this.chatDirectView.addOwnMessage(message);
                        this.chatDirectView.setTextFieldHint();
                    }
                }
                break;
            case BTN_DISLIKE:
                if(this.chatManager.deletePeer(GlobalUser.getInstance().getMyUser().getId(), this.destiny.getId())){
                    //dismatch
                    for (int i = 0; i < users.size(); i++) {
                        if(users.get(i).getId() == this.destiny.getId()){
                            users.remove(i);
                        }
                    }
                    this.chatDirectView.eliminateView();
                    this.chatListView.removeUser(this.destiny.getNickname());
                }
                break;
        }
    }


    public void loadListChat(){
        this.chatListView.removeChats();
        this.users = this.chatManager.getChatList();
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
        Component[] components;
        JPanel jPanel = (JPanel) e.getComponent();
        components = jPanel.getComponents();
        JLabel jimage = (JLabel) components[0];
        JLabel jtext = (JLabel) components[2];
        for (User user: users) {
            if(user.getNickname().equals(jtext.getText().split(" -> Alias: ")[1])){
                this.destiny = user;
            }
        }
        ArrayList<ChatMessage> messages = this.chatManager.getChatMessages(this.destiny);

        ChatDirectView chatDirect = new ChatDirectView();
        this.chatDirectView = chatDirect;
        this.chatDirectView.registerButtonController(this);
        this.chatDirectView.registerWindowController(this);
        this.chatDirectView.updateNorth((ImageIcon) jimage.getIcon(), jtext.getText());
        for (ChatMessage message: messages) {
            if(message.getIdSource() == GlobalUser.getInstance().getMyUser().getId()){
                this.chatDirectView.addOwnMessage(message.getMessage());
            }else{
                this.chatDirectView.addFriendMessage(message.getMessage());
            }
        }
        this.chatManager.launchChatThread(this.destiny, this);
        this.chatDirectView.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        this.chatManager.closeSocketAndThread();
    }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }
}
