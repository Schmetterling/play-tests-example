package utils;

import org.apache.commons.io.FileUtils;
import play.Logger;
import play.api.Play;
import play.db.DB;
import java.io.*;
import java.sql.*;

public class TestUtils {
    private final static String DATA_SOURCE_NAME = "default";
    private final static String CREATE_USER_SQL = "/test/dumps/createUser.sql";
    private final static String INSERT_USER_SQL = "/test/dumps/insertUser.sql";

    public static void initDB() {
        try {
            executeSQL(DATA_SOURCE_NAME, CREATE_USER_SQL);
            executeSQL(DATA_SOURCE_NAME, INSERT_USER_SQL);
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
        }
    }

    private static void executeSQL(String dataSourceName, String fileName) throws SQLException {
        Connection connection = null;

        try {
            connection = DB.getDataSource(dataSourceName).getConnection();
            connection.createStatement().executeUpdate(readFileToString(fileName));
        } catch (IOException ioException) {
            Logger.error(ioException.getMessage());
        } finally {
            if (connection != null && !connection.isClosed())
                connection.close();
        }
    }

    private static String readFileToString(String fileName) throws IOException {
        File file = Play.current().getFile(fileName);
        byte[] buffer = FileUtils.readFileToByteArray(file);

        return new String(buffer);
    }
}