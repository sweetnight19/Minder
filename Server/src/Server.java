import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Business.Model.ChatServer;
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

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Server!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");
        UserDAO userDAO = new SQLUserDAO(configurationDAO);
        PeerDAO peerDAO = new SQLPeerDAO(configurationDAO);
        ChatDAO chatDAO = new SQLChatDAO(configurationDAO);

        StatisticsManagement statisticsManagement = new StatisticsManagement(userDAO, peerDAO);
        ServerView view = new ServerView();
        ServerController serverController = new ServerController(view, statisticsManagement);
        serverController.start();
        view.start();

        try {
            ServerSocket sSocket = new ServerSocket(configurationDAO.getPortTCP()); // Inicialitzem el socket
            ServerSocket sSocketChat = new ServerSocket(54333);
            while (true) {
                Socket client = sSocket.accept(); // Acceptem peticions dels cients
                Socket chatClient = sSocketChat.accept(); //Peticions de client per chat instantani
                // Creem un servidor dedicat per cada client en particular
                DedicatedServer dedicated = new DedicatedServer(client, userDAO, chatDAO, peerDAO);
                ChatServer chatServer = new ChatServer(chatClient);
                dedicated.start();
                chatServer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
