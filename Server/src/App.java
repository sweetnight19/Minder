import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;
import Persistance.SQL.SQLPeerDAO;
import Persistance.SQL.SQLUserDAO;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ConfigurationDAO confDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");

        // **TEST SQL***//
        // User user = new User(0, "uno", "sweetnight", 25, "tres", "cuatro", "cinco",
        // "seis", "siete", "ocho");
        // SQLUserDAO SQLUserDAO = new SQLUserDAO(confDAO);
        SQLPeerDAO sqlPeerDAO = new SQLPeerDAO(confDAO);

        // add
        // user.setId(SQLUserDAO.addUser(user));
        sqlPeerDAO.addPeer(1, 2);
        // delete
        // SQLUserDAO.deleteUser(user.getId());

        // getAll
        // ArrayList<User> user2 = SQLUserDAO.getAllUsers();

        System.out.println("test");
    }
}
