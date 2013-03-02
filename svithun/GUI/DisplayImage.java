/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.*;
/**
 *
 * @author Baardsen
 */
public class DisplayImage extends JFrame {

    class PopUpDemo extends JPopupMenu {
        JMenuItem anItem, anItem2;
        public PopUpDemo(){
            anItem = new JMenuItem("Get data");
            anItem2 = new JMenuItem("Get image");
            add(anItem);
            add(anItem2);

        }
    }

    class PopClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e){
            if (e.isPopupTrigger())
                doPop(e);
        }

        @Override
        public void mouseReleased(MouseEvent e){
            if (e.isPopupTrigger())
                doPop(e);
        }

        private void doPop(MouseEvent e){
            PopUpDemo menu = new PopUpDemo();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private String path; 
    private URL url; 
    private Image img;

    /**
     * Taking imagepath as parameter and displaying the image in a jframe
     * @param path - Imagepath
     */
    /**
     *
     * @param path
     */
    public DisplayImage(String path){
        this.path = path;
        url = this.getClass().getResource(path);
        img = this.getToolkit().createImage(path);

        setSize(800,640);
        setVisible(true);
        this.addMouseListener(new PopClickListener());
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(img, 5, 20, this);
    }

}