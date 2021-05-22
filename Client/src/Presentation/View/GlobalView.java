package Presentation.View;

import Presentation.Controller.NavigationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * The type Global view.
 */
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
    /**
     * The constant MINDER_HOME.
     */
    public static final String MINDER_HOME = "MINDER HOME";
    /**
     * The constant HOME.
     */
    public static final String HOME = "HOME";
    /**
     * The constant CHAT.
     */
    public static final String CHAT = "CHAT";
    /**
     * The constant USER.
     */
    public static final String USER = "USER";
    /**
     * The constant LOGOUT.
     */
    public static final String LOGOUT = "LOGOUT";

    /**
     * Instantiates a new Global view.
     *
     * @param homeView     the home view
     * @param chatListView the chat list view
     * @param editView     the edit view
     */
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

    /**
     * Register controller.
     *
     * @param controller the controller
     */
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

    /**
     * Dislplay logout window int.
     *
     * @return the int
     */
    public int dislplayLogoutWindow() {
        ImageIcon icon = new ImageIcon("Client/Media/logoutIcon.png");
        return JOptionPane.showConfirmDialog(null, "Would you like to logout?", "MINDER LOGOUT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    /**
     * Display exit window int.
     *
     * @return the int
     */
    public int displayExitWindow() {
        return JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "EXIT APPLICATION", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show home.
     */
    public void showHome() {
        cardLayout.show(jpCard, HOME);
    }

    /**
     * Show chat.
     */
    public void showChat() {
        cardLayout.show(jpCard, CHAT);
    }

    /**
     * Show user.
     */
    public void showUser() {
        cardLayout.show(jpCard, USER);
    }

    /**
     * Display.
     */
    public void display() {
        setVisible(true);
    }

    /**
     * Delete.
     */
    public void delete() {
        setVisible(false);
    }

}
