import Business.Model.ChatManager;
import Business.Model.ProfileManager;
import Business.Model.SessionManager;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Persistance.JsonConfigurationDAO;
import Presentation.Controller.*;
import Presentation.View.*;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, Client!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
        SessionManager sessionManager = new SessionManager(connectionDAO);
        // View
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        HomeView homeView = new HomeView();
        ChatListView chatListView = new ChatListView();
        EditView editView = new EditView();
        GlobalView globalView = new GlobalView(homeView, chatListView, editView);
        ProfileManager profileManager = new ProfileManager(connectionDAO);
        // Controller
        ButtonController buttonController = new ButtonController(loginView, registerView, globalView, sessionManager);
        NavigationController navigationController = new NavigationController(globalView);
        HomeController homeController = new HomeController(homeView);
        ProfileController profileController = new ProfileController(editView, profileManager);
        loginView.registerController(buttonController);
        registerView.registerController(buttonController);
        globalView.registerController(navigationController);
        homeView.registerController(homeController);
        editView.registerController(profileController);

        //loginView.display();
        globalView.display();
    }
}
