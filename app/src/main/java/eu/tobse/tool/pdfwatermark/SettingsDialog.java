package eu.tobse.tool.pdfwatermark;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {

    public SettingsDialog(Splash splash, Image icon) {
        super(splash);
        setTitle("Settings");
        setIconImage(icon);
        setSize(284, 280);
        setLocationRelativeTo(splash);
        setLayout(new FlowLayout());
    }
}
