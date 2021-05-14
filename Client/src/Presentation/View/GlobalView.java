package Presentation.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GlobalView extends JFrame{
    private static final String USER_IMG_ERR = "Error al carregar la imatge d'usuari!!";
    private static final int TEXTFIELD_COLUMNS = 20;

    // Components
    private final HomeView jpHome;
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JPanel jpNavigationBar;
    private IconButton bHome;
    private IconButton bUser;
    private IconButton bLogout;
    private IconButton bChat;
    public static final String HOME = "HOME";
    public static final String CHAT = "CHAT";
    public static final String USER = "USER";
    public static final String LOGOUT = "LOGOUT";

    public GlobalView(HomeView homeView) {
        this.jpHome = homeView;
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
        bHome = new IconButton("Client/Media/homeIcon.png");
        bChat = new IconButton("Client/Media/chatIcon.png");
        bUser = new IconButton("Client/Media/profileVector.png");
        bLogout = new IconButton("Client/Media/logoutIcon.png");
        jpNavigationBar = new JPanel();
        jpNavigationBar.setLayout(new FlowLayout(FlowLayout.CENTER,70,10));
        jpNavigationBar.add(bHome);
        jpNavigationBar.add(bChat);
        jpNavigationBar.add(bUser);
        jpNavigationBar.add(bLogout);
        jPanel.add(jpNavigationBar, BorderLayout.NORTH);
    }
    private void configureCenter() {
        JPanel jpCard = new JPanel();
        jpCard.setLayout(new CardLayout());
        jpCard.add(jpHome, "1");
        jPanel.add(jpCard, BorderLayout.CENTER);
    }
    public void registerController(ActionListener actionListener) {
        bHome.setActionCommand(HOME);
        bHome.addActionListener(actionListener);
        bChat.setActionCommand(CHAT);
        bChat.addActionListener(actionListener);
        bUser.setActionCommand(USER);
        bUser.addActionListener(actionListener);
        bLogout.setActionCommand(LOGOUT);
        bLogout.addActionListener(actionListener);
    }
    public void dislplayLogoutWindow() {
        ImageIcon icon = new ImageIcon("Client/Media/logoutIcon.png");
        int answer = JOptionPane.showConfirmDialog(null, "Would you like to logout?", "MINDER LOGOUT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        if(answer == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    public void display() {
        setVisible(true);
    }
}
