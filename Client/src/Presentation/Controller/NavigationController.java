package Presentation.Controller;

import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Presentation.View.GlobalView;
import Presentation.View.HomeView;
import Presentation.View.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The type Navigation controller.
 */
public class NavigationController implements ActionListener, WindowListener {
    private final GlobalView globalView;
    private final ProfileController profileController;
    private final ChatController chatController;
    private final HomeController homeController;
    private final ConnectionDAO connectionDAO;
    private final LoginView loginView;

    /**
     * Instantiates a new Navigation controller.
     *
     * @param globalView        the global view
     * @param profileController the profile controller
     * @param chatController    the chat controller
     * @param homeController    the home controller
     * @param connectionDAO     the connection dao
     * @param loginView         the login view
     */
    public NavigationController(GlobalView globalView, ProfileController profileController, ChatController chatController, HomeController homeController, ConnectionDAO connectionDAO, LoginView loginView) {
        this.globalView = globalView;
        this.profileController = profileController;
        this.chatController = chatController;
        this.homeController = homeController;
        this.connectionDAO = connectionDAO;
        this.loginView = loginView;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GlobalView.HOME:
                globalView.setTitle("MINDER HOME");
                globalView.showHome();
                break;
            case GlobalView.CHAT:
                globalView.setTitle("MINDER CHAT");
                chatController.loadListChat();
                globalView.showChat();
                break;
            case GlobalView.USER:
                globalView.setTitle("MINDER USER");
                profileController.loadProfileInformation();
                globalView.showUser();
                break;
            case GlobalView.LOGOUT:
                int answer = globalView.dislplayLogoutWindow();
                if (answer == JOptionPane.YES_OPTION) {
                    //System.exit(0);
                    globalView.delete();
                    globalView.showHome();
                    loginView.display();
                }
                break;
            case HomeView.LIKE:
                System.out.println("LIKE");
                break;
            case HomeView.DENY:
                System.out.println("DENY");
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        connectionDAO.disconnectFromServer();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
