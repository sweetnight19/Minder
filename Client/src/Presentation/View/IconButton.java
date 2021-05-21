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
}
