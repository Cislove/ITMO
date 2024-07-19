package Model.IODriver;

import Model.IODriver.Reader.Reader;
import Model.IODriver.SQLogic.DataBaseConnection;
import Model.IODriver.SQLogic.DataBaseConnectionFactory;
import Model.IODriver.SQLogic.PGDataBaseConnectionFactory;
import Model.IODriver.Writter.Writter;
import Model.Storage.StorageObject.StudyGroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IOHandlerWithDatabase extends IOHandler{
    DataBaseConnection dbConn;
    DataBaseConnectionFactory dbConnFactory;
    public IOHandlerWithDatabase(Reader reader, Writter writter, String url, String user, String password) throws SQLException {
        super(reader, writter);
        dbConnFactory = new PGDataBaseConnectionFactory();
        dbConn = dbConnFactory.initializeConnection(url, user, password);
    }
    public ResultSet getSqlResponse(PreparedStatement ps) throws SQLException {
        return dbConn.queryToReceive(ps);
    }
    public int executeSqlRequest(PreparedStatement ps) throws SQLException {
        return dbConn.queryToUpdateOrInsert(ps);
    }
    public void finish() throws SQLException {
        dbConn.finishConnection();
    }
    public PreparedStatement getStatement(String sql) throws SQLException {
        return dbConn.getPreparedStatement(sql);
    }
}
