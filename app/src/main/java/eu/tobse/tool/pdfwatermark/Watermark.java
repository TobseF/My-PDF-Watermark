/*
 * @version 0.0 09.02.2009
 * @author Tobse F
 */
package eu.tobse.tool.pdfwatermark;

import com.lowagie.text.DocumentException;
import jpdftweak.core.PdfInputFile;
import jpdftweak.core.PdfTweak;

import java.io.File;
import java.io.IOException;

public class Watermark {

    public Watermark() throws IOException, DocumentException {
        this(null);
    }

    public Watermark(File inputFile) throws IOException, DocumentException {
        AppProperties appProperties = new AppProperties();
        appProperties.readProperties();
        if (inputFile == null) {
            new Splash();
        } else {
            PdfTweak pdfTweak = new PdfTweak(new PdfInputFile(inputFile, ""));
            pdfTweak.addWatermark(new PdfInputFile(appProperties.getWatermarkFile(), ""), "", 0, 0, 0, 0, 0, 0);
            File convertedFile = appProperties.getSaveFilePath(inputFile);
            pdfTweak.writeOutput(convertedFile.getAbsolutePath(), false, false);
            System.out.println("Saved PDF file with watermark to: " + convertedFile.getAbsolutePath());
        }
    }


}