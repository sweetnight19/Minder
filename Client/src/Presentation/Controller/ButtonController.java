package Presentation.Controller;

import Business.Entity.User;
import Business.Model.SessionManager;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final LoginView loginView;
    private final RegisterView registerView;
    private final SessionManager sessionManager;

    public ButtonController(LoginView loginView, RegisterView registerView, SessionManager sessionManager) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.sessionManager = sessionManager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        User cliente;

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
                cliente = new User(0, null, registerView.getNickname(), 0, null, null, registerView.getPasswd(), null, null, null);
                sessionManager.login(cliente);
                break;

            case RegisterView.REGISTER:
                cliente = new User(0, registerView.getFirstName(), registerView.getNickname(), Integer.parseInt(registerView.getAge()), registerView.getIsPremium(), registerView.getEmail(), registerView.getPasswd(), null, null, null);
                if (sessionManager.register(cliente)) {
                    registerView.delete();
                    loginView.display();
                }
                break;
        }
    }
}
