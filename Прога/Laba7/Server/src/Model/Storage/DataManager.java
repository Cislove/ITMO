package Model.Storage;

import Model.CommandHandler.Commands.Pair;
import Model.IODriver.IOHandler;
import Model.IODriver.IOHandlerWithDatabase;
import Model.IODriver.SQLParser.Parser;
import Model.Storage.ObjectDescription.MetaData;
import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataManager{
    Storage storage;
    ISQLManager sqlManager;
    Parser parser;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    //HashMap<Integer, User> owner = new HashMap<>();
    public DataManager(IOHandlerWithDatabase ioHandler) throws SQLException {
        storage = new Storage();
        sqlManager = new SQLManager(ioHandler);
        parser = new Parser();
        caching();
    }

    public void addGroup(StudyGroup group, User user) throws StorageException {
        lock.writeLock().lock();
            int id = sqlManager.addGroup(group, user);
            group.setId((long) id);
            storage.addElement(new StudyGroupWithUser(user.getLogin(), group));
        lock.writeLock().unlock();
        //owner.put(group.getId().intValue(), user);
    }

    public void updGroup(int id, StudyGroup group, User user) throws StorageException, SQLException {
        lock.writeLock().lock();
            StudyGroupWithUser el = storage.getElementById(id);
            if (el == null) {
                throw new SQLException("1");
            }
            if (!Objects.equals(el.owner, user.getLogin())) {
                throw new StorageException("У вас нет прав на изменение данного элемента\n");
            }
            sqlManager.updateGroup(id, group);
            storage.updElement(storage.getIdElementById(id), new StudyGroupWithUser(user.getLogin(), group));
        lock.writeLock().unlock();
    }

    public void rmGroup(int id, User user) throws StorageException, SQLException {
        lock.writeLock().lock();
        StudyGroupWithUser el = storage.getElementById(id);
        if(el == null){
            throw new SQLException("Такого элемента нет\n");
        }
        if(!Objects.equals(el.owner, user.getLogin())){
            //throw new RuntimeException("Че бля");
            throw new StorageException("У вас нет прав на удаление данного элемента\n");
        }
        //System.out.println("rmGroup 1");
        sqlManager.removeGroup(id);
        storage.delElement(storage.getIdElementById(id));
        lock.writeLock().unlock();
    }

    public StudyGroupWithUser getGroup(int id) throws StorageException, SQLException {
        lock.readLock().lock();
        StudyGroupWithUser el = storage.getElementById(id);
        lock.readLock().unlock();
        return el;
    }

    public void registerUser(User user) throws StorageException {
        lock.writeLock().lock();
        sqlManager.addUser(user);
        lock.writeLock().unlock();
    }

    public boolean verificationUser(User user) throws StorageException {
        lock.readLock().lock();
        boolean result = sqlManager.authorization(user);
        lock.readLock().unlock();
        return result;
    }

    public void checkUser(User user) throws StorageException {
        sqlManager.checkUser(user);
    }

    public LinkedList<StudyGroupWithUser> getCollection(){
        lock.readLock().lock();
        LinkedList<StudyGroupWithUser> result = storage.getAllElements();
        lock.readLock().unlock();
        return result;
    }

    public baseMetaData getmData(){
        lock.readLock().lock();
        baseMetaData result = storage.getmData();
        lock.readLock().unlock();
        return result;
    }

    private void caching() throws SQLException {
        try {
            ResultSet res = sqlManager.getCollection();
            while (res.next() && res.getInt("id") != 0) {
                //System.out.println("aaaaaaa");
                StudyGroup el = parser.parseStudyGroup(res);
                //System.out.println(el);
                storage.addElement(new StudyGroupWithUser(res.getString("login"), el));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
