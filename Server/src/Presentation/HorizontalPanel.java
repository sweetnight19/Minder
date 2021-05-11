package Presentation;

import javax.swing.*;
import java.awt.*;

public class HorizontalPanel extends JPanel {

    public HorizontalPanel() {
        setPreferredSize(new Dimension(0, 25));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = new Font("Arial", Font.PLAIN, 15);

        String string = "Time (hours)";

        FontMetrics metrics = g.getFontMetrics(font);
        int width = metrics.stringWidth(string);
        int height = metrics.getHeight();

        gg.setFont(font);

        gg.drawString(string, (getWidth() - width) / 2, 11);
    }

}
