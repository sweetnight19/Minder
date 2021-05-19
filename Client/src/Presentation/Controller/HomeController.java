package Presentation.Controller;

import Business.Entity.User;
import Business.Model.GlobalUser;
import Business.Model.HomeManager;
import Presentation.View.GlobalView;
import Presentation.View.HomeView;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeController implements ActionListener {
    private final HomeView homeView;
    private final HomeManager homeManager;

    public HomeController(HomeView homeView, HomeManager homeManager) {
        this.homeView = homeView;
        this.homeManager = homeManager;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HomeView.LIKE:
                System.out.println("LIKE");

                ArrayList<User> users = homeManager.getNextUsers();
                System.out.println("users: " + users.isEmpty());

                User user = homeManager.getUser();

                //BufferedImage image = homeManager.getNextImage(GlobalUser.getInstance().getMyUser());
                System.out.println("HOLSSSSS");
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File("C:\\Users\\Xavi\\Desktop\\ImageTest\\image1763433(4).png"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                homeView.showNextUser(user, image);
                break;
            case HomeView.DENY:
                System.out.println("DENY");
                break;
        }
    }
}
