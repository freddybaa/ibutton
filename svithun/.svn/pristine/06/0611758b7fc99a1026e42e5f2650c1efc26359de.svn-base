/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Gnuplot.Plot;

/**
 * Setting up the date range to be displayed on the graph
 * @author Baardsen
 */
public class GnuplotDateRange {
    private String day;
    private String month;
    private int year;

    private String stop_day;
    private String stop_month;
    private int stop_year;

    private String startDate;
    private String stopDate;

    private boolean wholeDate = false;
    /**
     * Setting up the start date from where you want to display the start of the graph
     * @param day - String 01,02...........31
     * @param month - String 01..............12
     * @param year - int 2012
     */
    /**
     *
     * @param day
     * @param month
     * @param year
     */
    public void startDate(String day, String month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Method for setting the whole start date range
     * @param date
     * Date format same as the data
     */
    public void startDate(String date){
        wholeDate = true;
        this.startDate = date;
    }
    /**
     * Method for setting the stop date range
     * @param date
     * Date format same as the data to be plotted.
     */
    public void stopDate(String date){
        this.stopDate = date;
    }
    /**
     * Setting up the stop date for the graph.
     * @param day - String 01,02...........31
     * @param month - String 01..............12
     * @param year - int 2012
     */
    /**
     *
     * @param day
     * @param month
     * @param year
     */
    public void stopDate(String day, String month, int year){
        this.stop_day = day;
        this.stop_month = month;
        this.stop_year = year;
    }

    /**
     * Getting the date range for the graph
     * @return String
     */
    /**
     * FIX
     * @return
     */
    public String getRange(){
        if(wholeDate){
            return String.format("[\"%s\":\"%s\"]",
                startDate,
                stopDate);
        }else{
           return String.format("[\"%s.%s.%s\":\"%s.%s.%s\"]",
                this.day,
                this.month,
                this.year,
                this.stop_day,
                this.stop_month,
                this.stop_year);
        }
        
    }
}
