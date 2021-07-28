package com.vizor.test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewPicture extends JPanel {

    private BufferedImage image;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    public ViewPicture(String s) {
        try {
            JFrame frame = new JFrame();
            image = ImageIO.read(new File(
                    "assets/" + s));

            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
            frame.getContentPane().add(this);
            this.setBackground(Color.BLACK);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}