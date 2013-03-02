/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 *
 * @author Baardsen
 */
public class Test extends JFrame{
    String path;
    public Test(String path) throws IOException{
        setVisible(true);
        this.path = path;
        File file = new File(path);
        BufferedImage image = javax.imageio.ImageIO.read(file);
        add(getLabel(100, 100, image));
    }

    


    public static void main(String [] args) throws IOException{
        Test t = new Test("K:\\Users\\Baardsen\\Documents\\NetBeansProjects\\Ibutton_\\Mission\\Hygrochron_1200000021002841\\HUM_TEMP_09.05.2012_142201.png");

    }
}
