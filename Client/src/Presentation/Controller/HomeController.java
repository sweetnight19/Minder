package Presentation.Controller;

import Business.Entity.User;
import Business.Model.GlobalUser;
import Business.Model.HomeManager;
import Presentation.View.GlobalView;
import Presentation.View.HomeView;

import javax.imageio.ImageIO;
import javax.swing.*;
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

    public void enableButtons(){
        SwingUtilities.invokeLater(() -> { this.homeView.enableButtons(); });
    }

    public void loadFirstUser() {
        if (homeManager.getSize() != 0) {
            this.sizeArray = 0;
            User user = homeManager.getNextUser(sizeArray);
            loadNextUsers(user);
        }
    }

    private void loadNextUsers(User user) {
        BufferedImage image = homeManager.getNextImage(user);
        if (image == null) {
            try {
                image = ImageIO.read(new File("Client/Media/avatar.png"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(this.sizeArray < this.homeManager.getCountPremium()){
            SwingUtilities.invokeLater(() -> { this.homeView.showIsPremium(); });
        }else{
            SwingUtilities.invokeLater(() -> { this.homeView.showIsNotPremium(); });
        }
        homeView.showNextUser(user, image);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case HomeView.LIKE:
                System.out.println("LIKE");
                homeManager.insertLike(sizeArray);
                sizeArray++;
                if(sizeArray < this.homeManager.getSize()) {
                    User nextUser = homeManager.getNextUser(sizeArray);

                    loadNextUsers(nextUser);
                }else{
                    SwingUtilities.invokeLater(() -> {
                        this.homeView.disableButtons();
                        this.homeView.dislplayNotMoreUsers();
                    });
                }
                break;
            case HomeView.DENY:
                System.out.println("DENY");
                sizeArray++;
                if(sizeArray < this.homeManager.getSize()) {
                    User nextUserDeny = homeManager.getNextUser(sizeArray);

                    loadNextUsers(nextUserDeny);
                }else{
                    SwingUtilities.invokeLater(() -> {
                        this.homeView.disableButtons();
                        this.homeView.dislplayNotMoreUsers();
                    });
                }
                break;
        }
    }
}
