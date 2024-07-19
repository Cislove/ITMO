package Model.Storage;

import Model.IODriver.IOHandlerWithDatabase;
import Model.IODriver.SQLConverter.Converter;
import Model.IODriver.SQLParser.Parser;
import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.*;

import java.sql.*;
import java.util.LinkedList;

public class SQLManager implements ISQLManager {
    private IOHandlerWithDatabase ioHandler;
    private Converter converter;
    private StorageWithDataBase storage;
    public SQLManager(IOHandlerWithDatabase ioHandler){
        this.ioHandler = ioHandler;
        converter = new Converter();
        storage = new StorageWithDataBase();
    }

    @Override
    public int addGroup(StudyGroup group, User user) throws StorageException {
        try{
            PreparedStatement ps = ioHandler.getStatement("SELECT id FROM users WHERE login=?");
            ps.setString(1, user.getLogin());
            ResultSet response = ioHandler.getSqlResponse(ps);
            response.next();
            ps = ioHandler.getStatement(
                    "INSERT INTO study_group " +
                            "(id_user, name, id_coordinates, creation_date, students_count, form_of_education, semester_enum, id_group_admin) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
            ps.setInt(1, response.getInt("id"));
            ps.setString(2, group.getName());
            ps.setInt(3, addCord(group.getCoordinates()));
            ps.setDate(4, Date.valueOf(group.getCreationDate()));
            ps.setInt(5, group.getStudentsCount().intValue());
            ps.setObject(6, group.getFormOfEducation(), Types.OTHER);
            ps.setObject(7, group.getSemesterEnum(), Types.OTHER);
            ps.setInt(8, addPerson(group.getGroupAdmin()));
            ResultSet res = ioHandler.getSqlResponse(ps);
            res.next();
            if(res.getInt("id") == 0){
                throw new SQLException();
            }
            return res.getInt("id");
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new StorageException("Сохранение в данный момент недоступно \n");
        }
    }

    @Override
    public void removeGroup(int id) throws StorageException {
        try {
            PreparedStatement ps = ioHandler.getStatement("SELECT id_coordinates, id_group_admin FROM study_group WHERE id = " + id);
            ResultSet info = ioHandler.getSqlResponse(ps);
            info.next();
            //System.out.println("removeGroup");
            ps = ioHandler.getStatement("DELETE FROM study_group WHERE id = " + id);
            int res = ioHandler.executeSqlRequest(ps);
            if (res == 0) {
                throw new StorageException("Ошибка удаления\n");
            }
            //System.out.println("removeGroup");
            rmCord(info.getInt("id_coordinates"));
            rmPerson(info.getInt("id_group_admin"));
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new StorageException("Удаление в данный момент недоступно\n");
        }
        //storage.delElement(id);
    }

    @Override
    public void updateGroup(int id, StudyGroup group) throws SQLException {
        try{
            System.out.println("updateGroup");
            ResultSet info = ioHandler.getSqlResponse(ioHandler.getStatement("SELECT id_coordinates, id_group_admin FROM study_group WHERE id = " + id));
            info.next();
            if(info.wasNull()){
                throw new SQLException();
            }
            //System.out.println(info.getInt("id_coordinates"));
            updCord(info.getInt("id_coordinates"), group.getCoordinates());
            int id_person = info.getInt("id_group_admin");
            if(id_person == 0 && group.getGroupAdmin() != null){
                id_person = addPerson(group.getGroupAdmin());
            }
            else if(group.getGroupAdmin() != null){
                updPerson(id_person, group.getGroupAdmin());
            }
            else{
                rmPerson(id_person);
                id_person = 0;
            }
            //System.out.println(id_person);
            PreparedStatement ps = ioHandler.getStatement("UPDATE study_group SET " +
                    "id_user=?, name=?, creation_date=?, students_count=?, form_of_education=?, semester_enum=?, id_group_admin=?  WHERE id=" + id);
            ps.setInt(1, id);
            ps.setString(2, group.getName());
            ps.setDate(3, Date.valueOf(group.getCreationDate()));
            ps.setInt(4, group.getStudentsCount().intValue());
            ps.setObject(5, group.getFormOfEducation(), Types.OTHER);
            ps.setObject(6, group.getSemesterEnum(), Types.OTHER);
            ps.setInt(7, id_person);
            //ps.setString(1, converter.convertStudyGroupUpdate(group, id_person));
            int res = ioHandler.executeSqlRequest(ps);
            //System.out.println("updateGroup3");
            if(res == 0){
                throw new SQLException();
            }
            //storage.updElement(id, group);
        }
        catch (SQLException e){
            throw new SQLException(e);
        }
    }

//    @Override
//    public String getOwnerGroup(int id) throws StorageException {
//
//    }

    @Override
    public ResultSet getCollection() throws SQLException {
        try {
            ResultSet res = ioHandler.getSqlResponse(ioHandler.getStatement("SELECT * FROM study_group " +
                    "FULL OUTER JOIN users ON study_group.id_user = users.id " +
                    "FULL OUTER JOIN coordinates ON study_group.id_coordinates=coordinates.id " +
                    "FULL OUTER JOIN person ON study_group.id_group_admin=person.id "));
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clearCollection() throws StorageException {
        try{
            ioHandler.executeSqlRequest(ioHandler.getStatement("DELETE FROM study_group"));
            ioHandler.executeSqlRequest(ioHandler.getStatement("DELETE FROM person"));
            ioHandler.executeSqlRequest(ioHandler.getStatement("DELETE FROM coordinates"));
        }
        catch(SQLException e){
            throw new StorageException(e.getMessage());
        }
    }

    @Override
    public void addUser(User user) throws StorageException {
        try{
            PreparedStatement ps = ioHandler.getStatement("INSERT INTO users (login, password) VALUES (?, ?)");
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            int res = ioHandler.executeSqlRequest(ps);
            //System.out.println(res);
            if (res == 0){
                System.out.println("ошибка");
                throw new StorageException();
            }
        }
        catch (SQLException e){
            //System.out.println(e.getMessage());
            throw new StorageException("Такой логин уже существует\n");
        }
    }

    @Override
    public void removeUser(User user) throws StorageException {
        try{
            int res = ioHandler.executeSqlRequest(ioHandler.getStatement("DELETE FROM users WHERE login='" + user.getLogin() + "'"));
            if(res == 0){
                throw new SQLException();
            }
        }
        catch (SQLException e){
            throw new StorageException("Такого пользователя не существует");
        }
    }

    @Override
    public void updateUser(int id, User user) throws StorageException {
        try{
            PreparedStatement ps = ioHandler.getStatement("UPDATE users SET login=?, password=? WHERE login='" + user.getLogin() + "'");
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            int res = ioHandler.executeSqlRequest(ps);
            if(res == 0){
                throw new SQLException();
            }
        }
        catch (SQLException e){
            throw new StorageException("Неверные данные\n");
        }
    }

    @Override
    public boolean checkUser(User user) {
        try{
            PreparedStatement ps = ioHandler.getStatement("SELECT count(id) FROM users WHERE login=?");
            ps.setString(1, user.getLogin());
            ResultSet res = ioHandler.getSqlResponse(ps);
            res.next();
            if (res.getLong("count") == 0){
                throw new SQLException();
            }
        }
        catch (SQLException e){
            return false;
        }
        return true;
    }

    public boolean authorization(User user){
        try{
            PreparedStatement ps = ioHandler.getStatement("SELECT password FROM users WHERE login=?");
            ps.setString(1, user.getLogin());
            ResultSet res = ioHandler.getSqlResponse(ps);
            res.next();
            return res.getString("password").equals(user.getPassword());
        }
        catch (SQLException e){
            return false;
        }
    }

    @Override
    public baseMetaData getmData() {
        return storage.getmData();
    }




    private int addCord(Coordinates cord) throws SQLException {
        PreparedStatement ps = ioHandler.getStatement("INSERT INTO coordinates (x_cord, y_cord) VALUES (?,?) RETURNING id");
        ps.setDouble(1, cord.getXCord());
        ps.setDouble(2, cord.getYCord());
        ResultSet res = ioHandler.getSqlResponse(ps);
        res.next();
        return res.getInt("id");
    }
    private int addPerson(Person person) throws SQLException{
        if(person == null){
            return 0;
        }
        PreparedStatement ps = ioHandler.getStatement("INSERT INTO person (names, birthday, height, weight, passport_id) VALUES (?, ?, ?, ?, ?) RETURNING id");
        ps.setString(1, person.getName());
        ps.setDate(2, Date.valueOf(person.getBirthday()));
        ps.setDouble(3, person.getHeight());
        ps.setDouble(4, person.getWeight());
        ps.setString(5, person.getPassportID());
        ResultSet res = ioHandler.getSqlResponse(ps);
        res.next();
        return res.getInt("id");
    }
    private void updCord(int id, Coordinates cord) throws SQLException{
        PreparedStatement ps = ioHandler.getStatement("UPDATE coordinates SET x_cord=?, y_cord=? WHERE id=" + id);
        ps.setFloat(1, cord.getXCord());
        ps.setFloat(2, cord.getYCord());
        int res = ioHandler.executeSqlRequest(ps);
        if(res == 0){
            throw new SQLException();
        }
    }
    private void updPerson(int id, Person person) throws SQLException{
        PreparedStatement ps = ioHandler.getStatement("UPDATE person SET names=?, birthday=?, height=?, weight=?, passport_id=? WHERE id=" + id);
        ps.setString(1, person.getName());
        ps.setDate(2, Date.valueOf(person.getBirthday()));
        ps.setDouble(3, person.getHeight());
        ps.setDouble(4, person.getWeight());
        ps.setString(5, person.getPassportID());
        int res = ioHandler.executeSqlRequest(ps);
        if(res == 0){
            throw new SQLException();
        }
    }
    private void rmCord(int id) throws SQLException {
        int res = ioHandler.executeSqlRequest(ioHandler.getStatement("DELETE FROM coordinates WHERE id=" + id));
        if(res == 0){
            throw new SQLException();
        }
    }
    private void rmPerson(int id) throws SQLException {
        if(id == 0) return;
        int res = ioHandler.executeSqlRequest(ioHandler.getStatement("DELETE FROM person WHERE id=" + id));
        if(res == 0){
            throw new SQLException();
        }
    }
}
