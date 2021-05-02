package Presentation.View;

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

public class RegisterView extends JFrame{
    private static final String WINDOW_TITLE = "MINDER REGISTER";
    private static final String LOGO_TITLE = "MINDER";
    private static final String LOGO_SRC = "Client/Media/Brain.png";
    private static final String LOGO_IMG_ERR = "Error al carregar la imatge!!";
    private static final int LOGO_WIDTH = 100;
    private static final int LOGO_HIGHT = 80;
    private static final int TEXTFIELD_COLUMNS = 20;
    public static final String MOVE_TO_LOGIN = "MOVE_TO_LOGIN";
    public static final String REGISTER = "REGISTER";

    // Components
    private JPanel jPanel;
    private JPanel jpBox;
    private Image logoImage;
    private JTextField firstNameField;
    private JTextField nicknameField;
    private JTextField ageField;
    private JTextField emailField;
    private JPasswordField passwdField;
    private JPasswordField confirmPasswdField;
    private Border pinkBorder, blackBorder;
    private TitledBorder firstNameTitledBorderSelected, firstNameTitledBorderUnselected;
    private TitledBorder nicknameTitledBorderSelected, nicknameTitledBorderUnselected;
    private TitledBorder ageTitledBorderSelected, ageTitledBorderUnselected;
    private TitledBorder emailTitledBorderSelected, emailTitledBorderUnselected;
    private TitledBorder passwdTitledBorderSelected, passwdTitledBorderUnselected;
    private TitledBorder confirmPasswdTitledBorderSelected, confirmPasswdTitledBorderUnselected;
    JRadioButton jrbPremium;
    private JButton bRegister;
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
        JScrollPane jScrollPane = new JScrollPane(jPanel);
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
        JLabel logoTitle = new JLabel(LOGO_TITLE);
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
        firstNameTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "First Name");
        firstNameTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "First Name");
        // TextField
        firstNameField = new JTextField(TEXTFIELD_COLUMNS);
        firstNameField.setBorder(firstNameTitledBorderUnselected);
        // Panel
        jpBox.add(firstNameField);
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
        ageTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Age");
        ageTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Age");
        // TextField
        ageField = new JTextField(TEXTFIELD_COLUMNS);
        ageField.setBorder(ageTitledBorderUnselected);
        // Panel
        jpBox.add(ageField);
        jpBox.add(Box.createVerticalStrut(10));
    }
    void configureRadioButtons() {
        JRadioButton jrbNormal = new JRadioButton("Normal");
        jrbNormal.setBackground(Color.decode(MinderColor.WHITE));
        jrbNormal.setSelected(true);
        jrbPremium = new JRadioButton("Premium");
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
        emailTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Email");
        emailTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Email");
        // TextField
        emailField = new JTextField(TEXTFIELD_COLUMNS);
        emailField.setBorder(emailTitledBorderUnselected);
        // Panel
        jpBox.add(emailField);
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
        confirmPasswdTitledBorderUnselected = BorderFactory.createTitledBorder(blackBorder, "Confirm Password");
        confirmPasswdTitledBorderSelected = BorderFactory.createTitledBorder(pinkBorder, "Confirm Password");
        // TextField
        confirmPasswdField = new JPasswordField(TEXTFIELD_COLUMNS);
        confirmPasswdField.setBorder(confirmPasswdTitledBorderUnselected);
        // Panel
        jpBox.add(confirmPasswdField);
        jpBox.add(Box.createVerticalStrut(50));
    }
    private void configureButtons() {
        // Login
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BorderLayout());
        bRegister = new JButton("Register");
        jpButtons.add(new WideButton(bRegister), BorderLayout.NORTH);
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
    public void registerController(ActionListener actionListener) {
        firstNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                firstNameTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
                firstNameField.setBorder(firstNameTitledBorderSelected);
                jPanel.revalidate();
                jPanel.repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                firstNameTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
                firstNameField.setBorder(firstNameTitledBorderUnselected);
                jPanel.revalidate();
                jPanel.repaint();
            }
        });
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
        ageField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ageTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
                ageField.setBorder(ageTitledBorderSelected);
                jPanel.revalidate();
                jPanel.repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                ageTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
                ageField.setBorder(ageTitledBorderUnselected);
                jPanel.revalidate();
                jPanel.repaint();
            }
        });
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                emailTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
                emailField.setBorder(emailTitledBorderSelected);
                jPanel.revalidate();
                jPanel.repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                emailTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
                emailField.setBorder(emailTitledBorderUnselected);
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
        confirmPasswdField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                confirmPasswdTitledBorderSelected.setTitleColor(Color.decode(MinderColor.PINK));
                confirmPasswdField.setBorder(confirmPasswdTitledBorderSelected);
                jPanel.revalidate();
                jPanel.repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                confirmPasswdTitledBorderUnselected.setTitleColor(Color.decode(MinderColor.BLACK));
                confirmPasswdField.setBorder(confirmPasswdTitledBorderUnselected);
                jPanel.revalidate();
                jPanel.repaint();
            }
        });
        bRegister.setActionCommand(REGISTER);
        bRegister.addActionListener(actionListener);
        bLogin.setActionCommand(MOVE_TO_LOGIN);
        bLogin.addActionListener(actionListener);
    }
    public String getFirstName() {
        return firstNameField.getText();
    }
    public String getNickname() {
        return nicknameField.getText();
    }
    public String getAge() {
        return ageField.getText();
    }
    public boolean getIsPremium() { return jrbPremium.isSelected(); }
    public String getEmail() {
        return emailField.getText();
    }
    public String getPasswd() { return new String(passwdField.getPassword());}
    public String getConfirmPasswd() { return new String(confirmPasswdField.getPassword());}

    public void delete() {
        setVisible(false);
    }
    public void display() {
        setVisible(true);
    }
}