package Presentation.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The type Chat direct view.
 */
public class ChatDirectView extends JFrame {
    /**
     * The constant BTN_SEND.
     */
    public static final String BTN_SEND = "BUT_SEND";
    /**
     * The constant BTN_DISLIKE.
     */
    public static final String BTN_DISLIKE = "DISLIKE";

    private PanelCustomWrap jPanel;
    private JScrollPane jspComments;
    private JTextField textMessage;
    private JButton jbutIcon;
    private JLabel name;
    private JLabel logoLabel;
    private JButton dislike;

    /**
     * Instantiates a new Chat direct view.
     */
    public ChatDirectView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        configureWindow();

        add(configureNorth(), BorderLayout.NORTH);
        add(configureCenter(), BorderLayout.CENTER);
        add(configureSouth(), BorderLayout.SOUTH);
    }

    /**
     * Configure window.
     */
    public void configureWindow() {
        setTitle("ChatDirect");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(350, 500);
        setLocationRelativeTo(null);
    }


    private JScrollPane configureCenter() {
        JPanel jglobal = new JPanel(new BorderLayout());
        jglobal.setBackground(Color.white);
        jglobal.setOpaque(true);
        jPanel = new PanelCustomWrap();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
        jPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        jPanel.setBackground(Color.white);
        jglobal.add(jPanel, BorderLayout.SOUTH);
        jspComments = new JScrollPane(jglobal, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspComments.setPreferredSize(new Dimension(250, 300));
        return jspComments;
    }


    private JPanel configureSouth() {
        JPanel jsouth = new JPanel(new BorderLayout());
        jsouth.setBackground(Color.WHITE);

        JPanel jinput = new JPanel();
        textMessage = new JTextField();
        textMessage.setColumns(20);
        textMessage.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#DF4B74"), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        jinput.setBackground(Color.WHITE);
        jinput.add(textMessage);

        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("Client/Media/sendIcon.png"));
            logoImage = resize(logoImage, 20, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jbutIcon = new JButton(new ImageIcon(logoImage));
        jbutIcon.setBackground(Color.WHITE);
        jbutIcon.setBorder(new EmptyBorder(0, 0, 0, 10));

        jsouth.add(jinput, BorderLayout.CENTER);
        jsouth.add(jbutIcon, BorderLayout.EAST);
        return jsouth;
    }

    private JPanel configureNorth() {
        JPanel north = new JPanel();
        north.setBackground(Color.WHITE);
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));

        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("Client/Media/avatar.png"));
            logoImage = resize(logoImage, 40, 40);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoLabel = new JLabel(new ImageIcon(logoImage));

        BufferedImage logoDisLike = null;
        try {
            logoDisLike = ImageIO.read(new File("Client/Media/disMatchIcon.png"));
            logoDisLike = resize(logoDisLike, 30, 30);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dislike = new JButton(new ImageIcon(logoDisLike));
        dislike.setBackground(Color.WHITE);
        dislike.setOpaque(true);
        dislike.setBorderPainted(false);

        name = new JLabel("Edmon Bosch");

        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        name.setAlignmentY(Component.CENTER_ALIGNMENT);
        dislike.setAlignmentY(Component.CENTER_ALIGNMENT);
        north.add(logoLabel);
        north.add(Box.createRigidArea(new Dimension(20, 0)));
        north.add(name);
        north.add(Box.createRigidArea(new Dimension(50, 0)));
        north.add(dislike);
        return north;
    }

    private JPanel configureSelfMessage(String message) {
        PanelCustomWrap jbox = new PanelCustomWrap(new BorderLayout());
        jbox.setBackground(Color.WHITE);

        JTextArea jtext = new JTextArea(0, 20);
        jtext.setText(message);
        jtext.setEditable(false);
        jtext.setLineWrap(true);
        jtext.setWrapStyleWord(true);

        jtext.setFont(jtext.getFont().deriveFont(Font.PLAIN));
        jtext.setForeground(Color.white);
        jtext.setBorder(new EmptyBorder(5, 10, 5, 10));
        jtext.setBackground(Color.decode("#E27B97"));

        jbox.add(jtext, BorderLayout.EAST);
        return jbox;
    }

    private JPanel configureMessage(String message) {
        PanelCustomWrap jbox = new PanelCustomWrap(new BorderLayout());
        jbox.setBackground(Color.WHITE);

        JTextArea jtext = new JTextArea(0, 20);
        jtext.setText(message);
        jtext.setEditable(false);
        jtext.setLineWrap(true);
        jtext.setWrapStyleWord(true);

        jtext.setFont(jtext.getFont().deriveFont(Font.PLAIN));
        jtext.setForeground(Color.WHITE);
        jtext.setBackground(Color.decode("#DF4B74"));
        jtext.setBorder(new EmptyBorder(5, 10, 5, 10));

        jbox.add(jtext, BorderLayout.WEST);
        return jbox;
    }

    /**
     * Register button controller.
     *
     * @param listener the listener
     */
    public void registerButtonController(ActionListener listener) {
        jbutIcon.setActionCommand(BTN_SEND);
        jbutIcon.addActionListener(listener);

        dislike.setActionCommand(BTN_DISLIKE);
        dislike.addActionListener(listener);
    }

    /**
     * Register window controller.
     *
     * @param listener the listener
     */
    public void registerWindowController(WindowListener listener) {
        this.addWindowListener(listener);
    }

    /**
     * Update north.
     *
     * @param icon        the icon
     * @param nameDestiny the name destiny
     */
    public void updateNorth(ImageIcon icon, String nameDestiny) {
        logoLabel.setIcon(icon);
        name.setText(nameDestiny);
    }

    /**
     * Get text field message string.
     *
     * @return the string
     */
    public String getTextFieldMessage() {
        return textMessage.getText();
    }

    /**
     * Set text field hint.
     */
    public void setTextFieldHint() {
        textMessage.setText("");
    }

    /**
     * Add own message.
     *
     * @param message the message
     */
    public void addOwnMessage(String message) {
        jPanel.add(configureSelfMessage(message), BorderLayout.SOUTH);
        jPanel.add(Box.createVerticalStrut(10));
        revalidate();
        repaint();
        barScrollSetUp();
    }

    /**
     * Add friend message.
     *
     * @param message the message
     */
    public void addFriendMessage(String message) {
        jPanel.add(configureMessage(message), BorderLayout.SOUTH);
        jPanel.add(Box.createVerticalStrut(10));
        revalidate();
        repaint();
        barScrollSetUp();
    }

    /**
     * Bar scroll set up.
     */
    public void barScrollSetUp() {
        JScrollBar sb = jspComments.getVerticalScrollBar();
        sb.setValue(sb.getMaximum());
    }


    /**
     * Eliminate view.
     */
    public void eliminateView() {
        dispose();
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
