/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ibutton;

import Ibutton.DeviceHandler.DeviceReader;
import Ibutton.DeviceHandler.Hygrochron;
import Ibutton.DeviceHandler.Thermochron;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;
import java.util.ArrayList;


/**
 *
 * @author Baardsen
 */
public class NewMission {

    private static String HYGROCHRON = "hygrochron";

    /**
     * Main entry point to the program
     * @param args
     * @throws OneWireIOException
     * @throws OneWireException
     * @throws Exception
     */
    /**
     *
     * @param args
     * @throws OneWireIOException
     * @throws OneWireException
     * @throws Exception
     */
    public static void main(String [] args) throws OneWireIOException, OneWireException, Exception{
         DeviceReader dr = new DeviceReader();

        System.out.printf("%s - %s - %s - %s\n", dr.getName(), dr.getAlternativeName(), dr.getAdress(), dr.getDescription());

        //finding hygrocron/thermacron
        ArrayList<OneWireContainer> devices = new ArrayList<OneWireContainer>();
        devices = dr.getDevices();

        for(int i=0;i<devices.size();i++){

            if(devices.get(i).getAlternateNames().equals(HYGROCHRON)){
                Hygrochron hc = new Hygrochron(devices.get(i));
                System.out.println(hc.getMissionInformation());
                hc.deleteMemory();
                hc.startMission(300, 0);
                 System.out.println(hc.getMissionInformation());
            }else{
               Thermochron tc = new Thermochron(devices.get(i));
               System.out.println(tc.getMissionInformation());
               tc.deleteMemory();
               tc.startMission(300,0);
                System.out.println(tc.getMissionInformation());
            }
    }
}

}
