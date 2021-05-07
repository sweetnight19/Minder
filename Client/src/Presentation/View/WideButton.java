package Presentation.View;

import javax.swing.*;
import java.awt.*;

public class WideButton extends JPanel{
    public WideButton(JButton jButton) {
        setLayout(new BorderLayout());
        jButton.setBackground(Color.decode(MinderColor.PINK));
        jButton.setForeground(Color.WHITE);
        add(jButton, BorderLayout.NORTH);
        add(Box.createVerticalStrut(10));
        setBackground(Color.decode(MinderColor.WHITE));
    }
}
