import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ConfigurationDAO confDAO = new JsonConfigurationDAO("Server/Data/configuracio-servidor.json");
    }
}
