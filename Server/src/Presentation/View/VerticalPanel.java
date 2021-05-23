package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * The type Vertical panel.
 */
public class VerticalPanel extends JPanel {

    /**
     * Instantiates a new Vertical panel.
     */
    public VerticalPanel() {
        setPreferredSize(new Dimension(25, 0));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = new Font("Arial", Font.PLAIN, 15);

        String string = "Number of Matches";

        FontMetrics metrics = g.getFontMetrics(font);
        int width = metrics.stringWidth(string);
        int height = metrics.getHeight();

        gg.setFont(font);

        drawRotate(gg, getWidth(), (getHeight() + width) / 2, 270, string);
    }

    /**
     * Draw rotate.
     *
     * @param gg    the gg
     * @param x     the x
     * @param y     the y
     * @param angle the angle
     * @param text  the text
     */
    public void drawRotate(Graphics2D gg, double x, double y, int angle, String text) {
        gg.translate((float) x, (float) y);
        gg.rotate(Math.toRadians(angle));
        gg.drawString(text, 0, 0);
        gg.rotate(-Math.toRadians(angle));
        gg.translate(-(float) x, -(float) y);
    }

}
