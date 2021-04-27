package Presentation.controller;

import Presentation.view.LoginView;
import Presentation.view.RegisterView;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FieldController implements FocusListener {
    private LoginView loginView;
    private RegisterView registerView;

    public FieldController(LoginView loginView, RegisterView registerView) {
        this.loginView = loginView;
        this.registerView = registerView;
    }

    @Override
    public void focusGained(FocusEvent e) {
        switch (e.getComponent().getName()) {
        case LoginView.NICK:
            loginView.nicknameSelected();
            break;
        case LoginView.PASSWD:
            loginView.passwdSelected();
            break;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        switch (e.getComponent().getName()) {
        case LoginView.NICK:
            loginView.nicknameUnselected();
            break;
        case LoginView.PASSWD:
            loginView.passwdUnselected();
            break;
        }
    }
}
