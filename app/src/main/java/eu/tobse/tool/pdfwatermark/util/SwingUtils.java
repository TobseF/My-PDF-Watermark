/*
 * @version 0.0 19.02.2009
 * @author Tobse F
 */
package eu.tobse.tool.pdfwatermark.util;

import javax.swing.*;
import java.awt.*;

public class SwingUtils {

    public static boolean setCoolLookAndFeel() {
        return (setNimbusLookAndFeel() || setSystemLookAndFeel());
    }

    public static boolean setSystemLookAndFeel() {
        boolean isSystemLookAndFeelSet = true;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            isSystemLookAndFeelSet = false;
        }
        return isSystemLookAndFeelSet;
    }

    public static boolean setNimbusLookAndFeel() {
        boolean isNumbusSet = true;
        try { // Nimbus LookAndFeelInfo
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException e) {
            isNumbusSet = false;
        }
        return isNumbusSet;
    }


    public static void setLocationToCenter(Window window) {
        window.setLocationRelativeTo(null);
    }
}