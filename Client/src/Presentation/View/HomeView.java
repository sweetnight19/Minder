package Presentation.View;

import Business.Entity.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class HomeView extends JPanel {
    public static final String LIKE = "LIKE";
    public static final String DENY = "DENY";

    private JPanel pUser;
    private PhotoPanel photoPanel;
    private JLabel lAge;
    private JLabel lName;
    private JButton bLike;
    private JButton bDeny;

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
        IconButton bMoreInfo = new IconButton("Client/Media/moreInfoIcon2.png");
        pInfoUser.add(bMoreInfo, BorderLayout.EAST);

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

    public void nextUser() {

    }

    /*
    private void configureCenter() {
        JPanel pUser = new JPanel();
        pUser.setLayout(new BorderLayout());
        // User Photo
        PhotoPanel photoPanel = new PhotoPanel("C:\\Users\\Xavi\\Desktop\\ImageTest\\image1763433(4)");
        pUser.add(photoPanel, BorderLayout.CENTER);
        // Info User
        JPanel pInfoUser = new JPanel();
        pInfoUser.setLayout(new BorderLayout());
        Border infoBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        pInfoUser.setBorder(infoBorder);
        pInfoUser.setBackground(Color.decode(MinderColor.PINK));
        // Age
        JLabel lAge = new JLabel("20");
        lAge.setForeground(Color.decode(MinderColor.WHITE));
        pInfoUser.add(lAge, BorderLayout.WEST);
        // Name
        JLabel lName = new JLabel("asdfasdfas", SwingConstants.CENTER);
        lName.setForeground(Color.decode(MinderColor.WHITE));
        pInfoUser.add(lName, BorderLayout.CENTER);
        // More Info
        IconButton bMoreInfo = new IconButton("Client/Media/moreInfoIcon2.png");
        pInfoUser.add(bMoreInfo, BorderLayout.EAST);

        pInfoUser.add(lName);
        pUser.add(pInfoUser, BorderLayout.SOUTH);

        add(pUser);
    }
     */
}
