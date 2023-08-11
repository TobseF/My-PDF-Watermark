/*
 * @version 0.0 22.02.2009
 * @author Tobse F
 */
package eu.tobse.tool.pdfwatermark;

import com.lowagie.text.DocumentException;
import eu.tobse.tool.pdfwatermark.util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static eu.tobse.tool.pdfwatermark.util.ImageUtil.readImage;

public class Splash extends JFrame {

    String windowTitle = "My PDF Watermark";
    Color background = new Color(250, 240, 210);

    public Splash() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(windowTitle);
        setSize(284, 280);
        setLayout(new FlowLayout());
        setResizable(false);
        setBackground(background);
        SwingUtils.setCoolLookAndFeel();
        SwingUtils.setLocationToCenter(this);
        setIconImage(readImage("icon.png"));


        DropDownPanel dropDownPanel = new DropDownPanel();
        dropDownPanel.setFileDropAction(this::convertPdf);
        dropDownPanel.setChoseFileAction(this::convertPdf);
        add(dropDownPanel);


        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBackground(background);
        add(buttons);
        Image settingsIcon = readImage("gear.png");

        JButton open = new JButton("Open");
        open.addActionListener(e -> new FileChooser(this::convertPdf));
        buttons.add(open);

        setVisible(true);
    }
    

    public void convertPdf(File file) {
        try {
            new Watermark(file);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

}