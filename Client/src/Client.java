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

/**
 * The type Client.
 */
public class Client {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException                     the io exception
     * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
     * @throws ClassNotFoundException          the class not found exception
     * @throws InstantiationException          the instantiation exception
     * @throws IllegalAccessException          the illegal access exception
     */
    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("Hello, Client!");
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        SwingUtilities.invokeLater(() -> {
            ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
            HomeManager homeManager = new HomeManager(connectionDAO);
            SessionManager sessionManager = new SessionManager(connectionDAO);

            // View
            LoginView loginView = new LoginView();
            RegisterView registerView = new RegisterView();
            HomeView homeView = new HomeView();
            ChatListView chatListView = new ChatListView();
            EditView editView = new EditView();
            GlobalView globalView = new GlobalView(homeView, chatListView, editView);
            ProfileManager profileManager = new ProfileManager(connectionDAO);
            ChatManager chatManager = new ChatManager(connectionDAO, configurationDAO);
            CheckLoginGUI checkLoginGUI = new CheckLoginGUI();

            // Controller
            HomeController homeController = new HomeController(homeView, homeManager);
            ButtonController buttonController = new ButtonController(loginView, registerView, globalView, sessionManager, homeManager, homeController, checkLoginGUI, connectionDAO);
            ProfileController profileController = new ProfileController(editView, profileManager, loginView, globalView);
            ChatController chatController = new ChatController(chatListView, chatManager);
            NavigationController navigationController = new NavigationController(globalView, profileController, chatController, connectionDAO,loginView);

            loginView.registerController(buttonController);
            registerView.registerController(buttonController);
            globalView.registerController(navigationController);
            homeView.registerController(homeController);
            chatListView.registerController(chatController);
            editView.registerController(profileController);
            checkLoginGUI.registerController(buttonController);

            loginView.display();
            //globalView.display();
        });
    }
}
