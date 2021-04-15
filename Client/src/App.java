import Business.Entity.User;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        ConnectionDAO connectionDAO = new ConnectionDAOImpl();
        connectionDAO.registerUser(new User(0, "Edmon", "bosched", 20, "Normal",
                "edmonbosch@gmail.com", "hola", null, "soc l'edmon", "Java"));
    }
}
