package Presentation.Controller;

import Presentation.View.GlobalView;
import Presentation.View.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController implements ActionListener {
    private final HomeView homeView;

    public HomeController(HomeView homeView) {
        this.homeView = homeView;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HomeView.LIKE:
                System.out.println("LIKE");
                break;
            case HomeView.DENY:
                System.out.println("DENY");
                break;
        }
    }
}
