package Presentation.controller;

import Presentation.View.GlobalView;
import Presentation.View.HomeView;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private LoginView loginView;
    private RegisterView registerView;
    private GlobalView globalView;

    public ButtonController(LoginView loginView, RegisterView registerView, GlobalView globalView) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.globalView = globalView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case LoginView.MOVE_TO_REGISTER:
                loginView.delete();
                registerView.display();
                break;

            case RegisterView.MOVE_TO_LOGIN:
                registerView.delete();
                loginView.display();
                break;

            case LoginView.LOGIN:
                System.out.println(loginView.getNickname());
                System.out.println(loginView.getPasswd());
                break;

            case RegisterView.REGISTER:
                System.out.println(registerView.getFirstName());
                System.out.println(registerView.getNickname());
                System.out.println(registerView.getAge());
                System.out.println(registerView.getIsPremium());
                System.out.println(registerView.getEmail());
                System.out.println(registerView.getPasswd());
                System.out.println(registerView.getConfirmPasswd());
                break;

            case GlobalView.HOME:
                System.out.println("HOME");
                globalView.setTitle("MINDER HOME");
                break;
            case GlobalView.CHAT:
                System.out.println("CHAT");
                globalView.setTitle("MINDER CHAT");
                break;
            case GlobalView.USER:
                System.out.println("USER");
                globalView.setTitle("MINDER USER");
                break;
            case GlobalView.LOGOUT:
                System.out.println("LOGOUT");
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
