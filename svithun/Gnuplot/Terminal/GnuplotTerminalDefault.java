/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Gnuplot.Terminal;

import Gnuplot.OS.OS_indentifier;
import Gnuplot.*;


/**
 * Basic Terminal template for gnuplot
 * @author Baardsen
 */

public class GnuplotTerminalDefault implements GnuplotTerminal{
   
    private String size = "";
    private String type = "";

    /**
     * Default
     */
    public GnuplotTerminalDefault(){
        if(OS_indentifier.isWindows()){
            this.type = "windows";
        }else{
            this.type = "wxt";
        }
    }
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
