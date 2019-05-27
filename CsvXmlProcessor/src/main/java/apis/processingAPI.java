package apis;

import org.xml.sax.SAXException;
import processing.FileService;
import processing.DataFileType;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub Filipiak on 20.05.2019.
 */
public class processingAPI {

    private FileService fileService = new FileService();

    public boolean isFileCorrect(String pathname) {

        if (fileService.fileExists(pathname)) {
            List<DataFileType> acceptedTypes = new ArrayList<>();
            acceptedTypes.add(DataFileType.CSV);
            acceptedTypes.add(DataFileType.XML);

            for (DataFileType type : acceptedTypes) {
                if (fileService.isFileTypeCorrect(pathname, type))
                    return true;
            }
            return false;
        }
        return false;
    }

    public void processFile(String pathname) throws IOException, ParserConfigurationException, SAXException {

        DataFileType type = fileService.getFileType(pathname);
        switch (type) {
            case CSV:
                System.out.println("Processing CSV...");
                fileService.processCSV(pathname);
                break;
            case XML:
                System.out.println("Processing XML...");
                fileService.processXML(pathname);
                break;
        }
    }
}
