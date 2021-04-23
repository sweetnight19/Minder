import Business.Entity.User;
import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Persistance.JsonConfigurationDAO;
import Presentation.controller.ButtonController;
import Presentation.controller.FieldController;
import Presentation.view.LoginView;
import Presentation.view.RegisterView;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
        connectionDAO.registerUser(new User(0, "Edmon", "bosched", 20, "Normal",
                "edmonbosch@gmail.com", "hola", null, "soc l'edmon", "Java"));
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();

        FieldController fieldController = new FieldController(loginView, registerView);
        ButtonController buttonController = new ButtonController(loginView, registerView);

        loginView.registerController(fieldController, buttonController);
        registerView.registerController(fieldController, buttonController);

        loginView.display();
        //ConfigurationDAO confDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
    }
}
