/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Gnuplot.Terminal;



/**
 * Basic Terminal template for gnuplot
 * @author Baardsen
 */
public class GnuplotTerminalPostScript implements GnuplotTerminal {

    private String size = "";
    private String type = "postscript";

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
