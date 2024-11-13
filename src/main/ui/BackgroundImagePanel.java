package ui;

import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;

// Referenced https://stackoverflow.com/questions/36445244/how-to-make-jbuttons-visible-on-a-jpanel-with-an-image-as-background
// Referenced https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background
// Referenced https://stackoverflow.com/questions/65533422/fit-image-into-jpanel

// Represents a JPanel with a background image
public class BackgroundImagePanel extends JPanel {

    private Image image;

    // REQUIRES: an image
    // EFFECTS: creates a background image panel with the given image
    public BackgroundImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
    }
}
