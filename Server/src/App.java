import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Business.Model.DedicatedServer;
import Persistance.*;
import Persistance.SQL.SQLChatDAO;
import Persistance.SQL.SQLPeerDAO;
import Persistance.SQL.SQLUserDAO;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");
        UserDAO userDAO = new SQLUserDAO(configurationDAO);
        PeerDAO peerDAO = new SQLPeerDAO(configurationDAO);
        ChatDAO chatDAO = new SQLChatDAO(configurationDAO);
        try {
            ServerSocket sSocket = new ServerSocket(configurationDAO.getPortTCP()); // Inicialitzem el socket

            while(true) {
                Socket client = sSocket.accept();   //Acceptem peticions dels cients
                //Creem un servidor dedicat per cada client en particular
                DedicatedServer dedicated = new DedicatedServer(client, userDAO, chatDAO, peerDAO);
                dedicated.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
