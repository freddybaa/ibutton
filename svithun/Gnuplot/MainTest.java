/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Gnuplot;

import Gnuplot.Plot.Gnuplot;
import Gnuplot.Plot.GnuplotDateFormat;
import Gnuplot.Plot.Plot;
import Gnuplot.Terminal.GnuplotTerminalPNG;
import Gnuplot.Terminal.GnuplotTerminal;


/**
 *
 * @author Baardsen
 */
public class MainTest {
    /**
     * Demostrating the gnuplot program
     * @param args
     * @throws Exception
     */
    public static void main(String [] args) throws Exception{
         Gnuplot gnuplot = new Gnuplot("K:\\GNUPLOT\\bin\\pgnuplot.exe");
        GnuplotTerminal terminal = new GnuplotTerminalPNG();
        terminal.setSize("1920,1080");
        gnuplot.setTerminal(terminal);
        //setting the output name accordingly to the terminal type
        gnuplot.setOutputName("deadlift.png");

        gnuplot.setTitle("Deadlift - Freddy");
        gnuplot.setYLabel("KG");
        gnuplot.setXLabel("Date");

        //displaying grid
        gnuplot.setGrid(true);
        gnuplot.set("set ytics 5");
        GnuplotDateFormat format = new GnuplotDateFormat();
        format.setOldFormat("%d.%m.%Y_%H:%M:%S");
        format.setOutputFormat("%d.%m.%Y-%H:%M");
        //predefined plot using textfile 'data.dat'
        Plot p = new Plot("data.dat" ,"1:2", Plot.GRAPH_COLOR_BLUE, "Deadlift", Plot.GRAPH_LINESTYLE_LINES, format);
        //plotting the predefined plot
        gnuplot.plot(p);

       
    }
}
