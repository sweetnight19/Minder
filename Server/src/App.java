import Business.Model.DedicatedServer;
import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");
        try {
            ServerSocket sSocket = new ServerSocket(configurationDAO.getPortTCP()); // Inicialitzem el socket

            while (true) {
                Socket client = sSocket.accept(); // Acceptem peticions dels cients
                // Creem un servidor dedicat per cada client en particular
                DedicatedServer dedicated = new DedicatedServer(client);
                dedicated.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
