package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * The type Icon button.
 */
public class IconButton extends JButton {
    private Image iconImage;

    /**
     * Instantiates a new Icon button.
     *
     * @param iconPath the icon path
     */
    public IconButton(String iconPath) {
        super(new ImageIcon(iconPath));
        this.setBorder(null);
        //this.setOpaque(false);
        this.setContentAreaFilled(false);
        //this.setBorderPainted(false);
    }
}
