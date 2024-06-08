package Model.IODriver.SQLogic;

import java.sql.SQLException;

public class PGDataBaseConnectionFactory implements DataBaseConnectionFactory {
    @Override
    public DataBaseConnection initializeConnection(String url, String user, String password) throws SQLException {
        return new PGDataBaseConnection(url, user, password);
    }
}
