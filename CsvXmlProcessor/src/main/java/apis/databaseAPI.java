package apis;

import database.DBConnService;
import database.DBAccessService;
import processing.FileService;
import processing.DataFileType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jakub Filipiak on 20.05.2019.
 */
public class databaseAPI {

    private static final Logger LOGGER = Logger.getLogger(databaseAPI.class.getName());

    private FileService fileService = new FileService();
    private DBAccessService dbAccessService = new DBAccessService();

    public boolean isFileCorrect(String pathname) {

        try {
            if (fileService.fileExists(pathname) && fileService.isFileTypeCorrect(pathname, DataFileType.TXT)) {
                BufferedReader bufferedReader = fileService.createBufferedReader(pathname);
                int correctLinesNumber = 3;
                return fileService.isLinesNumberCorrect(bufferedReader,
                        correctLinesNumber);
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            LOGGER.log(Level.WARNING, "File Not Found Exception");
            return false;
        }
    }

    public void setDBAccessDataFromFile(String pathname) {

        try {
            BufferedReader bufferedReader = fileService.createBufferedReader(pathname);
            Map<String, String> accessData =
                    dbAccessService.getAccessDataFromBufferedReader(bufferedReader);
            dbAccessService.setDBAccessData(accessData);
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            LOGGER.log(Level.WARNING, "File Not Found Exception");
        } catch (IOException e) {
//            e.printStackTrace();
            LOGGER.log(Level.WARNING, "IO Exception");
        }
    }

    public boolean isDBReady() {

        if (dbAccessService.isDBAccessDataSetCorrectly()) {
            DBConnService dbConnService = new DBConnService();
            dbConnService.connect();
            if (dbConnService.isConnected()) {
                try {
                    dbConnService.createTables();
                    return true;
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "Error when creating tables!" + e.getMessage());
                    dbConnService.disconnect();
                }
            }
        }
        return false;
    }
}
