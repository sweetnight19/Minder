package Presentation.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The type Check login gui.
 */
public class CheckLoginGUI extends JFrame {
    private final String[] data = {"Java", "Javascript", "html", "C++"};
    /**
     * The constant SAVE_BUTTON.
     */
    public static final String SAVE_BUTTON = "SAVE_BUTTON";
    /**
     * The constant CHOOSE_IMAGE_BUTTON.
     */
    public static final String CHOOSE_IMAGE_BUTTON = "CHOOSE_IMAGE_BUTTON";
    private JButton jSaveBtn;
    private JButton jchooseImage;
    private JTextArea jdesc;
    private JComboBox<String> jlanguage;
    private JLabel logoLabel;
    private BufferedImage logoImage;


    /**
     * Instantiates a new Check login gui.
     */
    public CheckLoginGUI() {
        setTitle("Fill profile information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 500);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);

        JScrollPane sp = new JScrollPane(centerOption());
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(sp, BorderLayout.CENTER);
        add(southButton(), BorderLayout.SOUTH);
    }

    private JPanel centerOption() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        center.setBackground(Color.WHITE);

        logoImage = null;
        try {
            logoImage = ImageIO.read(new File("Client/Media/avatar.png"));
            logoImage = resize(logoImage, 70, 70);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setBorder(new EmptyBorder(20, 20, 20, 20));


        jchooseImage = new JButton("CHOOSE PHOTO");
        jchooseImage.setBackground(Color.decode("#DF4B74"));
        jchooseImage.setForeground(Color.WHITE);
        jchooseImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        jchooseImage.setBorder(new EmptyBorder(10, 10, 10, 10));


        jlanguage = new JComboBox<>(data);
        jlanguage.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlanguage.setBackground(Color.white);
        jlanguage.setBorder(new EmptyBorder(50, 10, 50, 10));

        jdesc = new JTextArea(0, 0);
        jdesc.setLineWrap(true);
        jdesc.setWrapStyleWord(true);
        jdesc.setRows(2);
        jdesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        jdesc.setBackground(Color.white);
        jdesc.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        jdesc.setText("Introduce here the description that defines yourself.");

        center.add(logoLabel);
        center.add(jchooseImage);
        center.add(jlanguage);
        center.add(jdesc);
        center.setBorder(new EmptyBorder(30, 10, 30, 10));
        return center;
    }

    private JPanel southButton() {
        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(Color.WHITE);

        jSaveBtn = new JButton("SAVE");
        jSaveBtn.setBackground(Color.decode("#DF4B74"));
        jSaveBtn.setForeground(Color.WHITE);

        south.add(jSaveBtn, BorderLayout.CENTER);
        south.setBorder(new EmptyBorder(10, 10, 10, 10));
        return south;
    }

    /**
     * Register controller.
     *
     * @param actionListener the action listener
     */
    public void registerController(ActionListener actionListener) {
        jSaveBtn.setActionCommand(SAVE_BUTTON);
        jSaveBtn.addActionListener(actionListener);

        jchooseImage.setActionCommand(CHOOSE_IMAGE_BUTTON);
        jchooseImage.addActionListener(actionListener);
    }

    /**
     * Display.
     */
    public void display() {
        setVisible(true);
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return jdesc.getText();
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return jlanguage.getSelectedItem().toString();
    }

    /**
     * Delete.
     */
    public void delete() {
        setVisible(false);
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

    /**
     * Sets new image.
     *
     * @param image the image
     */
    public void setNewImage(BufferedImage image) {
        logoImage = null;
        logoImage = resize(image, 100, 100);
        logoLabel.setIcon(new ImageIcon(logoImage));
    }

}
