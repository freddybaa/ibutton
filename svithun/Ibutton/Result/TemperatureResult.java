/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ibutton.Result;

import Ibutton.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Make a new Temperature result.
 * Takes temperature and timestamp as parameters.
 * @author Baardsen
 */
public class TemperatureResult extends Result{
    /**
     * Takes temperature and timestamp as parameters to create a temperature record.
     * @param temperature double
     * @param timestamp long
     */
    public TemperatureResult(double temperature, long timestamp){
        super(temperature, timestamp);
    }

    @Override
    public String getDate() {
        return super.getDate();
    }

    @Override
    public double getTemperature() {
        return super.getTemperature();
    }


}
