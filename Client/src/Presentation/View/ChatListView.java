package Presentation.View;

import Business.Entity.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The type Chat list view.
 */
public class ChatListView extends JPanel {
    private BufferedImage logoImage;
    private final JScrollPane schatsScroll;
    private final JPanel chatsPanel;
    private final JPanel globalChats;
    private MouseListener listener;

    /**
     * Instantiates a new Chat list view.
     */
    public ChatListView(){
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        globalChats = new JPanel(new BorderLayout());
        globalChats.setBackground(Color.white);
        globalChats.setBorder(new EmptyBorder(10, 10, 0, 0));

        chatsPanel = new JPanel();
        chatsPanel.setLayout(new BoxLayout(chatsPanel, BoxLayout.PAGE_AXIS));
        chatsPanel.setBackground(Color.white);
        globalChats.add(chatsPanel, BorderLayout.WEST);

        schatsScroll = new JScrollPane(globalChats);
        schatsScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        schatsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(schatsScroll, BorderLayout.CENTER);
    }

    /**
     * Add user chat.
     *
     * @param user  the user
     * @param image the image
     */
    public void addUserChat(User user, BufferedImage image){
        JPanel userChat = new JPanel();
        userChat.addMouseListener(this.listener);
        userChat.setBackground(Color.WHITE);
        userChat.setLayout(new BoxLayout(userChat, BoxLayout.X_AXIS));

        logoImage = null;
        try {
            if(image == null) {
                logoImage = ImageIO.read(new File("Client/Media/avatar.png"));
            }else{
                logoImage = image;
            }
            logoImage = resize(logoImage, 40, 40);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));

        JLabel name = new JLabel(user.getFirstName() + " -> Alias: " + user.getNickname());

        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        name.setAlignmentY(Component.CENTER_ALIGNMENT);
        userChat.add(logoLabel);
        userChat.add(Box.createRigidArea(new Dimension(20, 0)));
        userChat.add(name);

        chatsPanel.add(userChat);
        chatsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    /**
     * Remove chats.
     */
    public void removeChats(){ chatsPanel.removeAll(); }

    public void removeUser(String nickname) {
        for (Component c : chatsPanel.getComponents()) {
            if(c instanceof JPanel) {
                JPanel jPanel = (JPanel) c;
                JLabel jLabel = (JLabel) jPanel.getComponent(2);
                if(jLabel.getText().split(" -> Alias: ")[1].equals(nickname)){
                    chatsPanel.remove(c);
                    repaint();
                }
            }
        }
    }

    /**
     * Register controller.
     *
     * @param listener the listener
     */
    public void registerController(MouseListener listener){
        this.listener = listener;
    }

    /**
     * Resize buffered image.
     *
     * @param img  the img
     * @param newW the new w
     * @param newH the new h
     * @return the buffered image
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
