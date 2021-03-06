/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 *
 * @author Baardsen
 */
public class ImageScaler {
    
    public ImageIcon getImage(int w, int h, BufferedImage image) {
        BufferedImage scaled = scale(image, w, h);
        return new ImageIcon(scaled);
    }

    private BufferedImage scale(BufferedImage src, int w, int h) {
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        // Fill background for scale to fit.
        g2.setBackground(UIManager.getColor("Panel.background"));
        g2.clearRect(0,0,w,h);
        double xScale = (double)w/src.getWidth();
        double yScale = (double)h/src.getHeight();
        // Scaling options:
        // Scale to fit - image just fits in label.
        double scale = Math.min(xScale, yScale);
        // Scale to fill - image just fills label.
        //double scale = Math.max(xScale, yScale);
        int width  = (int)(scale*src.getWidth());
        int height = (int)(scale*src.getHeight());
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(src, x, y, width, height, null);
        g2.dispose();
        return dst;
    }
}
