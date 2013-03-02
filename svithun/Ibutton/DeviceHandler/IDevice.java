/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ibutton.DeviceHandler;

/**
 * Describes basic information methods
 * @author Baardsen
 */
public interface IDevice {
    
    /**
     * Getting the device adress
     * @return String
     */
    public String getAdress();
    /**
     * Getting the device description
     * @return String
     */
    public String getDescription();
    /**
     * Getting the devices alternative name
     * @return String
     */
    public String getAlternativeName();
    /**
     * Getting the device name
     * @return String
     */
    public String getName();
}
