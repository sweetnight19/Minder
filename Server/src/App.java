import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;
import Persistance.UserMinderDAO;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ConfigurationDAO confDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");

        //TEST SQL
        User user=new User(0,"uno","sweetnight",25,"tres","cuatro","cinco","seis","siete","ocho");
        UserMinderDAO userMinderDAO=new UserMinderDAO(confDAO);
        user.setId(userMinderDAO.addUser(user));
        userMinderDAO.deleteUser(user.getId());
        System.out.println("hola");
        //
    }
}
