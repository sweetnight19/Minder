import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Persistance.JsonConfigurationDAO;
import Presentation.controller.ButtonController;
import Presentation.view.GlobalView;
import Presentation.view.HomeView;
import Presentation.view.LoginView;
import Presentation.view.RegisterView;
import Presentation.Controller.ButtonController;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, Client!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
        SessionManager sessionManager = new SessionManager(connectionDAO);
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        ButtonController buttonController = new ButtonController(loginView, registerView, sessionManager);
        HomeView homeView = new HomeView();
        GlobalView globalView = new GlobalView(homeView);

        ButtonController buttonController = new ButtonController(loginView, registerView, globalView);

        loginView.registerController(buttonController);
        registerView.registerController(buttonController);
        globalView.registerController(buttonController);
        homeView.registerController(buttonController);

        loginView.display();
    }
}
