/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Gnuplot.Terminal;

/**
 * Basic Terminal template for gnuplot
 * @author Baardsen
 */
public class GnuplotTerminalPNG implements GnuplotTerminal{
    private String filename = "";
    private String size = "";
    private String type = "png";

    /**
     *
     * @param size
     */
    public void setSize(String size){
        this.size = size;
    }
    /**
     *
     * @return
     */
    public String getSize(){ return this.size; }
    /**
     *
     * @return
     */
    public String getType() { return this.type; }

}
