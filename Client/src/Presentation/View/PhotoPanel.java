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
/*
    public PhotoPanel(String path) {
        try {
            photo = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error al carregar la foto de l'usuari");
        }
    }
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

//    @Override
//    public Dimension getPreferredSize() {
//        // Mida actual imatge
//        Dimension preferredSize = super.getPreferredSize();
//
//        // Ratio
//        //preferredSize.height = this.getWidth() * photo.getHeight() / photo.getWidth();
//        if (photo.getHeight() < photo.getWidth()) {
//            // mes ample -> vols fer el resize de altura
//            preferredSize.height = this.getWidth() * photo.getHeight() / photo.getWidth();
//        } else {
//            // mes alta -> vols fer el resize de amplada
//            preferredSize.width = this.getHeight() * photo.getWidth() / photo.getHeight();
//        }
//        return preferredSize;
//    }
}


