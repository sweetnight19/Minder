import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Persistance.JsonConfigurationDAO;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
        connectionDAO.registerUser(new User(0, "Edmon", "bosched", 20, "Normal",
                "edmonbosch@gmail.com", "hola", null, "soc l'edmon", "Java"));
    }
}
