package Presentation.Controller;

import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Presentation.View.GlobalView;
import Presentation.View.HomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NavigationController implements ActionListener, WindowListener {
    private final GlobalView globalView;
    private ProfileController profileController;
    private ChatController chatController;
    private ConnectionDAO connectionDAO;

    public NavigationController(GlobalView globalView, ProfileController profileController, ChatController chatController, ConnectionDAO connectionDAO) {
        this.globalView = globalView;
        this.profileController = profileController;
        this.chatController = chatController;
        this.connectionDAO = connectionDAO;
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
                    connectionDAO.disconnectFromServer();
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
