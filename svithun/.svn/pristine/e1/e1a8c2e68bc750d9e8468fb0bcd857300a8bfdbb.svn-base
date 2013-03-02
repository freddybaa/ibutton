/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ibutton.DeviceHandler;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.OneWireContainer21;
import com.dalsemi.onewire.container.OneWireContainer41;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * This class is used to scan the connected USB device for
 * different ibutton devices.
 * @author Baardsen
 */
public class DeviceReader implements IDevice{

    private DSPortAdapter adapter;
    private OneWireContainer owd;
    private ArrayList<OneWireContainer> devices = new ArrayList<OneWireContainer>();



    private String name;
    private String alternateName;
    private String adress;
    private String description;

    /**
     * Setting up the adapter and starting the scanning process.
     * @throws OneWireIOException
     * @throws OneWireException
     */
    @SuppressWarnings("static-access")
    public DeviceReader() throws OneWireIOException, OneWireException{
       
        adapter = OneWireAccessProvider.getDefaultAdapter();
        adapter.beginExclusive(true);
        adapter.setSearchAllDevices();
        adapter.targetAllFamilies();
        adapter.setSpeed(DSPortAdapter.SPEED_FLEX);
        findAllDevices();
        adapter.endExclusive();
     

    }
   /**
     * Getting the adress
     * @return String
     */
    public String getAdress(){ return this.adress;}
    /**
     * Getting the device description
     * @return String
     */
    public String getDescription(){ return this.description; }
    /**
     * Getting the device name
     * @return String
     */
    public String getName(){ return this.name; }
   /**
    * Getting the device alternativename
    * @return String
    */
    public String getAlternativeName(){ return this.alternateName; }

    /**
     * Holding all the devices found.
     * @return ArrayList<OneWireContainer>
     */
    public ArrayList<OneWireContainer> getDevices(){ return this.devices; }

    /**
     * Searching for the ibutton devices connected to the USB bus.
     * @throws OneWireIOException
     * @throws OneWireException
     */
    private void findAllDevices() throws OneWireIOException, OneWireException{

        for (Enumeration owd_enum = adapter.getAllDeviceContainers();owd_enum.hasMoreElements();){

            //making a new OneWirecontainer object of the device found.
            owd = ( OneWireContainer ) owd_enum.nextElement();


            //picking up hygrochron and thermochron devices
            if(owd instanceof OneWireContainer41){
                OneWireContainer41 owc41 = (OneWireContainer41) owd;
                 // read the device config byte just to force the init of
                 // description and other parameters.
                owc41.readDevice();
                devices.add(owc41);
            }
            //picking up adapter and setting basic adapter information.
            else{
                //set adapter information
                this.name = owd.getName();
                this.alternateName = owd.getAlternateNames();
                this.adress = owd.getAddressAsString();
                this.description = owd.getDescription();
            }
           
         }
    }

}
