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
        SQLPeerDAO sqlPeerDAO = new SQLPeerDAO(confDAO);
        //sqlPeerDAO.addPeer(5, 2);
        //sqlPeerDAO.addPeer(2, 5);
        sqlPeerDAO.deletePeer(2, 5);
        System.out.println("test");
    }
}
