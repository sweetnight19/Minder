package Presentation.Controller;

import Business.Entity.User;
import Business.Model.SessionManager;
import Persistance.ConnectionDAO;
import Presentation.View.GlobalView;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;
import Presentation.View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ButtonController implements ActionListener, WindowListener {
    private final LoginView loginView;
    private final RegisterView registerView;
    private final SessionManager sessionManager;
    private CheckLoginGUI checkLoginGUI;
    private final GlobalView globalView;
    private ConnectionDAO connectionDAO;
    private User cliente;

    public ButtonController(LoginView loginView, RegisterView registerView, GlobalView globalView, SessionManager sessionManager, ConnectionDAO connectionDAO) {
    public ButtonController(LoginView loginView, RegisterView registerView, GlobalView globalView, SessionManager sessionManager, CheckLoginGUI checkLoginGUI) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.globalView = globalView;
        this.sessionManager = sessionManager;
        this.connectionDAO = connectionDAO;
        this.checkLoginGUI = checkLoginGUI;
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
                        checkLoginGUI.display();
                        break;
                    case 1:
                        //login correcte, usuari reincident
                        loginView.delete();
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

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        connectionDAO.disconnectFromServer();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
