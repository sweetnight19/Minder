package Presentation.Controller;

import Business.Entity.User;
import Persistance.ConnectionDAO;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final LoginView loginView;
    private final RegisterView registerView;
    private final ConnectionDAO connectionDAO;

    public ButtonController(LoginView loginView, RegisterView registerView, ConnectionDAO connectionDAO) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.connectionDAO = connectionDAO;
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
                cliente = new User(0, null, registerView.getNickname(), 0, null, null, registerView.getPasswd(), null,
                        null, null);
                // TODO
                if (connectionDAO.validateLogin(cliente)) {
                    switch (connectionDAO.checklogin(cliente)) {
                        case 0:
                            System.out.println("Login correcte, primer cop");
                            break;
                        case 1:
                            System.out.println("login correcte, usuari reincident");
                            break;
                        case -1:
                            System.out.println("error en el servidor");
                            break;
                    }
                } else {
                    System.out.println("login incorrecte");
                }

                break;

            case RegisterView.REGISTER:
                cliente = new User(0, registerView.getFirstName(), registerView.getNickname(),
                        Integer.parseInt(registerView.getAge()), registerView.getIsPremium(), registerView.getEmail(),
                        registerView.getPasswd(), null, null, null);
                if (connectionDAO.registerUser(cliente)) {
                    registerView.delete();
                    loginView.display();
                } else {
                    System.out.println("error en el servidor");
                }
                break;
        }

    }
}
