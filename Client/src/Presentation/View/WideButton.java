package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * The type Wide button.
 */
public class WideButton extends JPanel {
    /**
     * Instantiates a new Wide button.
     *
     * @param jButton the j button
     */
    public WideButton(JButton jButton) {
        setLayout(new BorderLayout());
        jButton.setBackground(Color.decode(MinderColor.PINK));
        jButton.setForeground(Color.WHITE);
        add(jButton, BorderLayout.NORTH);
        add(Box.createVerticalStrut(10));
        setBackground(Color.decode(MinderColor.WHITE));
    }
}
