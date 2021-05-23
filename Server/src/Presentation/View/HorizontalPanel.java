package Presentation;

import javax.swing.*;
import java.awt.*;

/**
 * The type Horizontal panel.
 */
public class HorizontalPanel extends JPanel {
    private String string;

    /**
     * Instantiates a new Horizontal panel.
     */
    public HorizontalPanel(int type) {
        switch (type){
            case 0:
                string = "Time (hours)";
                break;
            case 1:
                string = "Time (Week Days)";
                break;
            case 2:
                string = "Time (Month Days)";
                break;
        }
        setPreferredSize(new Dimension(0, 25));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = new Font("Arial", Font.PLAIN, 15);

        FontMetrics metrics = g.getFontMetrics(font);
        int width = metrics.stringWidth(string);
        int height = metrics.getHeight();

        gg.setFont(font);

        gg.drawString(string, (getWidth() - width) / 2, 11);
    }
}
