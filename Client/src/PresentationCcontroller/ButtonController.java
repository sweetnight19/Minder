package PresentationCcontroller;

import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final LoginView loginView;
    private final RegisterView registerView;

    public ButtonController(LoginView loginView, RegisterView registerView) {
        this.loginView = loginView;
        this.registerView = registerView;
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
        }

    }
}
