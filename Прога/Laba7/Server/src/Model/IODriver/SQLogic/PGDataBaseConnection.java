package Model.IODriver.SQLogic;

import java.sql.*;

public class PGDataBaseConnection implements DataBaseConnection {
    Connection conn;
    public PGDataBaseConnection(String url, String user, String password) throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    @Override
    public void finishConnection() throws SQLException {
        conn.close();
    }

    @Override
    public ResultSet queryToReceive(String sql) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    @Override
    public int queryToUpdateOrInsert(String sql) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeUpdate();
    }
}
