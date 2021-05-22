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

public class NavigationController implements ActionListener, WindowListener {
    private final GlobalView globalView;
    private final ProfileController profileController;
    private final ChatController chatController;
    private final ConnectionDAO connectionDAO;
    private final LoginView loginView;

    public NavigationController(GlobalView globalView, ProfileController profileController, ChatController chatController, ConnectionDAO connectionDAO, LoginView loginView) {
        this.globalView = globalView;
        this.profileController = profileController;
        this.chatController = chatController;
        this.connectionDAO = connectionDAO;
        this.loginView = loginView;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GlobalView.HOME:
                globalView.setTitle("MINDER HOME");
                SwingUtilities.invokeLater(() -> { globalView.showHome(); });
                break;
            case GlobalView.CHAT:
                globalView.setTitle("MINDER CHAT");
                chatController.loadListChat();
                SwingUtilities.invokeLater(() -> { globalView.showChat(); });
                break;
            case GlobalView.USER:
                globalView.setTitle("MINDER USER");
                profileController.loadProfileInformation();
                SwingUtilities.invokeLater(() -> { globalView.showUser(); });
                break;
            case GlobalView.LOGOUT:
                SwingUtilities.invokeLater(() -> {
                    int answer = globalView.dislplayLogoutWindow();
                    if (answer == JOptionPane.YES_OPTION) {
                        globalView.delete();
                        loginView.display();
                    }
                });
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
