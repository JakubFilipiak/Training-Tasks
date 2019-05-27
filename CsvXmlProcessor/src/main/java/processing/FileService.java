package processing;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jakub Filipiak on 20.05.2019.
 */
public class FileService {

    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());

    public boolean fileExists(String pathname) {

        boolean exists = new File(pathname).exists();
        if (!exists) {
            LOGGER.log(Level.WARNING, "File does not exist");
        }
        return exists;
    }

    public DataFileType getFileType(String pathname) {

        String extension = pathname.substring(pathname.lastIndexOf("."));
        return DataFileType.fromString(extension);
    }

    public boolean isFileTypeCorrect(String pathname, DataFileType expectedType) {

        DataFileType type = getFileType(pathname);
        if (type != expectedType) {
            LOGGER.log(Level.WARNING, "Wrong file type");
            return false;
        }
        return true;
    }

    public BufferedReader createBufferedReader(String pathname) throws FileNotFoundException {

        return new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        new FileInputStream(
                                                new File(pathname)))));
    }

    public boolean isLinesNumberCorrect(BufferedReader bufferedReader, int correctLinesNumber) {

        String line;
        int linesCounter = 0;

        try {
            while ((line = bufferedReader.readLine()) != null
                    && !line.isEmpty()
                    && linesCounter <= correctLinesNumber) {

                linesCounter++;
            }
            return linesCounter == correctLinesNumber;
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            LOGGER.log(Level.WARNING, "File Not Found Exception");
            return false;
        } catch (IOException e) {
//            e.printStackTrace();
            LOGGER.log(Level.WARNING, "IO Exception");
            return false;
        }
    }

    public void processCSV(String pathname) throws IOException {

        BufferedReader bufferedReader = createBufferedReader(pathname);
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.parse(bufferedReader);
    }

    public void processXML(String pathname) throws IOException, SAXException, ParserConfigurationException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler();
        saxParser.parse(new File(pathname), xmlHandler);
    }
}
