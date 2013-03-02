/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ibutton.DeviceHandler;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Baardsen
 */
public class DeviceData {

    private String id;
    private String device_type;
    private String MISSION_DIRECTORY;
    private String DEVICE_FOLDER;
    /**
     * Takes device id, device type as parameters.
     * These values are used to generate the device folder where all the raw data files and graphs are stored.
     * @param id String
     * @param device_type String
     * @throws IOException
     */
    public DeviceData(String id, String device_type) throws IOException{
        this.id = id;
        this.device_type = device_type;
        new File("Mission").mkdir();
        this.MISSION_DIRECTORY = new File(".").getCanonicalPath()+"\\Mission";
        makeDeviceFolder();
    }
    /**
     * Generating the device folder
     * @return String - path to folder
     */
    public String makeDeviceFolder(){
        this.DEVICE_FOLDER = MISSION_DIRECTORY + "\\" + device_type + "_" + id;
        new File(DEVICE_FOLDER).mkdir();
        return DEVICE_FOLDER;
    }
}
