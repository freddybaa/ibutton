/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import javax.swing.JOptionPane;

/**
 * Simple static class for displaying simple and clean messageboxes
 * @author Baardsen
 */
public class Box {
    /**
     * Click option: YES
     */
    public static int YES = 0;
    /**
     * Click option: NO
     */
    public static int NO = 1;
    /**
     * Normal message box.
     * @param title
     * String - The message in the box
     */
    public static void Message(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
    /**
     * Normal confirm box. This is used with the static variables YES and NO
     * @param message
     * String - Message
     * @param title
     * String - Title of the box
     * @return
     * int - option selected 0 or 1.
     */
    public static int Confirm(String message, String title){
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }


}
