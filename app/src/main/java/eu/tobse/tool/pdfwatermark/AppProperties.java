package eu.tobse.tool.pdfwatermark;

import org.apache.commons.lang3.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class AppProperties {

    /**
     * Taages the converted PDF File
     */
    private static final String NEW_SUFFIX = "_w";
    /**
     * PDF File extension
     */
    private static final String PDF_FILE_EXTENSION = ".pdf";
    /**
     * Config file Name which stores the {@link Properties}
     */
    private static final String CONFIG_FILE = "config.ini";
    private Properties properties;
    private String savePath, watermarkFile;

    public void readProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileReader(CONFIG_FILE));
            } catch (IOException e) {
                createDefaultProperties();
            }
        }
        String watermark_file = properties.getProperty("watermark_file");
        String save_path = properties.getProperty("save_path");
        if (watermark_file.isEmpty() || save_path.isEmpty()) {
            createDefaultProperties();
        } else {
            savePath = save_path;
            watermarkFile = watermark_file;
            if (savePath.equals("desktop")) {
                savePath = getDesktopDirectory().getAbsolutePath();
            }
        }
    }

    public File getWatermarkFile() {
        return new File(watermarkFile);
    }

    public File getSaveFilePath(File resourceFile) {
        String savePath = getPropertySavePath();
        if (savePath.isEmpty() || savePath.equals("same")) {
            // Save the file to the same location
            return addSuffix(resourceFile, getNewSuffix());
        } else {
            String newFileName = addSuffix(resourceFile.getName(), getNewSuffix());
            // Save to desktop
            return Paths.get(getDesktopDirectory().getAbsolutePath(), newFileName).toFile();
        }
    }

    public String getNewSuffix() {
        return properties.getProperty("new_file_suffix", NEW_SUFFIX);
    }

    private String getPropertySavePath() {
        return properties.getProperty("save_path");
    }


    public File getDesktopDirectory() {
        return FileSystemView.getFileSystemView().getHomeDirectory();
    }

    private void createDefaultProperties() {
        properties = new Properties();
        properties.setProperty("watermark_file", "watermark.pdf");
        properties.setProperty("save_path", "desktop");
        properties.setProperty("new_file_suffix", "_w");
        try {
            properties.store(new FileOutputStream(CONFIG_FILE), "Watermark config file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File addSuffix(File fileName, String suffix) {
        return new File(addSuffix(fileName.getAbsolutePath(), suffix));
    }

    /**
     * Adds the {@link #NEW_SUFFIX} behind the {@link #PDF_FILE_EXTENSION}
     *
     * @param fileName which should be tagged with the {@link #NEW_SUFFIX}
     * @return fileName with {@link #NEW_SUFFIX} behind the {@link #PDF_FILE_EXTENSION}
     */
    private String addSuffix(String fileName, String suffix) {
        return StringUtils.substringBeforeLast(fileName, ".") + suffix + PDF_FILE_EXTENSION;
    }

}
