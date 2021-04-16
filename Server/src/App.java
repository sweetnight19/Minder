import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;
import Persistance.SQLUserDAO;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ConfigurationDAO confDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");

        //TEST SQL
        User user=new User(0,"uno","sweetnight",25,"tres","cuatro","cinco","seis","siete","ocho");
        SQLUserDAO SQLUserDAO =new SQLUserDAO(confDAO);
        
        //add
        user.setId(SQLUserDAO.addUser(user));
        
        //delete
        SQLUserDAO.deleteUser(user.getId());

        // getAllUser
        ArrayList<User> user2 = SQLUserDAO.getAllUsers();
        System.out.println();
    }
}
