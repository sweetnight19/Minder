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
    private HomeView homeView;
    private HomeManager homeManager;
    public int sizeArray;

    public HomeController(HomeView homeView, HomeManager homeManager) {
        this.homeView = homeView;
        this.homeManager = homeManager;
    }

    public void loadFirstUser() {
        if (homeManager.getSize() != 0) {
            this.sizeArray = 0;
            User user = homeManager.getNextUser(sizeArray);
            System.out.println("name user " + user.getFirstName());
            BufferedImage image = homeManager.getNextImage(user);
            if (image == null) {
                try {
                    image = ImageIO.read(new File("Client/Media/avatar.png"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            homeView.showNextUser(user, image);
        } else {
            System.out.println("Es size es 0!!!!!!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HomeView.LIKE:
                System.out.println("LIKE");
                homeManager.insertLike(sizeArray);
                sizeArray++;
                User nextUser = homeManager.getNextUser(sizeArray);

                BufferedImage nextImage = homeManager.getNextImage(nextUser);
                if (nextImage == null) {
                    try {
                        nextImage = ImageIO.read(new File("Client/Media/avatar.png"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                homeView.showNextUser(nextUser, nextImage);
                break;
            case HomeView.DENY:
                System.out.println("DENY");
                sizeArray++;
                User nextUserDeny = homeManager.getNextUser(sizeArray);

                BufferedImage nextImageDeny = homeManager.getNextImage(nextUserDeny);
                if (nextImageDeny == null) {
                    try {
                        nextImageDeny = ImageIO.read(new File("Client/Media/avatar.png"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                homeView.showNextUser(nextUserDeny, nextImageDeny);
                break;
        }
    }
}
