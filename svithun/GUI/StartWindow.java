/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Thomas
 */
import Ibutton.DeviceHandler.DeviceReader;
import Ibutton.DeviceHandler.Hygrochron;
import Ibutton.DeviceHandler.Thermochron;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StartWindow extends javax.swing.JFrame{
    private DeviceReader device;
    private boolean HYGROCHRON = false;
    private Hygrochron hygrochron;
    private Thermochron thermochron;
    private RealTime realtime;

    private String historyPath = System.getProperty("user.dir")+"\\Mission\\";
    private DefaultListModel listModel;
    private File directory;
    private File files;
    private BufferedImage image;
    private Graphics drawImage;

    private class RealTime implements MouseListener{
        private String path;
        private String title;

        public RealTime(String path, String title){
            this.path = path;
            this.title = title;
        }
        public void mouseClicked(MouseEvent e) {
            if(HYGROCHRON){
                try {

                    new RealTimeMonitor(hygrochron, path, title);
                } catch (Exception ex) {
                    Box.Message("There seems to be a problem with rendering of realtime, press refresh and try again.");
                }
            }else{
                try {
                    new RealTimeMonitor(thermochron, path, title);
                } catch (Exception ex) {
                    Box.Message("There seems to be a problem with rendering of realtime, press refresh and try again.");
                }
            }
        }

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {   }

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {

        }

    }


    /**
     * Creates new form StartWindow
     */
    public StartWindow(){
        initComponents();
        try{
            readDevices();
            customGraph_btn.setVisible(false);
            makeList();
            showFolders();
            fileText.setVisible(false);
            fileTextPanel.setVisible(false);
        }catch(OneWireException e){
            Box.Message("It seems like you haven't connected an ibutton to your computer, please connect it and try again!");
            System.exit(0);
        }
        
    }
    
    /*
     * reads devices connected to the computer
     */
    private void readDevices() throws OneWireIOException, OneWireException{
        device = new DeviceReader();
    }
    
    /*
     * Populates the list and shows connected devices
     */
    private void makeList(){
        DefaultListModel listModel = new DefaultListModel();
        //listModel.addElement(device);

        for(OneWireContainer owc : device.getDevices()){
               listModel.addElement(owc);
        }
        buttonList.setModel(listModel);
    }
    
  /*
   * Displays information about selected device
   */
   private void displayDeviceInformation(OneWireContainer owc) throws OneWireException, IOException{
        if(owc.getAlternateNames().equals("Hygrochron")){
            dataLogging.setVisible(true);
            dataLoggingText.setVisible(true);
            dataHighAlarm.setVisible(true);
            dataHighAlarmText.setVisible(true);
            dataLowAlarm.setVisible(true);
            dataLowAlarmText.setVisible(true);
            hygrochron = new Hygrochron(owc);
            HYGROCHRON = true;
            adressText.setText(hygrochron.getAdress());
            alternativeNameText.setText(hygrochron.getAlternativeName());
            nameText.setText(hygrochron.getName());
            descriptionText.setText(hygrochron.getDescription());
            String[] info  = hygrochron.getMissionInformationArray();
            missionRunningText.setText(info[0]);
            sutaEnabledText.setText(info[1]);
            sampleRateText.setText(info[2]);
            if(info[3].equals("30.11.1999_00:00:00"))
            {
                missionStartText.setText("No data recorded yet.");
            }
            else{
                missionStartText.setText(info[3]);
            }
            sampleCountText.setText(info[4]);
            rolloverEnabledText.setText(info[5]);
            if(info[6].equals("30.11.1999_00:00:00"))
            {
                firstSampleText.setText("No data recorded yet.");
            }
            else{
                firstSampleText.setText(info[6]);
            }
            totalDeviceSamplesText.setText(info[7]);
            tempHighAlarmText.setText(info[9]);
            tempLowAlarmText.setText(info[10]);
            dataLoggingText.setText(info[11]);
            dataHighAlarmText.setText(info[12]);
            dataLowAlarmText.setText(info[13]);
        }    
        else{
            dataLogging.setVisible(false);
            dataLoggingText.setVisible(false);
            dataHighAlarm.setVisible(false);
            dataHighAlarmText.setVisible(false);
            dataLowAlarm.setVisible(false);
            dataLowAlarmText.setVisible(false);
            thermochron = new Thermochron(owc);
            HYGROCHRON = false;
            adressText.setText(thermochron.getAdress());
            alternativeNameText.setText(thermochron.getAlternativeName());
            nameText.setText(thermochron.getName());
            descriptionText.setText(thermochron.getDescription());
            String[] info  = thermochron.getMissionInformationArray();
            missionRunningText.setText(info[0]);
            sutaEnabledText.setText(info[1]);
            sampleRateText.setText(info[2]);
            if(info[3].equals("30.11.1999_00:00:00"))
            {
                missionStartText.setText("No data recorded yet.");
            }
            else{
                missionStartText.setText(info[3]);
            }
            sampleCountText.setText(info[4]);
            rolloverEnabledText.setText(info[5]);
            if(info[6].equals("30.11.1999_00:00:00"))
            {
                firstSampleText.setText("No data recorded yet.");
            }
            else{
                firstSampleText.setText(info[6]);
            }
            totalDeviceSamplesText.setText(info[7]);
            tempHighAlarmText.setText(info[9]);
            tempLowAlarmText.setText(info[10]);
            dataLoggingText.setText(info[11]);
            dataHighAlarmText.setText(info[12]);
            dataLowAlarmText.setText(info[13]);
        }    
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        mainPanel = new javax.swing.JPanel();
        missionStatusLabel = new javax.swing.JLabel();
        missionRunningLabel = new javax.swing.JLabel();
        sutaEnabled = new javax.swing.JLabel();
        sampleRate = new javax.swing.JLabel();
        rolloverEnabled = new javax.swing.JLabel();
        firstSample = new javax.swing.JLabel();
        totalDeviceSamples = new javax.swing.JLabel();
        tempHighAlarm = new javax.swing.JLabel();
        tempLowAlarm = new javax.swing.JLabel();
        dataLogging = new javax.swing.JLabel();
        dataHighAlarm = new javax.swing.JLabel();
        dataLowAlarm = new javax.swing.JLabel();
        missionRunningText = new javax.swing.JTextField();
        sutaEnabledText = new javax.swing.JTextField();
        sampleRateText = new javax.swing.JTextField();
        rolloverEnabledText = new javax.swing.JTextField();
        firstSampleText = new javax.swing.JTextField();
        totalDeviceSamplesText = new javax.swing.JTextField();
        tempHighAlarmText = new javax.swing.JTextField();
        tempLowAlarmText = new javax.swing.JTextField();
        dataLoggingText = new javax.swing.JTextField();
        dataHighAlarmText = new javax.swing.JTextField();
        dataLowAlarmText = new javax.swing.JTextField();
        newMissionButton = new javax.swing.JButton();
        newMissionLabel = new javax.swing.JLabel();
        sampleCountLabel = new javax.swing.JLabel();
        sampleCountText = new javax.swing.JTextField();
        missionStartLabel = new javax.swing.JLabel();
        missionStartText = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        deleteMissionButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        buttonList = new javax.swing.JList();
        tabPanel = new javax.swing.JTabbedPane();
        tabPanel1 = new javax.swing.JPanel();
        customGraph_btn = new javax.swing.JButton();
        thumb1 = new javax.swing.JLabel();
        thumb2 = new javax.swing.JLabel();
        thumb3 = new javax.swing.JLabel();
        tabPanel2 = new javax.swing.JPanel();
        foldersTextLabel = new javax.swing.JLabel();
        browseText = new javax.swing.JLabel();
        filesTextLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        folderList = new javax.swing.JList();
        showFileButton = new javax.swing.JButton();
        deleteFileButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JList();
        infoPanel = new javax.swing.JPanel();
        fileTextPanel = new javax.swing.JScrollPane();
        fileText = new javax.swing.JTextArea();
        graphPreview = new javax.swing.JLabel();
        defaultPathButton = new javax.swing.JButton();
        changeFolderButton = new javax.swing.JButton();
        autoDeleteCheckBox = new javax.swing.JCheckBox();
        helpButton = new javax.swing.JButton();
        backupButton = new javax.swing.JButton();
        selectedDeviceLabel = new javax.swing.JLabel();
        alternativeNameText = new javax.swing.JTextField();
        nameText = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextArea();
        adressText = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        refreshButtonListButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hygrochron/Termochron Reader");

        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        missionStatusLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        missionStatusLabel.setText("Current Mission Status:");

        missionRunningLabel.setText("Is mission running:");
        missionRunningLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        sutaEnabled.setText("Is SUTA enabled:");
        sutaEnabled.setToolTipText("Start Upon Temperature Alarm");
        sutaEnabled.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        sampleRate.setText("Sample rate:");
        sampleRate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        rolloverEnabled.setText("Is Rollover enabled:");
        rolloverEnabled.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        firstSample.setText("First sample:");
        firstSample.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        totalDeviceSamples.setText("Total device samples:");
        totalDeviceSamples.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tempHighAlarm.setText("Temperature high alarm:");
        tempHighAlarm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        tempLowAlarm.setText("Temperature low alarm:");
        tempLowAlarm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        dataLogging.setText("Data logging:");
        dataLogging.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        dataHighAlarm.setText("Data high alarm:");
        dataHighAlarm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        dataLowAlarm.setText("Data low alarm:");
        dataLowAlarm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        missionRunningText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        missionRunningText.setEnabled(false);

        sutaEnabledText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sutaEnabledText.setEnabled(false);

        sampleRateText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sampleRateText.setEnabled(false);

        rolloverEnabledText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rolloverEnabledText.setEnabled(false);

        firstSampleText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        firstSampleText.setEnabled(false);

        totalDeviceSamplesText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totalDeviceSamplesText.setEnabled(false);

        tempHighAlarmText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tempHighAlarmText.setEnabled(false);

        tempLowAlarmText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tempLowAlarmText.setEnabled(false);

        dataLoggingText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        dataLoggingText.setEnabled(false);

        dataHighAlarmText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        dataHighAlarmText.setEnabled(false);

        dataLowAlarmText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        dataLowAlarmText.setEnabled(false);

        newMissionButton.setText("Create New Mission");
        newMissionButton.setToolTipText("Creating a new mission for the device.");
        newMissionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMissionButtonActionPerformed(evt);
            }
        });

        newMissionLabel.setText("New mission:");
        newMissionLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        sampleCountLabel.setText("Sample count:");
        sampleCountLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        sampleCountText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sampleCountText.setEnabled(false);

        missionStartLabel.setText("Mission start time:");
        missionStartLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        missionStartText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        missionStartText.setEnabled(false);

        refreshButton.setText("Refresh");
        refreshButton.setToolTipText("Refreshing the device and graphs.");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Delete current mission:");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        deleteMissionButton.setText("Delete current mission");
        deleteMissionButton.setToolTipText("Delete the current mission from the device.");
        deleteMissionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMissionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(missionStatusLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(refreshButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(sutaEnabled, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(sampleRate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(missionRunningLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(sampleCountLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(missionStartLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(newMissionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rolloverEnabled, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(firstSample, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalDeviceSamples, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dataLowAlarm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dataHighAlarm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dataLogging, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tempHighAlarm, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(tempLowAlarm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(missionRunningText)
                                .addComponent(sampleRateText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sampleCountText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(missionStartText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(firstSampleText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(totalDeviceSamplesText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tempHighAlarmText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tempLowAlarmText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dataLoggingText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dataHighAlarmText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dataLowAlarmText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rolloverEnabledText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sutaEnabledText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(deleteMissionButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newMissionButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(missionStatusLabel)
                    .addComponent(refreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(missionRunningLabel)
                    .addComponent(missionRunningText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sutaEnabled)
                    .addComponent(sutaEnabledText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sampleRate)
                    .addComponent(sampleRateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sampleCountLabel)
                    .addComponent(sampleCountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(missionStartLabel)
                    .addComponent(missionStartText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rolloverEnabled)
                    .addComponent(rolloverEnabledText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstSample)
                    .addComponent(firstSampleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalDeviceSamples)
                    .addComponent(totalDeviceSamplesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempHighAlarm)
                    .addComponent(tempHighAlarmText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempLowAlarm)
                    .addComponent(tempLowAlarmText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataLogging)
                    .addComponent(dataLoggingText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataHighAlarm)
                    .addComponent(dataHighAlarmText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataLowAlarm)
                    .addComponent(dataLowAlarmText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newMissionLabel)
                    .addComponent(newMissionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(deleteMissionButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Select a button from the list below");

        buttonList.setToolTipText("Here you will find the devices connected to the computer.");
        buttonList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonListMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(buttonList);

        tabPanel.setToolTipText("Here you will find file history of all your files.");
        tabPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPanelStateChanged(evt);
            }
        });

        tabPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPanel1MouseClicked(evt);
            }
        });

        customGraph_btn.setText("Create Custom Graph");
        customGraph_btn.setToolTipText("Starting the view graph and option to generate a custom one.");
        customGraph_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customGraph_btnActionPerformed(evt);
            }
        });

        thumb1.setText("jLabel4");
        thumb1.setToolTipText("Click to view realtime temperature graph.");

        thumb2.setText("jLabel4");
        thumb2.setToolTipText("Click to view realtime humidity graph.");

        thumb3.setText("jLabel4");
        thumb3.setToolTipText("Click to view realtime humidity and temperature graph.");

        javax.swing.GroupLayout tabPanel1Layout = new javax.swing.GroupLayout(tabPanel1);
        tabPanel1.setLayout(tabPanel1Layout);
        tabPanel1Layout.setHorizontalGroup(
            tabPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customGraph_btn)
                    .addGroup(tabPanel1Layout.createSequentialGroup()
                        .addComponent(thumb1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thumb3))
                    .addComponent(thumb2))
                .addContainerGap(492, Short.MAX_VALUE))
        );
        tabPanel1Layout.setVerticalGroup(
            tabPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thumb1)
                    .addComponent(thumb3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thumb2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
                .addComponent(customGraph_btn)
                .addContainerGap())
        );

        tabPanel.addTab("Graphs", tabPanel1);

        tabPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        foldersTextLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        foldersTextLabel.setText("Folders");

        browseText.setFont(new java.awt.Font("Tahoma", 1, 14));
        browseText.setText("Browse previously generated graphs");

        filesTextLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        filesTextLabel.setText("Files");

        folderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folderListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(folderList);

        showFileButton.setText("Show file");
        showFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showFileButtonActionPerformed(evt);
            }
        });

        deleteFileButton.setText("Delete file");
        deleteFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFileButtonActionPerformed(evt);
            }
        });

        fileList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(fileList);

        infoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoPanelMouseClicked(evt);
            }
        });

        fileText.setColumns(20);
        fileText.setLineWrap(true);
        fileText.setRows(5);
        fileText.setEnabled(false);
        fileText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoPanelMouseClicked(evt);
            }
        });
        fileTextPanel.setViewportView(fileText);

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(infoPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(fileTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(infoPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(fileTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        defaultPathButton.setText("Reset");
        defaultPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultPathButtonActionPerformed(evt);
            }
        });

        changeFolderButton.setText("Change Path");
        changeFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFolderButtonActionPerformed(evt);
            }
        });

        autoDeleteCheckBox.setText("Auto-delete.");

        helpButton.setText("HELP");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        backupButton.setText("Backup");
        backupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabPanel2Layout = new javax.swing.GroupLayout(tabPanel2);
        tabPanel2.setLayout(tabPanel2Layout);
        tabPanel2Layout.setHorizontalGroup(
            tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabPanel2Layout.createSequentialGroup()
                        .addComponent(browseText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                        .addComponent(helpButton))
                    .addGroup(tabPanel2Layout.createSequentialGroup()
                        .addGroup(tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tabPanel2Layout.createSequentialGroup()
                                .addComponent(foldersTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(changeFolderButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(defaultPathButton))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabPanel2Layout.createSequentialGroup()
                                .addComponent(filesTextLabel)
                                .addGap(0, 244, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabPanel2Layout.createSequentialGroup()
                                .addComponent(showFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(autoDeleteCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(backupButton)))
                        .addContainerGap())))
        );
        tabPanel2Layout.setVerticalGroup(
            tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPanel2Layout.createSequentialGroup()
                .addGroup(tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(browseText))
                    .addComponent(helpButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defaultPathButton)
                    .addComponent(foldersTextLabel)
                    .addComponent(changeFolderButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabPanel2Layout.createSequentialGroup()
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(showFileButton)
                            .addComponent(deleteFileButton)
                            .addComponent(autoDeleteCheckBox)
                            .addComponent(backupButton)))
                    .addGroup(tabPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filesTextLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPanel.addTab("History", tabPanel2);

        selectedDeviceLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        selectedDeviceLabel.setText("Information on selected device");

        alternativeNameText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        alternativeNameText.setEnabled(false);

        nameText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nameText.setEnabled(false);

        descriptionText.setColumns(5);
        descriptionText.setLineWrap(true);
        descriptionText.setRows(5);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        descriptionText.setEnabled(false);
        jScrollPane2.setViewportView(descriptionText);

        adressText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        adressText.setEnabled(false);

        nameLabel.setText("Name:");
        nameLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        addressLabel.setText("Address: ");
        addressLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setText("Alternative name:");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        refreshButtonListButton.setText("Refresh Button List");
        refreshButtonListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonListButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("About");

        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setText("Help");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(refreshButtonListButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectedDeviceLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameText)
                            .addComponent(alternativeNameText)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(adressText, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabPanel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(selectedDeviceLabel)
                    .addComponent(refreshButtonListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(alternativeNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addressLabel)
                            .addComponent(adressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newMissionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMissionButtonActionPerformed

        try
        {
            if(HYGROCHRON)
            {
                System.out.println(hygrochron.getAlternativeName());
                new Mission(hygrochron);
            }
            else
            {
                System.out.println(thermochron.getAlternativeName());
                new Mission(thermochron);
            }          
        }
        catch(Exception ex)
        {
            Box.Message("There seems to be a problem with setting up a new mission, try to reconnect the device to the USB.");
        }
    }//GEN-LAST:event_newMissionButtonActionPerformed

    /*
     * Listener for when user selects from buttonList
     */
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:

        try {
            displayDeviceInformation((OneWireContainer)buttonList.getSelectedValue());
            generateGraphThumbnail();
        } catch (OneWireException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteMissionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMissionButtonActionPerformed

        if(Box.Confirm("Are you sure you want to delete the current mission?", "Delete Mission?")==Box.YES){
            if(HYGROCHRON)
            {
                try {
                    hygrochron.deleteMemory();
                    displayDeviceInformation((OneWireContainer)buttonList.getSelectedValue());
                } catch (OneWireException ex) {
                    Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex) {
                Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{

                }
            }
            else
            {
                try {
                    thermochron.deleteMemory();
                    displayDeviceInformation((OneWireContainer)buttonList.getSelectedValue());
                } catch (OneWireException ex) {
                    Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex) {
                Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_deleteMissionButtonActionPerformed

    private void buttonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListMouseClicked
        // TODO add your handling code here:
       
        try {

            displayDeviceInformation((OneWireContainer)buttonList.getSelectedValue());
            generateGraphThumbnail();
        } catch (OneWireException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonListMouseClicked
    /**
     * * Removing a mouseevent on a label
     *  @param label
     * JLabel where a mouseevent is present.
     */
    private void removeMouseListener(JLabel label){
        if(label.getMouseListeners().length>0)
            label.removeMouseListener(label.getMouseListeners()[0]);
    }
    
    private void generateGraphThumbnail(){
        int width = 250;
        int height = 250;
  
        customGraph_btn.setVisible(true);
        if(HYGROCHRON){
            thumb1.setIcon(null);
            thumb2.setIcon(null);
            thumb3.setIcon(null);
            
             thumb1.setVisible(true);
                thumb2.setVisible(true);
                thumb3.setVisible(true);
               
                try {
                hygrochron.generateGraph();
                } catch (Exception ex) {
                    thumb1.setText("No graphs found, is the mission setup?");
                    customGraph_btn.setVisible(false);
                }
                
            try {
                thumb1.setText("Temperature");
                removeMouseListener(thumb1);
                thumb1.setIcon(getThumbnail(hygrochron.TEMPERATURE_GRAPH_NAME, width, height));
                realtime = new RealTime(hygrochron.TEMPERATURE_GRAPH_NAME, "Temperature");
                thumb1.addMouseListener(realtime);

                thumb2.setText("Humidity");
                removeMouseListener(thumb2);
               
                thumb2.setIcon(getThumbnail(hygrochron.HUMIDITY_GRAPH_NAME, width, height));
                realtime = new RealTime(hygrochron.HUMIDITY_GRAPH_NAME, "Humidity");
                thumb2.addMouseListener(realtime);

                thumb3.setText("Temperature and Humidity");
                removeMouseListener(thumb3);
                
                thumb3.setIcon(getThumbnail(hygrochron.TEMP_HUM_GRAPH_NAME, width, height));
                realtime = new RealTime(hygrochron.TEMP_HUM_GRAPH_NAME, "Temperature and humidity");
                thumb3.addMouseListener(realtime);
            } catch (IOException ex) {
                 thumb1.setText("No graphs found, try to press refresh.");
                 customGraph_btn.setVisible(false);
            } catch(NullPointerException ne){
                  thumb1.setText("No graphs found, is the mission setup?");
                  customGraph_btn.setVisible(false);
            }
           
        }else{
            thumb1.setIcon(null);
            thumb1.setVisible(true);
              
                thumb2.setVisible(false);
                thumb3.setVisible(false);
                thumb2.setText("");
                thumb3.setText("");
                    try {
                        thermochron.generateGraph();
                    } catch (Exception ex) {
                        thumb1.setText("No graphs found, is the mission setup?");
                        customGraph_btn.setVisible(false);
                    }

                    try {
                            removeMouseListener(thumb1);
                             thumb1.setText("Temperature");
                            thumb1.setIcon(getThumbnail(thermochron.TEMPERATURE_GRAPH_NAME, width, height));
                            realtime = new RealTime(thermochron.TEMPERATURE_GRAPH_NAME, "Temperature");
                            thumb1.addMouseListener(realtime);
                        } catch (IOException ex) {
                            thumb1.setText("No graphs found, try to press refresh.");
                            customGraph_btn.setVisible(false);
                        } catch(NullPointerException ne){
                            thumb1.setText("No graphs found, is the mission setup?");
                            customGraph_btn.setVisible(false);
                        }
            
        }
    }

     private ImageIcon getThumbnail(String imagepath, int height, int width) throws IOException{
        ImageScaler scaler = new ImageScaler();
        BufferedImage image = null;
        image = ImageIO.read(new File(imagepath));
        return scaler.getImage(width, width, image);

    }
    private void customGraph_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customGraph_btnActionPerformed
        if(HYGROCHRON){
            try {
                new GraphViewer(hygrochron);
            } catch (Exception ex) {
                Box.Message("There seems to be a problem displaying the graphs, are you sure the mission is setup?");
            }
        }else{
            try {
                new GraphViewer(thermochron);
            } catch (Exception ex) {
                Box.Message("There seems to be a problem displaying the graphs, are you sure the mission is setup?");
            }
        }
    }//GEN-LAST:event_customGraph_btnActionPerformed

    
   
    private void tabPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPanel1MouseClicked
        
    }//GEN-LAST:event_tabPanel1MouseClicked

    private void tabPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPanelStateChanged
     
    }//GEN-LAST:event_tabPanelStateChanged

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Box.Message("Hygrochron and temperature reader..\n Written by Thomas and Freddy @ Hogskolen in Ostfold, Norway. ");
    }//GEN-LAST:event_jMenuItem1ActionPerformed
     private void showFolders()
    {
        System.out.println(this.historyPath);
        listModel = new DefaultListModel();
        directory = new File(this.historyPath);
        String[] children = directory.list();
        for (int i=0; i<children.length; i++) {
            listModel.addElement(children[i]);
        }
        folderList.setModel(listModel);
    }

    private void showFiles(String folder)
    {
        listModel = new DefaultListModel();
        try {
            files = new File(this.historyPath + folder + "\\");
            String[] children = files.list();
            System.out.println(System.getProperty("user.dir") + "\\Mission\\");
            for (int i = 0; i < children.length; i++) {
                listModel.addElement(children[i]);
                
            }
            fileList.setModel(listModel);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    private void previewFile(String file) throws IOException {
        ImageScaler scaleImage = new ImageScaler();
        System.out.println(file);
        try {
            File fileCheck = new File(this.historyPath + file);
            if(fileCheck.length() == 0 && autoDeleteCheckBox.isSelected())
            {
                String folder = (String)folderList.getSelectedValue()+"\\";
                fileCheck.delete();
                showFiles(folder);
            }
            else if (file.contains(".dat")) {
                graphPreview.setVisible(false);
                fileTextPanel.setVisible(true);
                fileText.setVisible(true);
                BufferedReader br = new BufferedReader(new FileReader(new File(this.historyPath + file)));
                
                String s = null;
                String tmp = null;
                while ((s = br.readLine()) != null) {
                    tmp += s + "\n\r";
                }
                br.close();
                fileText.setText(tmp);
            } else {
                fileTextPanel.setVisible(false);
                fileText.setVisible(false);
                graphPreview.setVisible(true);
                image = ImageIO.read(new File(this.historyPath + file));
                graphPreview.setIcon(scaleImage.getImage(infoPanel.getWidth(), infoPanel.getHeight(), image));
            }
        } catch (IOException iOException) {
            System.out.println("File does not exist.");
        }
    }
    private void fileListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileListMouseClicked
        // TODO add your handling code here:
        String folder = (String)folderList.getSelectedValue()+"\\";
        String file = (String)fileList.getSelectedValue();
        String fullPath = folder+file;
        try {
            previewFile(fullPath);
        } catch (IOException ex) {
            Logger.getLogger(BrowseFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_fileListMouseClicked

    private void folderListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folderListMouseClicked
        // TODO add your handling code here:
        String folder = (String)folderList.getSelectedValue();
        showFiles(folder);
}//GEN-LAST:event_folderListMouseClicked

    private void showFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFileButtonActionPerformed

        String folder = (String)folderList.getSelectedValue()+"\\";
        String file = (String)fileList.getSelectedValue();
        String fullPath = folder+file;
        if(fullPath.contains(".dat")) {
            try{
                JTextArea displayFile = new JTextArea(20, 60);
                displayFile.read(new FileReader(this.historyPath+fullPath), null);
                displayFile.setEditable(false);
                JOptionPane.showMessageDialog(null, new JScrollPane(displayFile));
            } catch(IOException ioe) {
            }
        } else{
            final ImageIcon image = new ImageIcon(this.historyPath+fullPath);
            JOptionPane.showMessageDialog(null, image, "Graph", JOptionPane.INFORMATION_MESSAGE, null);
        }
}//GEN-LAST:event_showFileButtonActionPerformed

    private void deleteFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFileButtonActionPerformed
        // TODO add your handling code here:
        String folder = (String)folderList.getSelectedValue()+"\\";
        String file = (String)fileList.getSelectedValue();
        String fullPath = folder+file;
        int n = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the selected file?","Delete File?",JOptionPane.YES_NO_OPTION);
        if(n==0) {
            File delFile = new File(this.historyPath+fullPath);
            delFile.delete();
            showFiles(folder);
        }
}//GEN-LAST:event_deleteFileButtonActionPerformed

    private void defaultPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultPathButtonActionPerformed
        // TODO add your handling code here:
        this.historyPath = System.getProperty("user.dir")+"\\Mission\\";

        showFolders();
}//GEN-LAST:event_defaultPathButtonActionPerformed

    private void changeFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFolderButtonActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(null,"Are you sure you want to change path?","Warning! Really change path?",JOptionPane.YES_NO_OPTION);

        if(n==0) {
            String newFolder = "";
            JFileChooser fileChoose = new JFileChooser();
            fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fileChoose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                newFolder =  fileChoose.getSelectedFile().toString();
            }
            System.out.println(this.historyPath);
            System.out.println(newFolder);
            this.historyPath = newFolder+"\\";
            showFolders();
        }
}//GEN-LAST:event_changeFolderButtonActionPerformed

    private void infoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoPanelMouseClicked
        // TODO add your handling code here:showFolders();
        String folder = (String) folderList.getSelectedValue() + "\\";
        String file = (String) fileList.getSelectedValue();
        String fullPath = folder + file;
        if (fullPath.contains(".dat")) {
            try {
                JTextArea displayFile = new JTextArea(20, 60);
                displayFile.read(new FileReader(this.historyPath + fullPath), null);
                displayFile.setEditable(false);
                JOptionPane.showMessageDialog(null,new JScrollPane(displayFile), "Data File", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioe) {
            }
        } else {
            final ImageIcon image = new ImageIcon(this.historyPath + fullPath);
            JOptionPane.showMessageDialog(null, image, "Graph", JOptionPane.INFORMATION_MESSAGE, null);
        }

    }//GEN-LAST:event_infoPanelMouseClicked

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        // TODO add your handling code here:
        JTextArea displayText = new JTextArea();
        displayText.setText("INFO\n"
                + "The 'folder' window shows what folders exists in the current path. By default the path is the application_folder/Mission where all graphs are stored\n"
                + "The 'files' window show the files in the selected folder from the 'folder' window.\n"
                + "The 'Change path' button allows you to change the path you want to browse. This is useful if you save your graphs in a seperate folder from the application_folder.\n"
                + "The 'Reset path' button resets the path to the default application_folder/Mission folder.\n"
                + "The 'Auto-delete empty files' checkbox automatically deletes files that are empty when you click on them.\n"
                + "The 'Delete file' button deletes the selected file from the 'Files' window.\n"
                + "The 'Show file' button shows the selected file from the 'Files' windows in its original size.\n"
                + "The 'Backup' button allows you to copy the selected file to a different folder.\n\n"
                + "HOW TO:\n"
                + "Select a folder in the 'folders' window.\n"
                + "Select a file in the 'file' window.\n"
                + "Click the preview (or the 'show file' button)to see the original.\n");
        JOptionPane.showMessageDialog(null, new JScrollPane(displayText), "HELP", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_helpButtonActionPerformed

    private void backupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupButtonActionPerformed
        // TODO add your handling code here:
        String folder = (String)folderList.getSelectedValue()+"\\";
        String file = (String)fileList.getSelectedValue();
        String fullPath = folder+file;
        File backupFile = new File(this.historyPath+fullPath);
        JFileChooser backupFolder = new JFileChooser();
        String newFolder = "";
        File newFile;
        backupFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (backupFolder.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            newFolder =  backupFolder.getSelectedFile().toString();
            newFile = new File(newFolder);
            Path source = backupFile.toPath();
            Path newDir = newFile.toPath();
            try {
                Files.copy(source, newDir.resolve(source.getFileName()));
            } catch (IOException ex) {
                Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                JOptionPane.showMessageDialog(null,"File successfully copied to desired location.");
            }
        }
        
        
    }//GEN-LAST:event_backupButtonActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        JTextArea displayText = new JTextArea();
        displayText.setText("MAIN WINDOW\n"
                + "Select an iButton from the 'select a button' window.\n"
                + "Inspect the current mission when the details arrive. (PS: Depending on the amount of data gathered on an ibutton; this may take some time!)\n"
                + "Choose to Delete Mission, Create New Mission, or let the current mission continue.\n"
                + "The 'Refresh Button List' rescans your 1-wire net for connected ibuttons\n\n"
                + "GRAPHS TAB\n"
                + "Watch your graphs be generated real time in the GRAPHS tab.\n"
                + "Generate a custom graph by pressing the butting.\n\n"
                + "HISTORY TAB\n"
                + "INFO\n"
                + "The 'folder' window shows what folders exists in the current path. By default the path is the application_folder/Mission where all graphs are stored\n"
                + "The 'files' window show the files in the selected folder from the 'folder' window.\n"
                + "The 'Change path' button allows you to change the path you want to browse. This is useful if you save your graphs in a seperate folder from the application_folder.\n"
                + "The 'Reset path' button resets the path to the default application_folder/Mission folder.\n"
                + "The 'Auto-delete empty files' checkbox automatically deletes files that are empty when you click on them.\n"
                + "The 'Delete file' button deletes the selected file from the 'Files' window.\n"
                + "The 'Show file' button shows the selected file from the 'Files' windows in its original size.\n"
                + "The 'Backup' button allows you to copy the selected file to a different folder.\n\n"
                + "HOW TO:\n"
                + "Select a folder in the 'folders' window.\n"
                + "Select a file in the 'file' window.\n"
                + "Click the preview (or the 'show file' button)to see the original.\n");
        displayText.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(displayText), "HELP", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void refreshButtonListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonListButtonActionPerformed
        // TODO add your handling code here:
        
        try {
            buttonList.setModel(new DefaultListModel());
            readDevices();
        } catch (OneWireIOException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OneWireException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            makeList();
        }
        
    }//GEN-LAST:event_refreshButtonListButtonActionPerformed

    private void buttonListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListMouseEntered
        // TODO add your handling code here:
        try {
            buttonList.setModel(new DefaultListModel());
            readDevices();
        } catch (OneWireIOException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OneWireException ex) {
            Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            makeList();
        }
    }//GEN-LAST:event_buttonListMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
               
                  new StartWindow().setVisible(true);
               
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField adressText;
    private javax.swing.JTextField alternativeNameText;
    private javax.swing.JCheckBox autoDeleteCheckBox;
    private javax.swing.JButton backupButton;
    private javax.swing.JLabel browseText;
    private javax.swing.JList buttonList;
    private javax.swing.JButton changeFolderButton;
    private javax.swing.JButton customGraph_btn;
    private javax.swing.JLabel dataHighAlarm;
    private javax.swing.JTextField dataHighAlarmText;
    private javax.swing.JLabel dataLogging;
    private javax.swing.JTextField dataLoggingText;
    private javax.swing.JLabel dataLowAlarm;
    private javax.swing.JTextField dataLowAlarmText;
    private javax.swing.JButton defaultPathButton;
    private javax.swing.JButton deleteFileButton;
    private javax.swing.JButton deleteMissionButton;
    private javax.swing.JTextArea descriptionText;
    private javax.swing.JList fileList;
    private javax.swing.JTextArea fileText;
    private javax.swing.JScrollPane fileTextPanel;
    private javax.swing.JLabel filesTextLabel;
    private javax.swing.JLabel firstSample;
    private javax.swing.JTextField firstSampleText;
    private javax.swing.JList folderList;
    private javax.swing.JLabel foldersTextLabel;
    private javax.swing.JLabel graphPreview;
    private javax.swing.JButton helpButton;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel missionRunningLabel;
    private javax.swing.JTextField missionRunningText;
    private javax.swing.JLabel missionStartLabel;
    private javax.swing.JTextField missionStartText;
    private javax.swing.JLabel missionStatusLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton newMissionButton;
    private javax.swing.JLabel newMissionLabel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshButtonListButton;
    private javax.swing.JLabel rolloverEnabled;
    private javax.swing.JTextField rolloverEnabledText;
    private javax.swing.JLabel sampleCountLabel;
    private javax.swing.JTextField sampleCountText;
    private javax.swing.JLabel sampleRate;
    private javax.swing.JTextField sampleRateText;
    private javax.swing.JLabel selectedDeviceLabel;
    private javax.swing.JButton showFileButton;
    private javax.swing.JLabel sutaEnabled;
    private javax.swing.JTextField sutaEnabledText;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JPanel tabPanel1;
    private javax.swing.JPanel tabPanel2;
    private javax.swing.JLabel tempHighAlarm;
    private javax.swing.JTextField tempHighAlarmText;
    private javax.swing.JLabel tempLowAlarm;
    private javax.swing.JTextField tempLowAlarmText;
    private javax.swing.JLabel thumb1;
    private javax.swing.JLabel thumb2;
    private javax.swing.JLabel thumb3;
    private javax.swing.JLabel totalDeviceSamples;
    private javax.swing.JTextField totalDeviceSamplesText;
    // End of variables declaration//GEN-END:variables
}
