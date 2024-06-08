package Model.IODriver.SQLogic;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBaseConnection {
    ResultSet queryToReceive(String sql) throws SQLException;
    int queryToUpdateOrInsert(String sql) throws SQLException;
    void finishConnection() throws SQLException;
}
