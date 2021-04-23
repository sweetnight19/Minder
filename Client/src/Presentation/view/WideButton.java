package Presentation.view;

import javax.swing.*;
import java.awt.*;

public class WideButton extends JPanel{
    public WideButton(String nameButton) {
        setLayout(new BorderLayout());
        JButton widthButton = new JButton(nameButton);
        widthButton.setBackground(Color.decode(MinderColor.PINK));
        widthButton.setForeground(Color.WHITE);
        add(widthButton, BorderLayout.NORTH);
        add(Box.createVerticalStrut(10));
        setBackground(Color.decode(MinderColor.WHITE));
    }
}
