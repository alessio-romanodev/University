package esempio.is.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final static String DATABASE_PATH = "./mailingList"; // DA ADATTARE
    private final static String DATABASE_USERNAME = "sa";
    private final static String DATABASE_PASSWORD = "";
    
    public static java.sql.Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:" + DATABASE_PATH, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
