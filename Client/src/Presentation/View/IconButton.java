package Presentation.View;

import javax.swing.*;
import java.awt.*;

public class IconButton extends JButton {
    private Image iconImage;

    public IconButton(String iconPath) {
        super(new ImageIcon(iconPath));
        this.setBorder(null);
        //this.setOpaque(false);
        this.setContentAreaFilled(false);
        //this.setBorderPainted(false);
    }
//    public IconButton(String iconPath, int iconWidth, int iconHight) {
//        iconImage = new Icon(iconPath);
//        iconImage.getScaledInstance(iconWidth, iconHight, Image.SCALE_DEFAULT);
//        super(iconImage);
//        this.setBorder(null);
//        //this.setOpaque(false);
//        this.setContentAreaFilled(false);
//        //this.setBorderPainted(false);
//    }
}
