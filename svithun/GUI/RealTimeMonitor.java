/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RealTimeMonitor.java
 *
 * Created on 08.mai.2012, 19:26:10
 */

package GUI;

import Ibutton.DeviceHandler.Hygrochron;
import Ibutton.DeviceHandler.Thermochron;
import com.dalsemi.onewire.OneWireException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Baardsen
 */
public class RealTimeMonitor extends javax.swing.JFrame {
    
    private String graphPath = "";
    private RenderGraph r_graph;
    private Hygrochron hygrochron;
    private Thermochron thermochron;
    private boolean HYGROCHRON = false;

   

    /**
     * Thread for rendering the graph in realtime
     */
    private class RenderGraph extends Thread{

        public void run(){
        
            while(!Thread.currentThread().isInterrupted()){
                if(HYGROCHRON){
                    try {
                    hygrochron.loadMission();
                    hygrochron.generateGraph();
                    drawImage();
                    Thread.sleep(hygrochron.getMissionSampleRate()*1000);
                    } catch (Exception ex) {
                        return;
                    }
                }else{
                    try {
                    thermochron.loadMission();
                    thermochron.generateGraph();
                    drawImage();
                    Thread.sleep(thermochron.getMissionSampleRate()*1000);
                    } catch (Exception ex) {
                        return;
                    }
               }
            }
        }
        /**
         * Drawing the graph picture
         * @throws IOException
         */
         private void drawImage() throws IOException{
           BufferedImage image = ImageIO.read(new File(graphPath));
           setSize(image.getWidth()+5, image.getHeight()+40);
           graph_image.setIcon(new ImageIcon(image));
        }
    }
    /**
     * Constructor, setting up the correct devices and starting a new realtime rendering thread for displaying the graph in realtime.
     * @param owc
     * @param graphPath
     * @throws OneWireException
     */
    public RealTimeMonitor(Object owc,String graphPath, String type) throws OneWireException{
        initComponents();
        setupDevice(owc);
        setVisible(true);
        setTitle("Realtime " + type);
        this.graphPath = graphPath;
        //render realtime
        r_graph = new RenderGraph();
        r_graph.start();

        addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
             if(r_graph.isAlive()){
                 r_graph.interrupt();
                 r_graph = null;
             }
             setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             }
          }
      );
        
    }

    /**
     * Setting up the device. Either Hygrochron or Thermochron
     * @param owc
     * OneWireContainer
     * @throws OneWireException
     */
    private void setupDevice(Object owc) throws OneWireException{
        try{
            hygrochron = (Hygrochron) owc;
            HYGROCHRON = true;
        }catch(Exception e){
            thermochron = (Thermochron) owc;

        }
    }
 

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graph_image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        graph_image.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(graph_image)
                .addContainerGap(957, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(graph_image)
                .addContainerGap(562, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel graph_image;
    // End of variables declaration//GEN-END:variables

}
