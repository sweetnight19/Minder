import Business.Model.ChatManager;
import Business.Model.HomeManager;
import Business.Model.ProfileManager;
import Business.Model.SessionManager;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Persistance.JsonConfigurationDAO;
import Presentation.Controller.*;
import Presentation.View.*;

import javax.swing.*;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, Client!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        SwingUtilities.invokeLater(() -> {
            ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
            SessionManager sessionManager = new SessionManager(connectionDAO);
            HomeManager homeManager = new HomeManager(connectionDAO);
            // View
            LoginView loginView = new LoginView();
            RegisterView registerView = new RegisterView();
            HomeView homeView = new HomeView();
            ChatListView chatListView = new ChatListView();
            EditView editView = new EditView();
            GlobalView globalView = new GlobalView(homeView, chatListView, editView);
            ProfileManager profileManager = new ProfileManager(connectionDAO);
            ChatManager chatManager = new ChatManager(connectionDAO, configurationDAO);
            // Controller
            ButtonController buttonController = new ButtonController(loginView, registerView, globalView, sessionManager, connectionDAO);
            ProfileController profileController = new ProfileController(editView, profileManager);
            ChatController chatController = new ChatController(chatListView, chatManager);
            NavigationController navigationController = new NavigationController(globalView, profileController, chatController, connectionDAO);
            HomeController homeController = new HomeController(homeView, homeManager);

            loginView.registerController(buttonController);
            registerView.registerController(buttonController);
            globalView.registerController(navigationController);
            homeView.registerController(homeController);
            editView.registerController(profileController);

            loginView.display();
            //globalView.display();
        });
    }
}
