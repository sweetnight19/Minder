package Presentation.Controller;

import Business.Entity.User;
import Business.Model.GlobalUser;
import Business.Model.HomeManager;
import Business.Model.SessionManager;
import Persistance.ConnectionDAO;
import Presentation.View.CheckLoginGUI;
import Presentation.View.GlobalView;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ButtonController implements ActionListener, WindowListener {
    private final LoginView loginView;
    private final RegisterView registerView;
    private final SessionManager sessionManager;
    private final HomeManager homeManager;
    private final HomeController homeController;
    private final CheckLoginGUI checkLoginGUI;
    private final GlobalView globalView;
    private final ConnectionDAO connectionDAO;
    private BufferedImage image;
    private User cliente;

    public ButtonController(LoginView loginView, RegisterView registerView, GlobalView globalView, SessionManager sessionManager, HomeManager homeManager, HomeController homeController,CheckLoginGUI checkLoginGUI, ConnectionDAO connectionDAO) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.globalView = globalView;
        this.sessionManager = sessionManager;
        this.homeManager = homeManager;
        this.homeController = homeController;
        this.connectionDAO = connectionDAO;
        this.checkLoginGUI = checkLoginGUI;
        cliente = null;
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
                        sessionManager.saveGlobalUser(cliente);
                        checkLoginGUI.display();
                        break;
                    case 1:
                        //login correcte, usuari reincident
                        loginView.delete();
                        sessionManager.saveGlobalUser(cliente);
                        homeManager.getNextUsers();
                        homeController.loadFirstUser();
                        homeController.enableButtons();
                        globalView.display();
                        break;
                    case -1:
                        //error en el servidor
                        loginView.displayLoginError();
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
                    loginView.displayLoginError();
                }
                break;
            case CheckLoginGUI.SAVE_BUTTON:
                GlobalUser.getInstance().getMyUser().setDescription(checkLoginGUI.getDescription());
                GlobalUser.getInstance().getMyUser().setProgrammingLanguage(checkLoginGUI.getLanguage());

                if (sessionManager.updateUser(GlobalUser.getInstance().getMyUser())) {
                    sessionManager.saveGlobalUser(GlobalUser.getInstance().getMyUser());
                    homeManager.getNextUsers();
                    homeController.loadFirstUser();
                    homeController.enableButtons();
                    globalView.display();
                } else {
                    loginView.displayCredentialsLoginError();
                    checkLoginGUI.delete();
                    loginView.display();
                }
                break;
            case CheckLoginGUI.CHOOSE_IMAGE_BUTTON:
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    try {
                        image = ImageIO.read(new File(selectedFile.getAbsolutePath()));
                        SwingUtilities.invokeLater(() -> {
                            checkLoginGUI.setNewImage(image);
                        });
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    sessionManager.saveNewImage(image);
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
