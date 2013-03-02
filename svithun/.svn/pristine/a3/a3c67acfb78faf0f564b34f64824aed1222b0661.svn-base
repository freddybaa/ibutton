package Ibutton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import Ibutton.DeviceHandler.Device;
import Ibutton.DeviceHandler.DeviceReader;
import Ibutton.DeviceHandler.Hygrochron;
import Ibutton.DeviceHandler.Thermochron;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.OneWireContainer;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Baardsen
 */
public class Main {


    /**
     * Hygrochron device
     */
    private static String HYGROCHRON = "Hygrochron";

    /**
     * Main program, demostrating the communcation with the ibutton devices.
     * @param args
     * @throws OneWireException
     * @throws IOException
     * @throws Exception
     */
    public static void main(String [] args) throws OneWireException, IOException, Exception{
        DeviceReader dr = new DeviceReader();

        System.out.printf("%s - %s - %s - %s\n", dr.getName(), dr.getAlternativeName(), dr.getAdress(), dr.getDescription());

        //finding hygrocron/thermacron
        ArrayList<OneWireContainer> devices = new ArrayList<OneWireContainer>();
        devices = dr.getDevices();

        for(int i=0;i<devices.size();i++){

            if(devices.get(i).getAlternateNames().equals(HYGROCHRON)){
                Hygrochron hc = new Hygrochron(devices.get(i));
                System.out.println(hc.getName() + " - " + hc.getAlternativeName());
                System.out.println(hc.getDescription());
                System.out.println();
                System.out.println(hc.getMissionInformation());
                hc.readHumidity();
                hc.readTemperatures();
                hc.generateGraph();
            }else{
               Thermochron tc = new Thermochron(devices.get(i));
               System.out.println(tc.getName() + " - " + tc.getAlternativeName());
               System.out.println(tc.getDescription());
               System.out.println();
              
               tc.readTemperatures();
               tc.generateGraph();
               System.out.println(tc.getMissionInformation());
            }
        }

       
        

        
        
    }
}
