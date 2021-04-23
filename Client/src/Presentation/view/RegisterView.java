package Presentation.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class RegisterView extends JFrame{
    private static final String WINDOW_TITLE = "MINDER REGISTER";
    private static final String LOGO_TITLE = "MINDER";
    private static final String LOGO_SRC = "Client/Media/Brain.png";
    private static final String LOGO_IMG_ERR = "Error al carregar la imatge!!";
    protected static final String NICK = "NICK";
    protected static final String PASSWD = "PASSWD";
    private static final int LOGO_WIDTH = 100;
    private static final int LOGO_HIGHT = 80;
    private static final int TEXTFIELD_COLUMNS = 20;

    // Components
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JPanel jpBox;
    private JPanel jpButtons;
    private Image logoImage;
    private JLabel logoTitle;
    private JTextField nicknameField;
    private JPasswordField passwdField;
    private Border pinkBorder, blackBorder;
    private TitledBorder nicknameTitledBorderSelected, nicknameTitledBorderUnselected;
    private TitledBorder passwdTitledBorderSelected, passwdTitledBorderUnselected;
    private JButton bLogin;

    public RegisterView() {
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
        jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane);
    }
    private void configureNorth() {
        jpBox = new JPanel();
        jpBox.setLayout(new BoxLayout(jpBox, BoxLayout.Y_AXIS));
        jpBox.setBackground(Color.decode(MinderColor.WHITE));
        jPanel.add(jpBox, BorderLayout.NORTH);
        Border generalBorder = BorderFactory.createEmptyBorder(50,60,50,60);
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
        logoTitle = new JLabel(LOGO_TITLE);
        Font logoFont = logoTitle.getFont().deriveFont(Font.BOLD, 30);
        logoTitle.setFont(logoFont);
        // Panel
        JPanel jpLogo = new JPanel();
        jpLogo.setBackground(Color.decode(MinderColor.WHITE));
        jpLogo.add(logoLabel);
        jpLogo.add(logoTitle);
        jpBox.add(jpLogo, BorderLayout.NORTH);
        jpBox.add(Box.createVerticalStrut(50));
    }
    private void configureFields() {
        // Border
        blackBorder = new LineBorder(Color.decode(MinderColor.BLACK));
        pinkBorder = new LineBorder(Color.decode(MinderColor.PINK));
        configureFirstName();
        configureNickname();
        configureAge();
        configureRadioButtons();
        configureEmail();
        configurePasswd();
        configureConfirmPasswd();
    }
    private void configureFirstName() {
        // TitledBorder
        nicknameTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "First Name");
        nicknameTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "First Name");
        // TextField
        nicknameField = new JTextField(TEXTFIELD_COLUMNS);
        nicknameField.setBorder(nicknameTitledBorderUnselected);
        // Panel
        jpBox.add(nicknameField);
        jpBox.add(Box.createVerticalStrut(10));
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
        jpBox.add(Box.createVerticalStrut(10));
    }
    private void configureAge() {
        // TitledBorder
        nicknameTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Age");
        nicknameTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Age");
        // TextField
        nicknameField = new JTextField(TEXTFIELD_COLUMNS);
        nicknameField.setBorder(nicknameTitledBorderUnselected);
        // Panel
        jpBox.add(nicknameField);
        jpBox.add(Box.createVerticalStrut(10));
    }
    void configureRadioButtons() {
        JRadioButton jrbNormal = new JRadioButton("Normal");
        jrbNormal.setBackground(Color.decode(MinderColor.WHITE));
        jrbNormal.setSelected(true);
        JRadioButton jrbPremium = new JRadioButton("Premium");
        jrbPremium.setBackground(Color.decode(MinderColor.WHITE));
        ButtonGroup group = new ButtonGroup();
        group.add(jrbNormal);
        group.add(jrbPremium);
        JPanel jpRadioButtons = new JPanel();
        jpRadioButtons.setLayout(new BorderLayout());
        jpRadioButtons.setBackground(Color.decode(MinderColor.WHITE));
        jpRadioButtons.add(jrbNormal, BorderLayout.WEST);
        jpRadioButtons.add(jrbPremium, BorderLayout.EAST);
        jpBox.add(jpRadioButtons);
    }
    private void configureEmail() {
        // TitledBorder
        nicknameTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Email");
        nicknameTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Email");
        // TextField
        nicknameField = new JTextField(TEXTFIELD_COLUMNS);
        nicknameField.setBorder(nicknameTitledBorderUnselected);
        // Panel
        jpBox.add(nicknameField);
        jpBox.add(Box.createVerticalStrut(10));
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
        jpBox.add(Box.createVerticalStrut(10));
    }
    private void configureConfirmPasswd() {
        // TitledBorder
        passwdTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Confirm Password");
        passwdTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Confirm Password");
        // TextField
        passwdField = new JPasswordField(TEXTFIELD_COLUMNS);
        passwdField.setBorder(passwdTitledBorderUnselected);
        // Panel
        jpBox.add(passwdField);
        jpBox.add(Box.createVerticalStrut(50));
    }
    private void configureButtons() {
        // Login
        jpButtons = new JPanel();
        jpButtons.setLayout(new BorderLayout());
        jpButtons.add(new WideButton("Register"), BorderLayout.NORTH);
        // Register Text
        JPanel jpRegister = new JPanel();
        jpRegister.setLayout(new BorderLayout());
        jpRegister.setBackground(Color.decode(MinderColor.WHITE));
        JLabel lRegister = new JLabel("Already have an account?");
        jpRegister.add(lRegister, BorderLayout.WEST);
        // Login Button
        bLogin = new JButton("Log in");
        bLogin.setForeground(Color.decode(MinderColor.PINK));
        bLogin.setBackground(Color.decode(MinderColor.WHITE));
        bLogin.setBorderPainted(false);
        jpRegister.add(bLogin, BorderLayout.EAST);
        jpButtons.add(jpRegister, BorderLayout.SOUTH);
        // Panel
        jPanel.add(jpButtons, BorderLayout.SOUTH);
    }
    public void registerController(FocusListener focusListener, ActionListener actionListener) {
        nicknameField.setName(NICK);
        passwdField.setName(PASSWD);
        nicknameField.addFocusListener(focusListener);
        passwdField.addFocusListener(focusListener);
        bLogin.setActionCommand("LOGIN");
        bLogin.addActionListener(actionListener);
    }
    public void firstNameSelected() {
        nicknameTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
        nicknameField.setBorder(nicknameTitledBorderSelected);
        jPanel.revalidate();
        jPanel.repaint();
    }
    public void firstNameUnselected() {
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
    public void display() {
        setVisible(true);
    }
}