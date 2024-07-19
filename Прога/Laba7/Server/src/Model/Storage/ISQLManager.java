package Model.Storage;

import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public interface ISQLManager {
    int addGroup(StudyGroup group, User user) throws StorageException;
    void removeGroup(int id) throws SQLException, StorageException;
    void updateGroup(int id, StudyGroup group) throws SQLException;
    //String getOwnerGroup(int id) throws StorageException;
    ResultSet getCollection() throws SQLException;
    void clearCollection() throws StorageException;
    void addUser(User user) throws StorageException;
    void removeUser(User user) throws StorageException;
    void updateUser(int id, User user) throws StorageException;
    boolean checkUser(User user);
    boolean authorization(User user);
    baseMetaData getmData();
}
