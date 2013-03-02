/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Ibutton.Interface;

/**
 * Describes methods for performing miliseconds to date converting.
 * @author Baardsen
 */
public interface Convert {
    /**
     * Converting a time given in miliseconds to a date
     * @param milliseconds Long
     * @return String
     */
    String convertDate(long milliseconds);
}
