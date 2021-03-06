/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Gnuplot.Plot;


/**
 * This is a class used to easily handle the plotting process in Gnuplot.
 * It contains of four different contructors:
 * 1. Plot multiple files without dateformat
 * 2. Plot multiple files with dateformat
 * 3. Plot single file without dateformat
 * 4. Plot single file with dateformat
 * @author Baardsen
 */
public class Plot {

   
    public static final String GRAPH_COLOR_RED = "#ff0004";
    
    public static final String GRAPH_COLOR_BLUE = "#0061ff";
    
    public static final String GRAPH_COLOR_ORANGE = "#ff8c00";
   
    public static final String GRAPH_COLOR_BLACK = "#000000";
    
   
    public static final String GRAPH_COLOR_PINK = "#e100ff";
    
    public static final String GRAPH_COLOR_GREEN = "#329610";
   
    public static final String GRAPH_COLOR_YELLOW = "#fff600";


    public static final String GRAPH_LINESTYLE_LINEPOINTS = "linespoint";
    public static final String GRAPH_LINESTYLE_LINES = "lines";
    public static final String GRAPH_LINESTYLE_DOTS = "dots";
    public static final String GRAPH_LINESTYLE_POINTS = "points";
    public static final String GRAPH_LINESTYLE_IMPULSES = "impulses";
    public static final String GRAPH_LINESTYLE_STEPS = "steps";

    //dateformat object
    private GnuplotDateFormat dateformat = null;
    //plotting string generated based on the chosen contructor
    private String plotString = "";

    //get methods
    /**
     * Method to get an instance of the dateformat
     * @return
     * Dateformat object
     */
    public GnuplotDateFormat getDateFormat(){ return this.dateformat; }
    /**
     * Method to get the plotstring
     * @return
     * String to plot
     */
    public String getPlotString(){ return this.plotString; }

    /**
     * Contructor to handle multiple files without dateformat
     * @param files
     * String [] with the filepath
     * @param linesToUse
     * String [] with the lines you want to use
     * @param color
     * String [] with the hex color to be used on the graph
     * @param graphLabel
     * String [] with labels to be display on each graph
     * @param lineType
     * String [] with graphlines
     */
    public Plot(String [] files, String [] linesToUse, String [] color, String [] graphLabel, String [] lineType){
        this.plotString = "plot '"+files[0]+"' using "+linesToUse[0]+" title \""+graphLabel[0]+"\" with "+ lineType[0] + " lc rgb \""+color[0]+"\"";
        for(int i=1;i<files.length;i++){
            this.plotString += ", '"+files[i]+"' using "+linesToUse[i]+" title \""+graphLabel[i]+"\" with "+ lineType[i] + " lc rgb \""+color[i]+"\"";
        }
    }

     /**
     * Contructor to handle multiple files with dateformat
     * @param files
     * String [] with the filepath
     * @param linesToUse
     * String [] with the lines you want to use
     * @param color
     * String [] with the hex color to be used on the graph
     * @param graphLabel
     * String [] with labels to be display on each graph
      * @param lineType
      * String [] with linetypes
      * @param dateformat
     * A GnuplotDateFormat object with the dateformat
     */
    public Plot(String [] files, String [] linesToUse, String [] color, String [] graphLabel, String [] lineType, GnuplotDateFormat dateformat){
        this.plotString = "plot '"+files[0]+"' using "+linesToUse[0]+" title \""+graphLabel[0]+"\" with "+ lineType[0] + " lc rgb \""+color[0]+"\"";
        for(int i=1;i<files.length;i++){
            this.plotString += ", '"+files[i]+"' using "+linesToUse[i]+" title \""+graphLabel[i]+"\" with "+ lineType[i] + " lc rgb \""+color[i]+"\"";
        }
        this.dateformat = dateformat;
    }

     /**
     * Contructor to handle multiple files with dateformat and daterange
     * @param files
     * String [] with the filepath
     * @param linesToUse
     * String [] with the lines you want to use
     * @param color
     * String [] with the hex color to be used on the graph
     * @param graphLabel
     * String [] with labels to be display on each graph
      * @param lineType
      * String [] with linetype
      * @param dateformat
     * A GnuplotDateFormat object with the dateformat
     * @param range
     * A GnuplotDateRange object with the date ranges to be plotted
     */
    public Plot(String [] files, String [] linesToUse, String [] color, String [] graphLabel, String [] lineType, GnuplotDateFormat dateformat, GnuplotDateRange range){
        this.plotString = "plot " + range.getRange() + " '"+files[0]+"' using "+linesToUse[0]+" title \""+graphLabel[0]+"\" with "+ lineType[0] + " lc rgb \""+color[0]+"\"";
        for(int i=1;i<files.length;i++){
            this.plotString += ", " + " '"+files[i]+"' using "+linesToUse[i]+" title \""+graphLabel[i]+"\" with "+ lineType[i] + " lc rgb \""+color[i]+"\"";
        }
        this.dateformat = dateformat;
    }

     /**
     * Contructor to handle single file without dateformat
      * @param file
      * @param linesToUse
     * String with the lines you want to use
     * @param color
     * String with the hex color to be used on the graph
     * @param graphLabel
      * String with labels to be display on each graph
      * @param lineType
      * String with linetype
     */
    public Plot(String file, String linesToUse, String  color, String graphLabel, String lineType){
        this.plotString = "plot '"+file+"' using "+linesToUse+" title \""+graphLabel+"\" with "+lineType+" lc rgb \""+color+"\"";
    }

    /**
     * Contructor to handle single file with dateformat
     * @param file
     * @param linesToUse
     * String with the lines you want to use
     * @param color
     * String with the hex color to be used on the graph
     * @param graphLabel
     * String  with labels to be display on each graph
     * @param lineType
     * String with linetype
     * @param dateformat
     * A GnuplotDateFormat object with the dateformat
     */
    public Plot(String file, String linesToUse, String  color, String graphLabel, String lineType, GnuplotDateFormat dateformat){
        this.plotString = "plot '"+file+"' using "+linesToUse+" title \""+graphLabel+"\" with "+ lineType + " lc rgb \""+color+"\"";
        this.dateformat = dateformat;
    }
     /**
     * Contructor to handle single file with dateformat and daterange
     * @param file
     * @param linesToUse
     * String with the lines you want to use
     * @param color
     * String with the hex color to be used on the graph
     * @param graphLabel
     * String  with labels to be display on each graph
      * @param lineType - Line you want to draw the graph in
      * @param dateformat - Dateformat object with the correct date formatting
     * A GnuplotDateFormat object with the dateformat
     * @param range 
     * A GnuplotDateRange object with the start/stop dates set
     */
    public Plot(String file, String linesToUse, String  color, String graphLabel, String lineType, GnuplotDateFormat dateformat, GnuplotDateRange range){
        this.plotString = "plot " + range.getRange() + " '"+file+"' using "+linesToUse+" title \""+graphLabel+"\" with "+ lineType + " lc rgb \""+color+"\"";
        this.dateformat = dateformat;
    }

 
}
