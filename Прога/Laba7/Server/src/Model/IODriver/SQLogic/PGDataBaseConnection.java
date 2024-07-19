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
    public ResultSet queryToReceive(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    @Override
    public int queryToUpdateOrInsert(PreparedStatement ps) throws SQLException {
        int res = ps.executeUpdate();
        System.out.println(res);
        return res;
    }

    @Override
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
}
