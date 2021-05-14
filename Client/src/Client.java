import Business.Model.SessionManager;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Persistance.JsonConfigurationDAO;
import Presentation.Controller.ButtonController;
import Presentation.View.*;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, Client!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
        SessionManager sessionManager = new SessionManager(connectionDAO);
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        HomeView homeView = new HomeView();
        GlobalView globalView = new GlobalView(homeView);
        CheckLoginGUI checkLoginGUI = new CheckLoginGUI();
        ButtonController buttonController = new ButtonController(loginView, registerView, globalView, sessionManager, checkLoginGUI);

        checkLoginGUI.registerController(buttonController);
        loginView.registerController(buttonController);
        registerView.registerController(buttonController);
        globalView.registerController(buttonController);
        homeView.registerController(buttonController);

        loginView.display();
        //globalView.display();
    }
}
