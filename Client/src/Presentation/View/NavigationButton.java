package Presentation.View;

import javax.swing.*;

public class NavigationButton extends JButton {
    private final ImageIcon disabledIcon;
    private final ImageIcon enabledIcon;

    public NavigationButton(String disabledPath, String enabledPath) {
        this.disabledIcon = new ImageIcon(disabledPath);
        this.enabledIcon = new ImageIcon(enabledPath);
        this.setIcon(disabledIcon);
        this.setBorder(null);
        this.setContentAreaFilled(false);

    }

    public void changeToDisable() {
        this.setIcon(disabledIcon);
    }

    public void changeToEnable() {
        this.setIcon(enabledIcon);
    }
}
