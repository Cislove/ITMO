package Model.IODriver.SQLogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBaseConnection {
    ResultSet queryToReceive(PreparedStatement ps) throws SQLException;
    int queryToUpdateOrInsert(PreparedStatement ps) throws SQLException;
    void finishConnection() throws SQLException;
    PreparedStatement getPreparedStatement(String sql) throws SQLException;
}
