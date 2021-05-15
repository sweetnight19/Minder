package Presentation.Controller;

import Business.Model.SessionManager;
import Presentation.View.GlobalView;
import Presentation.View.HomeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationController implements ActionListener {
    private final GlobalView globalView;
    private ProfileController profileController;
    private ChatController chatController;

    public NavigationController(GlobalView globalView, ProfileController profileController, ChatController chatController) {
        this.globalView = globalView;
        this.profileController = profileController;
        this.chatController = chatController;
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
                globalView.dislplayLogoutWindow();
                break;
            case HomeView.LIKE:
                System.out.println("LIKE");
                break;
            case HomeView.DENY:
                System.out.println("DENY");
                break;
        }
    }
}
