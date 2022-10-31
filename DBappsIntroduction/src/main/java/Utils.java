

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

enum Utils {
    ;
    static final String COLUMN_LABEL_NAME = "name";
    static final String COLUMN_MINIONS_COUNT = "minions_count";
    static final String COLUMN_LABEL_AGE = "age";
    static final String COLUMN_LABEL_ID = "id";
    static Connection getSQLConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", ".Gledka123");

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
    }
}
