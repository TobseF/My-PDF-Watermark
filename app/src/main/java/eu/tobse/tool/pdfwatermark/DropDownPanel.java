package eu.tobse.tool.pdfwatermark;

import eu.tobse.tool.pdfwatermark.util.ImageUtil;
import eu.tobse.tool.pdfwatermark.util.awt.FileDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class DropDownPanel extends JPanel {

    private final BufferedImage image = ImageUtil.readImage("help-y.png");

    public DropDownPanel() throws IOException {
        super(new GridLayout(3, 3));
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        setOpaque(false);
    }

    public void setChoseFileAction(Consumer<File> action) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FileChooser(action);
            }
        });
    }

    public void setFileDropAction(Consumer<File> action) {
        new FileDrop(this, files -> {
            for (File file : files) {
                action.accept(file);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, 0, 0, this);
        super.paintComponent(g);
    }
}
