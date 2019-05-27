package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jakub Filipiak on 16.05.2019.
 */
public class DBAccessService {

    public Map<String, String> getAccessDataFromBufferedReader(BufferedReader bufferedReader) throws IOException {

        final int LINES_NUMBER = 3;
        final int USERNAME_LINE = 0;
        final int PASSWORD_LINE = 1;
        final int URL_LINE = 2;

        Map<String, String> accessData = new HashMap<>();

        for (int lineNumber = 0; lineNumber < LINES_NUMBER; lineNumber++) {

            String line = bufferedReader.readLine().trim();

            switch (lineNumber) {
                case USERNAME_LINE:
                    accessData.put("username", line);
                    break;
                case PASSWORD_LINE:
                    accessData.put("password", line);
                    break;
                case URL_LINE:
                    accessData.put("url", line);
                    break;
            }
        }
        return accessData;
    }

    public void setDBAccessData(Map<String, String> accessData) {

        DBAccessData dbAccessData = DBAccessData.INSTANCE;

        String username = accessData.get("username");
        String password = accessData.get("password");
        String url = accessData.get("url");

        dbAccessData.setUsername(username);
        dbAccessData.setPassword(password);
        dbAccessData.setUrl(url);
    }

    public boolean isDBAccessDataSetCorrectly() {

        DBAccessData dbAccessData = DBAccessData.INSTANCE;

        boolean usernameNotNull = dbAccessData.getUsername() != null;
        boolean passwordNotNull = dbAccessData.getPassword() != null;
        boolean urlNotNull = dbAccessData.getUrl() != null;

        return usernameNotNull && passwordNotNull && urlNotNull;
    }
}
