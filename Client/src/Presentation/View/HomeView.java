package Presentation.View;

import Business.Entity.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeView extends JPanel {
    public static final String LIKE = "LIKE";
    public static final String DENY = "DENY";

    private JPanel pUser;
    private PhotoPanel photoPanel;
    private JLabel lAge;
    private JLabel lName;
    private JButton bLike;
    private JButton bDeny;
    private IconButton bMoreInfo;
    private JLabel logoLabel;

    public HomeView() {
        configureWindow();
        configureCenter();
        configureSouth();
    }

    private void configureWindow() {
        setLayout(new BorderLayout());
        setBackground(Color.decode(MinderColor.WHITE));
        Border generalBorder = BorderFactory.createEmptyBorder(30, 60, 5, 60);
        setBorder(generalBorder);
    }

    public void showNextUser(User user, BufferedImage image) {
        System.out.println("image = " + user.getPathImage());
        photoPanel.setPhoto(image);
        //PhotoPanel photoPanel = new PhotoPanel("Client/Media/image1763433(4).png");
        // Set Age
        lAge.setText(Integer.toString(user.getAge()));
        // Name
        lName.setText(user.getNickname());

        //pUser.revalidate();
        //pUser.repaint();
    }

    private void configureCenter() {
        pUser = new JPanel();
        pUser.setLayout(new BorderLayout());
        // Info User
        JPanel pInfoUser = new JPanel();
        pInfoUser.setLayout(new BorderLayout());
        Border infoBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        pInfoUser.setBorder(infoBorder);
        pInfoUser.setBackground(Color.decode(MinderColor.PINK));
        // Age
        lAge = new JLabel();
        lAge.setForeground(Color.decode(MinderColor.WHITE));
        pInfoUser.add(lAge, BorderLayout.WEST);
        // Name
        lName = new JLabel();
        lName.setHorizontalAlignment(SwingConstants.CENTER);
        lName.setForeground(Color.decode(MinderColor.WHITE));
        pInfoUser.add(lName, BorderLayout.CENTER);
        // More Info
        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("Client/Media/moreInfoIcon2.png"));
            logoLabel = new JLabel(new ImageIcon(logoImage));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pInfoUser.add(logoLabel, BorderLayout.EAST);

        pInfoUser.add(lName);
        pUser.add(pInfoUser, BorderLayout.SOUTH);
        photoPanel = new PhotoPanel(null);
        pUser.add(photoPanel, BorderLayout.CENTER);

        add(pUser);
    }

    private void configureSouth() {
        // Components
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 20));
        jpButtons.setBackground(Color.decode(MinderColor.WHITE));
        bDeny = new IconButton("Client/Media/denyIcon.png");
        bLike = new IconButton("Client/Media/likeIcon.png");
        jpButtons.add(bDeny);
        jpButtons.add(bLike);
        add(jpButtons, BorderLayout.SOUTH);
    }

    public void registerController(ActionListener actionListener) {
        bLike.setActionCommand(LIKE);
        bLike.addActionListener(actionListener);
        bDeny.setActionCommand(DENY);
        bDeny.addActionListener(actionListener);
    }

    public void dislplayNotMoreUsers() {
        JOptionPane.showMessageDialog(null,
                "You don't have more pretendents for today", "Enough for Today", JOptionPane.ERROR_MESSAGE);
    }

    public void disableButtons(){
        bLike.setEnabled(false);
        bDeny.setEnabled(false);
    }

    public void enableButtons(){
        bLike.setEnabled(true);
        bDeny.setEnabled(true);
    }

    public void showIsPremium(){
        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("Client/Media/Star.png"));
            logoLabel.setIcon(new ImageIcon(logoImage));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showIsNotPremium(){
        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("Client/Media/moreInfoIcon2.png"));
            logoLabel.setIcon(new ImageIcon(logoImage));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextUser() {

    }
}
