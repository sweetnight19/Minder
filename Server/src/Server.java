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
import Presentation.Controller.ServerController;
import Presentation.View.ServerView;

import javax.swing.*;

/**
 * @author Edmon Bosch
 * @author Joan Casals
 * @author Xavier Garrigós
 * @author David Marquet
 * The type Server.
 */
public class Server {
    /**
     * Main of server to initialize all classes needed, start the view of statistics and top 5
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Server!");
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");
        UserDAO userDAO = new SQLUserDAO(configurationDAO);
        PeerDAO peerDAO = new SQLPeerDAO(configurationDAO);
        ChatDAO chatDAO = new SQLChatDAO(configurationDAO);

        SwingUtilities.invokeLater(() -> {
            StatisticsManagement statisticsManagement = new StatisticsManagement(userDAO, peerDAO);
            ServerView view = new ServerView();
            ServerController serverController = new ServerController(view, statisticsManagement);
            serverController.start();
            view.registerController(serverController);
            view.start();
        });

        ChatServer chatServer = new ChatServer();
        chatServer.start();

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
