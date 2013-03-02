/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Gnuplot.Plot;

/**
 *
 * @author Baardsen
 */
public class GnuplotDateFormat {
    private String oldFormat = "";
    private String newFormat = "";
    /**
     * Default date formatting
     */
    /**
     *
     */
    public  String DEFAULT = "%d.%m.%Y_%H:%M:%S";

    //old format from the textfile
    /**
     *
     * @param format
     */
    public void setOldFormat(String format){ this.oldFormat = format; }
    //the new format you want to represent the graph with
    /**
     *
     * @param newFormat
     */
    public void setOutputFormat(String newFormat){ this.newFormat = newFormat; }

    //get methods
    /**
     *
     * @return
     */
    public String getOldFormat() { return this.oldFormat; }
    /**
     *
     * @return
     */
    public String getNewFormat() { return this.newFormat; }


}
