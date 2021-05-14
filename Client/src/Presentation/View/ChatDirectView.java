package Presentation.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChatDirectView extends JPanel {
    public static final String BTN_SEND = "BUT_SEND";

    private PanelCustomWrap jPanel;
    private JScrollPane jspComments;
    private JTextField textMessage;
    private JButton jbutIcon;

    /*
    Constructor que afegeix els elements i configuracion necessàries a la vista
     */
    public ChatDirectView(){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(configureNorth(), BorderLayout.NORTH);
        add(configureCenter(), BorderLayout.CENTER);
        add(configureSouth(), BorderLayout.SOUTH);
    }

    /*
    Configuració de la part que ocupa el xat en el centre de la pantalla
     */
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
        jspComments.setPreferredSize(new Dimension(250,300));
        return jspComments;
    }

    /*
    Configuració del sud de la pantalla on està el botó per fer HONK
     */
    private JPanel configureSouth() {
        JPanel jsouth = new JPanel(new BorderLayout());
        jsouth.setBackground(Color.WHITE);

        JPanel jinput = new JPanel();
        textMessage = new JTextField();
        textMessage.setColumns(20);
        textMessage.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#DF4B74"), 1, true),
                BorderFactory.createEmptyBorder(5,10,5,10)));
        jinput.setBackground(Color.WHITE);
        jinput.add(textMessage);

        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("src/Media/sendIcon.png"));
            logoImage = resize(logoImage, 20, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jbutIcon = new JButton(new ImageIcon(logoImage));
        jbutIcon.setBackground(Color.WHITE);
        jbutIcon.setBorder(new EmptyBorder(0,0,0,10));

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
            logoImage = ImageIO.read(new File("C:\\Users\\edmon\\Downloads\\profileTest.jpg"));
            logoImage = resize(logoImage, 40, 40);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));

        JLabel name = new JLabel("Edmon Bosch");

        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        name.setAlignmentY(Component.CENTER_ALIGNMENT);
        north.add(logoLabel);
        north.add(Box.createRigidArea(new Dimension(20, 0)));
        north.add(name);
        return north;
    }

    /*
    Funció per crear el panell del nostre propi missatge HONK
     */
    private JPanel configureSelfMessage(String message){
        PanelCustomWrap jbox = new PanelCustomWrap(new BorderLayout());
        jbox.setBackground(Color.WHITE);

        JTextArea jtext = new JTextArea(0,20);
        jtext.setText(message);
        jtext.setEditable(false);
        jtext.setLineWrap(true);
        jtext.setWrapStyleWord(true);

        jtext.setFont(jtext.getFont().deriveFont(Font.PLAIN));
        jtext.setForeground(Color.white);
        jtext.setBorder(new EmptyBorder(5,10,5,10));
        jtext.setBackground(Color.decode("#E27B97"));

        jbox.add(jtext, BorderLayout.EAST);
        return jbox;
    }

    /*
    Funció per crear el panell dels missatges HONK que ens envien els altres usuaris
     */
    private JPanel configureMessage(String message){
        PanelCustomWrap jbox = new PanelCustomWrap(new BorderLayout());
        jbox.setBackground(Color.WHITE);

        JTextArea jtext = new JTextArea(0,20);
        jtext.setText(message);
        jtext.setEditable(false);
        jtext.setLineWrap(true);
        jtext.setWrapStyleWord(true);

        jtext.setFont(jtext.getFont().deriveFont(Font.PLAIN));
        jtext.setForeground(Color.WHITE);
        jtext.setBackground(Color.decode("#DF4B74"));
        jtext.setBorder(new EmptyBorder(5,10,5,10));

        jbox.add(jtext, BorderLayout.WEST);
        return jbox;
    }

    /*
    Registrem el controlador ButtonController que s'encarregarà d'escoltar quan prenem el botó
     */
    public void registerButtonController(ActionListener listener){
        jbutIcon.setActionCommand(BTN_SEND);
        jbutIcon.addActionListener(listener);
    }

    public String getTextFieldMessage(){
        return textMessage.getText();
    }

    public void setTextFieldHint(){
        textMessage.setText("");
    }

    /*
    Funció que afegeix a la vista quan enviem nosaltres mateixos un missatge
     */
    public void addOwnMessage(String message){
        jPanel.add(configureSelfMessage(message), BorderLayout.SOUTH);
        jPanel.add(Box.createVerticalStrut(10));
        revalidate();
        repaint();
        barScrollSetUp();
    }

    /*
    Funció que afegeix a la vista quan ens envien missatges altres usuaris
     */
    public void addFriendMessage(String message){
        jPanel.add(configureMessage(message), BorderLayout.SOUTH);
        jPanel.add(Box.createVerticalStrut(10));
        revalidate();
        repaint();
        barScrollSetUp();
    }

    /*
    Funció que aconsegueix que la barra de scroll sempre es mantingui a baix de tot per fer visible
    els últims missatges rebuts o enviats
     */
    public void barScrollSetUp(){
        JScrollBar sb = jspComments.getVerticalScrollBar();
        sb.setValue( sb.getMaximum() );
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
