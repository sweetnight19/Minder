package Presentation;

import Business.Entity.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ServerView extends JFrame {
    private static final String LOGO_SRC = "Server/Media/TrophyCup.png";
    private static final int LOGO_WIDTH = 40;
    private static final int LOGO_HIGHT = 30;

    private Image logoImage;
    private JPanel top5;

    public ServerView(){
        add(configureCenter());
        configureWindow();
    }

    private void configureWindow() {
        setTitle("Minder Server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
    }

    private JSplitPane configureCenter(){
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, configureGraphic(), configureTop5());
        sp.setBackground(Color.WHITE);
        sp.setResizeWeight(0.75);
        return sp;
    }

    private JPanel configureGraphic() {
        JPanel hola = new JPanel();
        JLabel hola2 = new JLabel("hola");
        hola.add(hola2);
        return hola;
    }

    private JPanel configureTop5() {
        top5 = new JPanel();
        try {
            logoImage = ImageIO.read(new File(LOGO_SRC));
            // Image
            logoImage = logoImage.getScaledInstance(LOGO_WIDTH, LOGO_HIGHT, Image.SCALE_DEFAULT);
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            // Title
            JLabel logoTitle = new JLabel("TOP 5 USERS");
            Font logoFont = logoTitle.getFont().deriveFont(Font.BOLD, 20);
            logoTitle.setFont(logoFont);
            // Panel
            JPanel jpLogo = new JPanel();
            jpLogo.add(logoTitle);
            jpLogo.add(logoLabel);
            top5.add(jpLogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return top5;
    }

    public void updateTable(String[][] data, String[] headers){
        JTable j = new JTable(data, headers);
        j.setBounds(30, 400, 200, 300);
        top5.add(j);
    }

    public void start(){
        setVisible(true);
    }
}
