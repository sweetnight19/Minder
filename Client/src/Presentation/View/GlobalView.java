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
    private NavigationButton bHome;
    private NavigationButton bUser;
    private NavigationButton bLogout;
    private NavigationButton bChat;
    public static final String MINDER_HOME = "MINDER HOME";
    public static final String HOME = "HOME";
    public static final String CHAT = "CHAT";
    public static final String USER = "USER";
    public static final String LOGOUT = "LOGOUT";

    public GlobalView(HomeView homeView, ChatListView chatListView, EditView editView) {
        setTitle(MINDER_HOME);
        setPreferredSize(new Dimension(500, 700));
        this.jpHome = homeView;
        this.jpChat = chatListView;
        this.jpUser = editView;
        configureWindow();
        configureNorth();
        configureCenter();
        pack();
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
        //bHome = new IconButton();
        bHome = new NavigationButton("Client/Media/homeIcon.png","Client/Media/homeIconEnabled.png");
        bChat = new NavigationButton("Client/Media/chatIcon.png", "Client/Media/chatIconEnabled.png");
        bUser = new NavigationButton("Client/Media/profileVector.png", "Client/Media/profileVectorEnabled.png");
        bLogout = new NavigationButton("Client/Media/logoutIcon.png", "Client/Media/logoutIconEnabled.png");
        bHome.changeToEnable();
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
        bHome.changeToDisable();
        bChat.changeToDisable();
        bUser.changeToDisable();
        bLogout.changeToEnable();
        ImageIcon icon = new ImageIcon("Client/Media/logoutIcon.png");
        int answer = JOptionPane.showConfirmDialog(null, "Would you like to logout?", "MINDER LOGOUT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        bLogout.changeToDisable();
        return answer;
    }

    public void showHome() {
        cardLayout.show(jpCard, HOME);
        bHome.changeToEnable();
        bChat.changeToDisable();
        bUser.changeToDisable();
        bLogout.changeToDisable();
    }

    public void showChat() {
        cardLayout.show(jpCard, CHAT);
        bHome.setIcon(new ImageIcon("Client/Media/homeIcon.png"));
        bHome.changeToDisable();
        bChat.changeToEnable();
        bUser.changeToDisable();
        bLogout.changeToDisable();
    }

    public void showUser() {
        cardLayout.show(jpCard, USER);
        bHome.changeToDisable();
        bChat.changeToDisable();
        bUser.changeToEnable();
        bLogout.changeToDisable();
    }

    public void display() {
        setVisible(true);
    }

    public void delete() {
        setVisible(false);
    }

}
