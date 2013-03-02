/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Ibutton.DeviceHandler.Hygrochron;
import Ibutton.DeviceHandler.Thermochron;
import com.dalsemi.onewire.OneWireException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class Mission extends javax.swing.JFrame {
    private Hygrochron hygrochron;
    private Thermochron thermochron;
    private Boolean THERMOCHRON = false;
    private Boolean HYGROCHRON = false;

    /**
     * Creates new form newMission
     */
    public Mission(Hygrochron hygrochron) throws OneWireException{
        HYGROCHRON = true;
        this.hygrochron = hygrochron;
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newMissionSampleRate.setText(Integer.toString(hygrochron.getMissionSampleRate()));
        newMissionTempAlarmHigh.setText(Double.toString(hygrochron.getMissionAlarmTempHigh()));
        newMissionTempAlarmLow.setText(Double.toString(hygrochron.getMissionAlarmTempLow()));
        newMissionHumidityAlarmHigh.setText(Double.toString(hygrochron.getMissionAlarmHumHigh()));
        newMissionHumidityAlarmLow.setText(Double.toString(hygrochron.getMissionAlarmHumLow()));
    }
    public Mission(Thermochron thermochron) throws OneWireException{
        THERMOCHRON = true;
        this.thermochron = thermochron;
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newMissionSampleRate.setText(Integer.toString(thermochron.getMissionSampleRate()));
        newMissionTempAlarmHigh.setText(Double.toString(thermochron.getMissionAlarmHigh()));
        newMissionTempAlarmLow.setText(Double.toString(thermochron.getMissionAlarmLow()));
        humidAlarmHigh.setVisible(false);
        humidAlarmLow.setVisible(false);
        newMissionHumidityAlarmLow.setVisible(false);
        newMissionHumidityAlarmHigh.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newMissionPanel = new javax.swing.JPanel();
        sampleRateLabelNewMission = new javax.swing.JLabel();
        delayStartLabelNewMission = new javax.swing.JLabel();
        rolloverLabelNewMission = new javax.swing.JLabel();
        tempAlarmHighLabelNewMission = new javax.swing.JLabel();
        tempAlarmLowLabelNewMission = new javax.swing.JLabel();
        sutaLabel = new javax.swing.JLabel();
        newMissionLabel = new javax.swing.JLabel();
        newMissionSampleRate = new javax.swing.JTextField();
        newMissionDelayStart = new javax.swing.JTextField();
        newMissionTempAlarmHigh = new javax.swing.JTextField();
        newMissionTempAlarmLow = new javax.swing.JTextField();
        rolloverCheck = new javax.swing.JCheckBox();
        sutaCheck = new javax.swing.JCheckBox();
        startNewMissionButton = new javax.swing.JButton();
        humidAlarmHigh = new javax.swing.JLabel();
        humidAlarmLow = new javax.swing.JLabel();
        newMissionHumidityAlarmHigh = new javax.swing.JTextField();
        newMissionHumidityAlarmLow = new javax.swing.JTextField();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Mission");

        sampleRateLabelNewMission.setText("Sample rate (in seconds):");

        delayStartLabelNewMission.setText("Delay start (in minutes):");

        rolloverLabelNewMission.setText("Rollover: ");

        tempAlarmHighLabelNewMission.setText("Temperature Alarm High:");

        tempAlarmLowLabelNewMission.setText("Temperature Alarm Low:");

        sutaLabel.setText("Start Upon Temperature Alarm:");

        newMissionLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        newMissionLabel.setText("Create New Mission");

        newMissionDelayStart.setText("0");

        rolloverCheck.setText("Yes");

        sutaCheck.setText("Yes");

        startNewMissionButton.setText("Start New Mission");
        startNewMissionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startNewMissionButtonActionPerformed(evt);
            }
        });

        humidAlarmHigh.setText("Humidity Alarm High:");

        humidAlarmLow.setText("Humidity Alarm Low:");

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newMissionPanelLayout = new javax.swing.GroupLayout(newMissionPanel);
        newMissionPanel.setLayout(newMissionPanelLayout);
        newMissionPanelLayout.setHorizontalGroup(
            newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newMissionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newMissionPanelLayout.createSequentialGroup()
                        .addComponent(humidAlarmHigh)
                        .addGap(62, 62, 62)
                        .addComponent(newMissionHumidityAlarmHigh, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                    .addGroup(newMissionPanelLayout.createSequentialGroup()
                        .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(newMissionPanelLayout.createSequentialGroup()
                                    .addComponent(humidAlarmLow)
                                    .addGap(64, 64, 64)
                                    .addComponent(newMissionHumidityAlarmLow))
                                .addGroup(newMissionPanelLayout.createSequentialGroup()
                                    .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sutaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(newMissionLabel)
                                        .addComponent(tempAlarmLowLabelNewMission, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tempAlarmHighLabelNewMission, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rolloverLabelNewMission, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(delayStartLabelNewMission, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sampleRateLabelNewMission, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rolloverCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sutaCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(newMissionTempAlarmLow)
                                        .addComponent(newMissionTempAlarmHigh)
                                        .addComponent(newMissionDelayStart)
                                        .addComponent(newMissionSampleRate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(newMissionPanelLayout.createSequentialGroup()
                                .addComponent(startNewMissionButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(closeButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        newMissionPanelLayout.setVerticalGroup(
            newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newMissionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newMissionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sampleRateLabelNewMission)
                    .addComponent(newMissionSampleRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delayStartLabelNewMission)
                    .addComponent(newMissionDelayStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rolloverLabelNewMission)
                    .addComponent(rolloverCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempAlarmHighLabelNewMission)
                    .addComponent(newMissionTempAlarmHigh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempAlarmLowLabelNewMission)
                    .addComponent(newMissionTempAlarmLow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sutaLabel)
                    .addComponent(sutaCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(humidAlarmHigh)
                    .addComponent(newMissionHumidityAlarmHigh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(humidAlarmLow)
                    .addComponent(newMissionHumidityAlarmLow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newMissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startNewMissionButton)
                    .addComponent(closeButton))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(newMissionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(newMissionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * When create new button is pressed
     * the new mission is set in the ibutton
     */
    private void startNewMissionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startNewMissionButtonActionPerformed
        // TODO add your handling code here:
        if(THERMOCHRON)
                try {
                thermochron.stopMission();
                thermochron.setRollover(rolloverCheck.isEnabled() );
                thermochron.setTemperatureAlarmHigh(Double.parseDouble( newMissionTempAlarmHigh.getText()) );
                thermochron.setTemperatureAlarmLow(Double.parseDouble( newMissionTempAlarmLow.getText()) );
                thermochron.startMission(
                        Integer.parseInt( newMissionSampleRate.getText()),
                        Integer.parseInt( newMissionDelayStart.getText())
                        );
                
                System.out.println(thermochron.getMissionInformation());
            } catch (OneWireException ex) {
          
            }finally
                {
                    Box.Message("Started a new mission!");
                    this.dispose();
                }
            else
                try {
                hygrochron.stopMission();
                hygrochron.setRollover(rolloverCheck.isEnabled());
                hygrochron.setHumidityAlarmHigh(Double.parseDouble(newMissionHumidityAlarmHigh.getText()));
                hygrochron.setHumidityAlarmLow(Double.parseDouble( newMissionHumidityAlarmLow.getText()));
                hygrochron.setTemperatureAlarmHigh(Double.parseDouble( newMissionTempAlarmHigh.getText()));
                hygrochron.setTemperatureAlarmLow(Double.parseDouble( newMissionTempAlarmLow.getText()));
                hygrochron.startMission(
                        Integer.parseInt(newMissionSampleRate.getText()),
                        Integer.parseInt(newMissionDelayStart.getText())
                        );
                System.out.println(hygrochron.getMissionInformation());
            } catch (OneWireException ex) {
           
            }finally
                {
                    Box.Message("Started a new mission!");
                    this.dispose();
                }
    }//GEN-LAST:event_startNewMissionButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel delayStartLabelNewMission;
    private javax.swing.JLabel humidAlarmHigh;
    private javax.swing.JLabel humidAlarmLow;
    private javax.swing.JTextField newMissionDelayStart;
    private javax.swing.JTextField newMissionHumidityAlarmHigh;
    private javax.swing.JTextField newMissionHumidityAlarmLow;
    private javax.swing.JLabel newMissionLabel;
    private javax.swing.JPanel newMissionPanel;
    private javax.swing.JTextField newMissionSampleRate;
    private javax.swing.JTextField newMissionTempAlarmHigh;
    private javax.swing.JTextField newMissionTempAlarmLow;
    private javax.swing.JCheckBox rolloverCheck;
    private javax.swing.JLabel rolloverLabelNewMission;
    private javax.swing.JLabel sampleRateLabelNewMission;
    private javax.swing.JButton startNewMissionButton;
    private javax.swing.JCheckBox sutaCheck;
    private javax.swing.JLabel sutaLabel;
    private javax.swing.JLabel tempAlarmHighLabelNewMission;
    private javax.swing.JLabel tempAlarmLowLabelNewMission;
    // End of variables declaration//GEN-END:variables
}
