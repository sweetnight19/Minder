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

/**
 * The type Home view.
 */
public class HomeView extends JPanel {
    /**
     * The constant LIKE.
     */
    public static final String LIKE = "LIKE";
    /**
     * The constant DENY.
     */
    public static final String DENY = "DENY";

    private PhotoPanel photoPanel;
    private JLabel lAge;
    private JLabel lName;
    private JButton bLike;
    private JButton bDeny;
    private JLabel logoLabel;

    /**
     * Instantiates a new Home view.
     */
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

    /**
     * Show next user.
     *
     * @param user  the user
     * @param image the image
     */
    public void showNextUser(User user, BufferedImage image) {
        System.out.println("image = " + user.getPathImage());
        photoPanel.setPhoto(image);
        lAge.setText(Integer.toString(user.getAge()));
        lName.setText(user.getNickname());
    }

    private void configureCenter() {
        JPanel pUser = new JPanel();
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
        BufferedImage logoImage;
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

    /**
     * Register controller.
     *
     * @param actionListener the action listener
     */
    public void registerController(ActionListener actionListener) {
        bLike.setActionCommand(LIKE);
        bLike.addActionListener(actionListener);
        bDeny.setActionCommand(DENY);
        bDeny.addActionListener(actionListener);
    }

    /**
     * Dislplay not more users.
     */
    public void dislplayNotMoreUsers() {
        JOptionPane.showMessageDialog(null,
                "You don't have more pretendents for today", "Enough for Today", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Disable buttons.
     */
    public void disableButtons() {
        bLike.setEnabled(false);
        bDeny.setEnabled(false);
    }

    /**
     * Enable buttons.
     */
    public void enableButtons() {
        bLike.setEnabled(true);
        bDeny.setEnabled(true);
    }

    /**
     * Show is premium.
     */
    public void showIsPremium() {
        BufferedImage logoImage;
        try {
            logoImage = ImageIO.read(new File("Client/Media/Star.png"));
            logoLabel.setIcon(new ImageIcon(logoImage));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show is not premium.
     */
    public void showIsNotPremium() {
        BufferedImage logoImage;
        try {
            logoImage = ImageIO.read(new File("Client/Media/moreInfoIcon2.png"));
            logoLabel.setIcon(new ImageIcon(logoImage));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
