import Presentation.controller.ButtonController;
import Presentation.view.LoginView;
import Presentation.view.RegisterView;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();

        ButtonController buttonController = new ButtonController(loginView, registerView);

        loginView.registerController(buttonController);
        registerView.registerController(buttonController);

        loginView.display();
        //ConfigurationDAO confDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
    }
}
