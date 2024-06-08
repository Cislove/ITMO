package Model.IODriver.SQLogic;

import java.sql.SQLException;

public interface DataBaseConnectionFactory {
    DataBaseConnection initializeConnection(String url, String user, String password) throws SQLException;
}
