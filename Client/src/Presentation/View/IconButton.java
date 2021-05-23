package Presentation.View;

import javax.swing.*;

/**
 * The type Icon button.
 */
public class IconButton extends JButton {

    /**
     * Instantiates a new Icon button.
     *
     * @param iconPath the icon path
     */
    public IconButton(String iconPath) {
        super(new ImageIcon(iconPath));
        this.setBorder(null);
        this.setContentAreaFilled(false);
    }
}
