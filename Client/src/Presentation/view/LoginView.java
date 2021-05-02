package Presentation.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class LoginView extends JFrame {
    private static final String WINDOW_TITLE = "MINDER LOGIN";
    private static final String LOGO_TITLE = "MINDER";
    private static final String LOGO_SRC = "Client/Media/Brain.png";
    private static final String LOGO_IMG_ERR = "Error al carregar la imatge!!";
    private static final int LOGO_WIDTH = 100;
    private static final int LOGO_HIGHT = 80;
    private static final int TEXTFIELD_COLUMNS = 20;
    public static final String MOVE_TO_REGISTER = "MOVE_TO_REGISTER";
    public static final String LOGIN = "LOGIN";

    // Components
    private JPanel jPanel;
    private JPanel jpBox;
    private Image logoImage;
    private JTextField nicknameField;
    private JPasswordField passwdField;
    private Border pinkBorder, blackBorder;
    private TitledBorder nicknameTitledBorderSelected, nicknameTitledBorderUnselected;
    private TitledBorder passwdTitledBorderSelected, passwdTitledBorderUnselected;
    private JButton bLogin;
    private JButton bRegister;

    public LoginView() {
        configureWindow();
        configureNorth();
        pack();
        setLocationRelativeTo(null);
    }

    private void configureWindow() {
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Color.decode(MinderColor.WHITE));
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane);
    }

    private void configureNorth() {
        jpBox = new JPanel();
        jpBox.setLayout(new BoxLayout(jpBox, BoxLayout.Y_AXIS));
        jpBox.setBackground(Color.decode(MinderColor.WHITE));
        jPanel.add(jpBox, BorderLayout.NORTH);
        Border generalBorder = BorderFactory.createEmptyBorder(50, 60, 50, 60);
        jPanel.setBorder(generalBorder);
        try {
            logoImage = ImageIO.read(new File(LOGO_SRC));
            configureLogo();
        } catch (IOException ex) {
            System.out.println(LOGO_IMG_ERR);
        }
        configureFields();
        configureButtons();
    }

    private void configureLogo() {
        // Image
        logoImage = logoImage.getScaledInstance(LOGO_WIDTH, LOGO_HIGHT, Image.SCALE_DEFAULT);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        // Title
        JLabel logoTitle = new JLabel(LOGO_TITLE);
        Font logoFont = logoTitle.getFont().deriveFont(Font.BOLD, 30);
        logoTitle.setFont(logoFont);
        // Panel
        JPanel jpLogo = new JPanel();
        jpLogo.setBackground(Color.decode(MinderColor.WHITE));
        jpLogo.add(logoLabel);
        jpLogo.add(logoTitle);
        jpBox.add(jpLogo, BorderLayout.NORTH);
        jpBox.add(Box.createVerticalStrut(100));
    }

    private void configureFields() {
        // Border
        blackBorder = new LineBorder(Color.decode(MinderColor.BLACK));
        pinkBorder = new LineBorder(Color.decode(MinderColor.PINK));
        configureNickname();
        configurePasswd();
    }

    private void configureNickname() {
        // TitledBorder
        nicknameTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Nickname");
        nicknameTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Nickname");
        // TextField
        nicknameField = new JTextField(TEXTFIELD_COLUMNS);
        nicknameField.setBorder(nicknameTitledBorderUnselected);
        // Panel
        jpBox.add(nicknameField);
        jpBox.add(Box.createVerticalStrut(30));
    }

    private void configurePasswd() {
        // TitledBorder
        passwdTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Password");
        passwdTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Password");
        // TextField
        passwdField = new JPasswordField(TEXTFIELD_COLUMNS);
        passwdField.setBorder(passwdTitledBorderUnselected);
        // Panel
        jpBox.add(passwdField);
        jpBox.add(Box.createVerticalStrut(50));
    }

    private void configureButtons() {
        // Login
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BorderLayout());
        bLogin = new JButton("Log in");
        jpButtons.add(new WideButton(bLogin), BorderLayout.NORTH);
        // Register Text
        JPanel jpRegister = new JPanel();
        jpRegister.setLayout(new BorderLayout());
        jpRegister.setBackground(Color.decode(MinderColor.WHITE));
        JLabel lRegister = new JLabel("Don't have an account?");
        jpRegister.add(lRegister, BorderLayout.WEST);
        // Tegister Button
        bRegister = new JButton("Register");
        bRegister.setForeground(Color.decode(MinderColor.PINK));
        bRegister.setBackground(Color.decode(MinderColor.WHITE));
        bRegister.setBorderPainted(false);
        jpRegister.add(bRegister, BorderLayout.EAST);
        jpButtons.add(jpRegister, BorderLayout.SOUTH);
        // Panel
        jPanel.add(jpButtons, BorderLayout.SOUTH);
    }

    public void registerController(FocusListener focusListener, ActionListener actionListener) {
        nicknameField.addFocusListener(focusListener);
        passwdField.addFocusListener(focusListener);
        bRegister.setActionCommand(MOVE_TO_REGISTER);
        bRegister.addActionListener(actionListener);
    }

    public void nicknameSelected() {
        nicknameTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
        nicknameField.setBorder(nicknameTitledBorderSelected);
        jPanel.revalidate();
        jPanel.repaint();
    }

    public void nicknameUnselected() {
        nicknameTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
        nicknameField.setBorder(nicknameTitledBorderUnselected);
        jPanel.revalidate();
        jPanel.repaint();
    }

    public void passwdSelected() {
        passwdTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
        passwdField.setBorder(passwdTitledBorderSelected);
        jPanel.revalidate();
        jPanel.repaint();
    }

    public void passwdUnselected() {
        passwdTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
        passwdField.setBorder(passwdTitledBorderUnselected);
        jPanel.revalidate();
        jPanel.repaint();
    }

    public void registerController(ActionListener actionListener) {
        nicknameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nicknameTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
                nicknameField.setBorder(nicknameTitledBorderSelected);
                jPanel.revalidate();
                jPanel.repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                nicknameTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
                nicknameField.setBorder(nicknameTitledBorderUnselected);
                jPanel.revalidate();
                jPanel.repaint();
            }
        });
        passwdField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwdTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
                passwdField.setBorder(passwdTitledBorderSelected);
                jPanel.revalidate();
                jPanel.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwdTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
                passwdField.setBorder(passwdTitledBorderUnselected);
                jPanel.revalidate();
                jPanel.repaint();
            }
        });
        bLogin.setActionCommand(LOGIN);
        bLogin.addActionListener(actionListener);
        bRegister.setActionCommand(MOVE_TO_REGISTER);
        bRegister.addActionListener(actionListener);
    }
    public String getNickname() {
       return nicknameField.getText();
    }
    public String getPasswd() {
        return new String(passwdField.getPassword());
    }
    public void delete() {
        setVisible(false);
    }
    public void display() {
        setVisible(true);
    }
}