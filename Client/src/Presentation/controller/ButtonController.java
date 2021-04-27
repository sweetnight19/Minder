package Presentation.controller;

import Presentation.view.LoginView;
import Presentation.view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private LoginView loginView;
    private RegisterView registerView;

    public ButtonController(LoginView loginView, RegisterView registerView) {
        this.loginView = loginView;
        this.registerView = registerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "REGISTER":
                System.out.println("button Register");
                loginView.delete();
                registerView.display();
                break;

            case "LOGIN":
                System.out.println("button Login");
                registerView.delete();
                loginView.display();
                break;
        }

    }
}
