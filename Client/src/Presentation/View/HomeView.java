package Presentation.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
    public static final String LIKE = "LIKE";
    public static final String DENY = "DENY";

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

    private void configureCenter() {
        JPanel pUser = new JPanel();
        pUser.setLayout(new BorderLayout());
        // User Photo
        PhotoPanel photoPanel = new PhotoPanel("Client/Media/person.png");
        pUser.add(photoPanel, BorderLayout.CENTER);
        // Info User
        JPanel pInfoUser = new JPanel();
        pInfoUser.setLayout(new BorderLayout());
        Border infolBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        pInfoUser.setBorder(infolBorder);
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
}
