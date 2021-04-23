import Persistance.ConfigurationDAO;
import Persistance.JsonConfigurationDAO;
import Presentation.controller.ButtonController;
import Presentation.controller.FieldController;
import Presentation.view.LoginView;
import Presentation.view.RegisterView;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
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
