package eu.tobse.tool.pdfwatermark;

import org.apache.commons.lang3.StringUtils;
import org.ini4j.Wini;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class AppProperties {

    /**
     * Appended suffix for the converted PDF File
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
    /**
     * Section name in INI CONFIG_FILE
     */
    private static final String INI_SECTION = "watermark";
    private Wini ini;
    private String watermarkFile;

    public void readProperties() {
        if (ini == null) {
            try {
                ini = new Wini(new File(CONFIG_FILE));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        watermarkFile = getProperty("watermark_file", "watermark.pdf");
    }

    public String getProperty(String key) {
        return ini.get(INI_SECTION, key);
    }

    public String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return StringUtils.defaultIfEmpty(value, defaultValue);
    }

    public File getWatermarkFile() {
        return new File(watermarkFile);
    }

    public File getSaveFilePath(File resourceFile) {
        String savePath = getPropertySavePath();
        String newFileName = addSuffix(resourceFile.getName(), getNewSuffix());
        if (savePath.isEmpty() || savePath.equals("same")) {
            // Save the file to the same location
            return addSuffix(resourceFile, getNewSuffix());
        } else if(savePath.equals("desktop")){
            // Save to desktop
            return Paths.get(getDesktopDirectory().getAbsolutePath(), newFileName).toFile();
        }else {
            // Save to a custom location
            return Paths.get(savePath, newFileName).toFile();
        }
    }

    public String getNewSuffix() {
        return getProperty("new_file_suffix", NEW_SUFFIX);
    }

    private String getPropertySavePath() {
        return getProperty("save_path", "desktop");
    }

    public File getDesktopDirectory() {
        return FileSystemView.getFileSystemView().getHomeDirectory();
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
