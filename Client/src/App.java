import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.JsonConfigurationDAO;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        ConnectionDAO conn = new ConnectionDAO();
    }
}
