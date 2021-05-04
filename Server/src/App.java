import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Business.Model.DedicatedServer;
import Business.Model.StatisticsManagement;
import Persistance.ChatDAO;
import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;
import Persistance.PeerDAO;
import Persistance.UserDAO;
import Persistance.SQL.SQLChatDAO;
import Persistance.SQL.SQLPeerDAO;
import Persistance.SQL.SQLUserDAO;
import Presentation.ServerController;
import Presentation.ServerView;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");
        UserDAO userDAO = new SQLUserDAO(configurationDAO);
        // int test = userDAO.checkLoginIntent(new User(0, null, "sweetnight", 0, null, null, null, null, null, null));
        PeerDAO peerDAO = new SQLPeerDAO(configurationDAO);
        ChatDAO chatDAO = new SQLChatDAO(configurationDAO);

        StatisticsManagement statisticsManagement = new StatisticsManagement(userDAO, peerDAO);
        ServerView view = new ServerView();
        ServerController serverController = new ServerController(view, statisticsManagement);
        serverController.start();
        view.start();

        try {
            ServerSocket sSocket = new ServerSocket(configurationDAO.getPortTCP()); // Inicialitzem el socket

            while (true) {
                Socket client = sSocket.accept(); // Acceptem peticions dels cients
                // Creem un servidor dedicat per cada client en particular
                DedicatedServer dedicated = new DedicatedServer(client, userDAO, chatDAO, peerDAO);
                dedicated.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
