/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ibutton.Result;

import Ibutton.Interface.Convert;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Baardsen
 */
public class Result implements Convert{
    /**
     * Temperature
     */
    public double temperature;
    /**
     * Date
     */
    public String date;
    private final String DATE_FORMAT = "dd.MM.yyyy_HH:mm:ss";

    /**
     * Takes temperature and timestamp as parameters
     * @param temperature double
     * @param timestamp long
     */
    public Result(double temperature, long timestamp){
        this.temperature = temperature;
        this.date = convertDate(timestamp);
    }
    /**
     * Getting the temperature
     * @return double
     */
    public double getTemperature(){ return this.temperature; }
    /**
     * Getting the date
     * @return String
     */
    public String getDate(){ return this.date; }
    /**
     * Converting the time given in miliseconds to a date
     * @param miliseconds Long
     * @return String
     */
    public String convertDate(long miliseconds){
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miliseconds);
        format.setTimeZone(TimeZone.getTimeZone("CET"));
        return format.format(calendar.getTime());
    }
}
