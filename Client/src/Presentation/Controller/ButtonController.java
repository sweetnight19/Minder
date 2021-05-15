package Presentation.Controller;

import Business.Entity.User;
import Business.Model.SessionManager;
import Presentation.View.GlobalView;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final LoginView loginView;
    private final RegisterView registerView;
    private final SessionManager sessionManager;
    private final GlobalView globalView;

    public ButtonController(LoginView loginView, RegisterView registerView, GlobalView globalView, SessionManager sessionManager) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.globalView = globalView;
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
                cliente = new User(0, null, loginView.getNickname(), 0, null, null, loginView.getPasswd(), null, null, null);
                switch (sessionManager.login(cliente)) {
                    case 0:
                        //Login correcte, primer cop
                        loginView.delete();
                        sessionManager.saveGlobalUser(cliente);
                        globalView.display();
                        System.out.println("al final es veu q estic aqui haha");
                        break;
                    case 1:
                        //login correcte, usuari reincident
                        loginView.delete();
                        System.out.println("he entrat aqui");
                        sessionManager.saveGlobalUser(cliente);
                        globalView.display();
                        break;
                    case -1:
                        //error en el servidor
                        loginView.dislplayLoginError();
                        break;
                    case -2:
                        //error en les credencials
                        loginView.displayCredentialsLoginError();
                        break;
                }
                break;

            case RegisterView.REGISTER:
                cliente = new User(0, registerView.getFirstName(), registerView.getNickname(), Integer.parseInt(registerView.getAge()), registerView.getIsPremium(), registerView.getEmail(), registerView.getPasswd(), null, null, null);
                if (sessionManager.register(cliente)) {
                    registerView.delete();
                    loginView.display();
                } else {
                    loginView.dislplayLoginError();
                }
                break;
        }
    }
}
