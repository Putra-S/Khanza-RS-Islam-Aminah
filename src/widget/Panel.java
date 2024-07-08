/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package widget;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

/**
 *
 * @author dosen
 */
public class Panel extends JPanel {

    private static final long serialVersionUID = -1;
    private BufferedImage gradientImage;
    private final Color black = new Color(30,30,0);
    private final Color warna = new Color(80,80,0);

    /**
     *
     */
    public Panel() {
        super();
        //this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(50,60,70)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            setUpGradient();
            g2.drawImage(gradientImage, 0, 0, getWidth(), getHeight(), null);

            int width = getWidth();
            int height = getHeight() * 5 / 100;

            Color light = new Color(1F, 1F, 1F, 0.5F);
            Color dark = new Color(1F, 1F, 1F, 0.0F);

            GradientPaint paint = new GradientPaint(0, 0, light, 0, height, dark);
            GeneralPath path = new GeneralPath();
            path.moveTo(0, 0);
            path.lineTo(0, height);
            path.curveTo(0, height, width / 2, height / 2, width, height);
            path.lineTo(width, 0);
            path.closePath();

            g2.setPaint(paint);
            g2.fill(path);

            paint = new GradientPaint(0, getHeight(), light, 0, getHeight() - height, dark);
            path = new GeneralPath();
            path.moveTo(0, getHeight());
            path.lineTo(0, getHeight() - height);
            path.curveTo(0, getHeight() - height, width / 2, getHeight() - height / 2, width, getHeight() - height);
            path.lineTo(width, getHeight());
            path.closePath();

            g2.setPaint(paint);
            g2.fill(path);
            g2.dispose();
        }
    }

    private void setUpGradient() {
        gradientImage = new BufferedImage(1, getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) gradientImage.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint paint = new GradientPaint(0, 0, black, 0, getHeight(), warna);

        g2.setPaint(paint);
        g2.fillRect(0, 0, 1, getHeight());
        g2.dispose();
    }

}
