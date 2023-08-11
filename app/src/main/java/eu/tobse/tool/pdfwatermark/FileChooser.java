package eu.tobse.tool.pdfwatermark;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.function.Consumer;

public class FileChooser extends JFileChooser {
    public FileChooser(Consumer<File> action) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int chosenOption = fileChooser.showOpenDialog(null);
        if (chosenOption == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            action.accept(selectedFile);
        }
    }
}
