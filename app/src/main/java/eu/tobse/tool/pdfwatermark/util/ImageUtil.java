package eu.tobse.tool.pdfwatermark.util;

import eu.tobse.tool.pdfwatermark.App;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
    private static InputStream getResource(String fileName) {
        return App.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static BufferedImage readImage(String fileName) throws IOException {
        InputStream resource = getResource(fileName);
        if (resource == null) {
            throw new IOException("Cannot find resource: " + fileName);
        }
        return ImageIO.read(resource);
    }
}
