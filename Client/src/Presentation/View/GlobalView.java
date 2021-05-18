package Presentation.View;

import Presentation.Controller.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class GlobalView extends JFrame {
    private static final String USER_IMG_ERR = "Error al carregar la imatge d'usuari!!";
    private static final int TEXTFIELD_COLUMNS = 20;

    // Components
    private HomeView jpHome;
    private ChatListView jpChat;
    private EditView jpUser;
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JPanel jpNavigationBar;
    private CardLayout cardLayout;
    private JPanel jpCard;
    private IconButton bHome;
    private IconButton bUser;
    private IconButton bLogout;
    private IconButton bChat;
    public static final String MINDER_HOME = "MINDER HOME";
    public static final String HOME = "HOME";
    public static final String CHAT = "CHAT";
    public static final String USER = "USER";
    public static final String LOGOUT = "LOGOUT";

    public GlobalView(HomeView homeView, ChatListView chatListView, EditView editView) {
        setTitle(MINDER_HOME);
        setSize(450, 700);
        this.jpHome = homeView;
        this.jpChat = chatListView;
        this.jpUser = editView;
        configureWindow();
        configureNorth();
        configureCenter();
        //pack();
        setLocationRelativeTo(null);
    }

    private void configureWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Color.GREEN);
        jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane);
    }

    private void configureNorth() {
        bHome = new IconButton("Client/Media/homeIcon.png");
        bChat = new IconButton("Client/Media/chatIcon.png");
        bUser = new IconButton("Client/Media/profileVector.png");
        bLogout = new IconButton("Client/Media/logoutIcon.png");
        jpNavigationBar = new JPanel();
        jpNavigationBar.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        jpNavigationBar.add(bHome);
        jpNavigationBar.add(bChat);
        jpNavigationBar.add(bUser);
        jpNavigationBar.add(bLogout);
        jPanel.add(jpNavigationBar, BorderLayout.NORTH);
    }

    private void configureCenter() {
        jpCard = new JPanel();
        cardLayout = new CardLayout();
        jpCard.setLayout(cardLayout);
        jpUser.transfromToNotEditable();
        jpCard.add(jpUser, USER);
        //jpChat.addUserChat();
        //jpChat.addUserChat();
        jpCard.add(jpChat, CHAT);
        jpCard.add(jpHome, HOME);
        cardLayout.last(jpCard);
        jPanel.add(jpCard, BorderLayout.CENTER);
    }

    public void registerController(NavigationController controller) {
        bHome.setActionCommand(HOME);
        bHome.addActionListener(controller);
        bChat.setActionCommand(CHAT);
        bChat.addActionListener(controller);
        bUser.setActionCommand(USER);
        bUser.addActionListener(controller);
        bLogout.setActionCommand(LOGOUT);
        bLogout.addActionListener(controller);
        this.addWindowListener(controller);
    }

    public int dislplayLogoutWindow() {
        ImageIcon icon = new ImageIcon("Client/Media/logoutIcon.png");
        return JOptionPane.showConfirmDialog(null, "Would you like to logout?", "MINDER LOGOUT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public int displayExitWindow() {
        return JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "EXIT APPLICATION", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showHome() {
        cardLayout.show(jpCard, HOME);
    }

    public void showChat() {
        cardLayout.show(jpCard, CHAT);
    }

    public void showUser() {
        cardLayout.show(jpCard, USER);
    }

    public void display() {
        setVisible(true);
    }

    public void delete() {
        setVisible(false);
    }

}
