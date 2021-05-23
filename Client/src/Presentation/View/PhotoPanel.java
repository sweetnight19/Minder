package Presentation.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The type Photo panel.
 */
public class PhotoPanel extends JPanel {
    private BufferedImage photo;

    /**
     * Instantiates a new Photo panel.
     *
     * @param image the image
     */
    public PhotoPanel(BufferedImage image) {
        try {
            if (image == null) {
                photo = ImageIO.read(new File("Client/Media/avatar.png"));
            } else {
                photo = image;
            }
        } catch (IOException e) {
            System.out.println("Error al carregar la foto de l'usuari");
        }
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Fins a on es pintara la foto
        int x = 0;
        int y = 0;

        int w = this.getWidth();
        int h = this.getHeight();
        if (photo.getHeight() < photo.getWidth()) {
            // mes ample -> vols fer el resize de altura
            h = this.getWidth() * photo.getHeight() / photo.getWidth();
            y = (this.getHeight() - h) / 2;
        } else {
            // mes alta -> vols fer el resize de amplada
            w = this.getHeight() * photo.getWidth() / photo.getHeight();
            x = (this.getWidth() - w) / 2;
        }
        g.drawImage(photo, x, y, w, h, this);
    }
}


