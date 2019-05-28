import org.xml.sax.SAXException;
import apis.databaseAPI;
import apis.processingAPI;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jakub Filipiak on 15.05.2019.
 */
public class CsvXmlProcessorApp {

    private static final Logger LOGGER = Logger.getLogger(CsvXmlProcessorApp.class.getName());

    private static boolean test = true;

    private static final String RESOURCES_PATH =
            "D://IdeaProjects/trainingtasks/CsvXmlProcessor/src/main/resources/";

    private static final String DB_FILE_PATH = RESOURCES_PATH + "baza-dane.txt";
    private static final String CSV_FILE_PATH = RESOURCES_PATH + "dane-osoby.csv";
    private static final String XML_FILE_PATH = RESOURCES_PATH + "dane-osoby.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        databaseAPI databaseAPI = new databaseAPI();
        processingAPI processingAPI = new processingAPI();

        Scanner scanner = new Scanner(System.in);
        String dbFileUri;
        String dataFileUri;


        System.out.println("--- CSV & XML processor ---");
        System.out.println("---------------------------");

        // ---------- Preparing DB Config file path ----------
        // ---------- and checking its correctness ----------
        do {
            System.out.println("DB config file path:");
            if (test) {
                dbFileUri = DB_FILE_PATH;
            } else {
                dbFileUri = scanner.nextLine().trim();
            }
            System.out.println("Path: " + dbFileUri);

        } while (!databaseAPI.isFileCorrect(dbFileUri));

        // ---------- Setting DB Access Data ----------

        databaseAPI.setDBAccessDataFromFile(dbFileUri);

        // ---------- Preparing Data file path ----------

        if (databaseAPI.isDBReady()) {
            do {
                System.out.println("CSV/XML file path:");
                if (test) {
                    dataFileUri = CSV_FILE_PATH;
                } else {
                    dataFileUri = scanner.nextLine().trim();
                }
                System.out.println("Path: " + dataFileUri);
            } while (!processingAPI.isFileCorrect(dataFileUri));

            // ---------- Processing Data file ----------

            processingAPI.processFile(dataFileUri);
        } else {
            LOGGER.log(Level.WARNING, "ERROR: DBAccessData set incorrectly!");
        }
    }
}
