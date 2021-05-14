package Presentation.Controller;

import Business.Entity.User;
import Business.Model.SessionManager;
import Presentation.View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final LoginView loginView;
    private final RegisterView registerView;
    private final SessionManager sessionManager;
    private CheckLoginGUI checkLoginGUI;
    private final GlobalView globalView;
    private User cliente;

    public ButtonController(LoginView loginView, RegisterView registerView, GlobalView globalView, SessionManager sessionManager, CheckLoginGUI checkLoginGUI) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.globalView = globalView;
        this.sessionManager = sessionManager;
        this.checkLoginGUI = checkLoginGUI;
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
                cliente = new User(0, null, loginView.getNickname(), 0, null, null, loginView.getPasswd(), null, null, null);
                switch (sessionManager.login(cliente)) {
                    case 0:
                        //Login correcte, primer cop
                        loginView.delete();
                        checkLoginGUI.display();
                        break;
                    case 1:
                        //login correcte, usuari reincident
                        loginView.delete();
                        globalView.display();
                        break;
                    case -1:
                        //error en el servidor
                        loginView.dislplayLoginError();
                        break;
                    case -2:
                        //error en les credencials
                        loginView.displayCredentialsLoginError();
                }
            case RegisterView.REGISTER:
                cliente = new User(0, registerView.getFirstName(), registerView.getNickname(), Integer.parseInt(registerView.getAge()), registerView.getIsPremium(), registerView.getEmail(), registerView.getPasswd(), null, null, null);
                if (sessionManager.register(cliente)) {
                    registerView.delete();
                    loginView.display();
                } else {
                    loginView.dislplayLoginError();
                }
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
            case CheckLoginGUI.SAVE_BUTTON:
                cliente.setDescription(checkLoginGUI.getDescription());
                cliente.setProgrammingLanguage(checkLoginGUI.getLanguage());

                if (sessionManager.updateUser(cliente)) {
                    globalView.display();
                } else {
                    loginView.displayCredentialsLoginError();
                    checkLoginGUI.delete();
                    loginView.display();
                }
                break;
        }

    }
}
