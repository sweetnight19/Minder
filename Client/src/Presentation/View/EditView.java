package Presentation.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EditView extends JPanel {
    private JLabel jname;
    private JLabel jage;
    private JComboBox<String> jlanguage;
    private JLabel jtype;
    private JLabel jemail;
    private JTextArea jdesc;
    private JScrollPane sp;
    private BufferedImage logoImage;
    private String[] data = { "Java", "Javascript", "html", "C++" };

    public EditView(){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setOpaque(true);

        JPanel wrapper = new PanelCustomWrap(new BorderLayout());
        wrapper.add(profileInformation(), BorderLayout.NORTH);
        wrapper.add(centerTextArea(), BorderLayout.CENTER);
        wrapper.setBackground(Color.WHITE);

        sp = new JScrollPane(wrapper);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setBackground(Color.WHITE);

        add(southButtons(), BorderLayout.SOUTH);
        add(sp, BorderLayout.CENTER);
        updateData();
    }

    private JPanel profileInformation(){
        JPanel profile = new JPanel();
        profile.setBackground(Color.WHITE);

        profile.setLayout(new BoxLayout(profile, BoxLayout.PAGE_AXIS));
        profile.setBorder(new EmptyBorder(10,10,10,10));

        setBorders();

        JPanel imagePanel = imageChange();
        imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        profile.add(imagePanel);
        profile.add(jname);
        profile.add(jage);
        profile.add(jlanguage);
        profile.add(jtype);
        profile.add(jemail);

        return profile;
    }

    private void setBorders(){
        jname = new JLabel();
        jname.setAlignmentX(Component.LEFT_ALIGNMENT);
        jname.setBorder(new EmptyBorder(25,0 ,15,5));

        jage = new JLabel();
        jage.setAlignmentX(Component.LEFT_ALIGNMENT);
        jage.setBorder(new EmptyBorder(10,0 ,15,5));

        jlanguage = new JComboBox<>(data);
        jlanguage.setAlignmentX(Component.LEFT_ALIGNMENT);
        jlanguage.setBackground(Color.white);
        jlanguage.setBorder(new EmptyBorder(5,0 ,5,5));

        jemail = new JLabel();
        jemail.setAlignmentX(Component.LEFT_ALIGNMENT);
        jemail.setBorder(new EmptyBorder(10,0 ,15,5));

        jtype = new JLabel();
        jtype.setAlignmentX(Component.LEFT_ALIGNMENT);
        jtype.setBorder(new EmptyBorder(10,0 ,15,5));
    }

    private JTextArea centerTextArea(){
        jdesc = new JTextArea(0,0);
        jdesc.setLineWrap(true);
        jdesc.setWrapStyleWord(true);
        jdesc.setRows(2);
        jdesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        jdesc.setBackground(Color.white);
        jdesc.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1, true),
                BorderFactory.createEmptyBorder(5,5 ,5,5)));
        return jdesc;
    }

    private  JPanel imageChange(){
        JPanel changeImage = new JPanel();
        changeImage.setBackground(Color.WHITE);
        changeImage.setLayout(new BoxLayout(changeImage, BoxLayout.X_AXIS));

        logoImage = null;
        try {
            logoImage = ImageIO.read(new File("C:\\Users\\edmon\\Downloads\\javaJoan.png"));
            logoImage = resize(logoImage, 70, 70);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));

        JButton changebtn = new JButton("Change");
        changebtn.setBackground(Color.decode("#DF4B74"));
        changebtn.setForeground(Color.WHITE);

        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        changebtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        changeImage.add(logoLabel);
        changeImage.add(Box.createRigidArea(new Dimension(20, 0)));
        changeImage.add(changebtn);

        return  changeImage;
    }

    private JPanel southButtons(){
        JPanel butconatiner = new JPanel(new BorderLayout());
        butconatiner.setBackground(Color.WHITE);

        JButton southEdit = new JButton("SAVE");
        southEdit.setBackground(Color.decode("#DF4B74"));
        southEdit.setForeground(Color.WHITE);

        JButton southDelete = new JButton("Delete");
        southDelete.setBackground(Color.WHITE);
        southDelete.setForeground(Color.decode("#DF4B74"));

        butconatiner.setBorder(new EmptyBorder(10,10,10,10));
        butconatiner.add(southEdit, BorderLayout.EAST);
        butconatiner.add(southDelete, BorderLayout.WEST);
        return butconatiner;
    }

    /*public void updateData(User user, BufferedImage image){
        logoImage = resize(image, 70, 70);
        jname.setText(user.getName());
        jage.setText(user.getAge + " years");
        jlanguage.setSelectedItem(user.getProgrammingLanguage());
        jemail.setText(user.getEmail());
        jtype.setText(user.getType());
        jdesc.setText(user.getDescription());
    }*/

    public void updateData(){
        //logoImage = resize(image, 70, 70);
        jname.setText("Edmon Bosch");
        jage.setText("21 years");
        jlanguage.setSelectedItem("Javascript");
        jemail.setText("edmonbosch@gmail.com");
        jtype.setText("Premium");
        jdesc.setText("Estudio GTAS i soc de Girona, m'agrada mirar crims amb els amics del pis mentres bec una cervesa.");
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
