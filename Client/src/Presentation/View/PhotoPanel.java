package Presentation.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PhotoPanel extends JPanel {
    private BufferedImage photo;

    public PhotoPanel(String path) {
        try {
            photo = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error al carregar la foto de l'usuari");
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        // Ratio
        preferredSize.height = this.getWidth() * photo.getHeight() / photo.getWidth();

        return preferredSize;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(photo, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}


