import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;
import Presentation.views.MinderView;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        MinderView view = new MinderView();
        view.display();
        //ConfigurationDAO confDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
    }
}
