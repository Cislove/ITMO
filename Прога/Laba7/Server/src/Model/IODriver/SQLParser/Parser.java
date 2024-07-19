package Model.IODriver.SQLParser;

import Model.Storage.StorageObject.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Parser {
    public User parseUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setLogin(rs.getString("user"));
        user.setPassword(rs.getString("password"));
        return user;
    }
    public StudyGroup parseStudyGroup(ResultSet rs) throws SQLException {
        StudyGroup sg = new StudyGroup();
        Person person = null;
        sg.setId((long) rs.getInt("id"));
        sg.setName(rs.getString("name"));
        sg.setCoordinates(new Coordinates(rs.getFloat("x_cord"), rs.getFloat("y_cord")));
        sg.setCreationDate(LocalDate.parse(rs.getDate("creation_date").toString()));
        sg.setStudentsCount(rs.getLong("students_count"));
        if(rs.getString("form_of_education") != null)
            sg.setFormOfEducation(FormOfEducation.valueOf(rs.getString("form_of_education")));
        if(rs.getString("semester_enum") != null)
            sg.setSemesterEnum(Semester.valueOf(rs.getString("semester_enum")));
        if(rs.getInt("id_group_admin") != 0){
            person = new Person();
            person.setName(rs.getString("names"));
            person.setBirthday(LocalDate.parse(rs.getString("birthday")));
            person.setHeight((double) rs.getFloat("height"));
            person.setWeight((double) rs.getFloat("weight"));
            person.setPassportID(rs.getString("passport_id"));
        }
//        person.setName(rs.getString("person.name"));
//        person.setBirthday(LocalDate.parse(rs.getString("birthday")));
//        person.setHeight((double) rs.getFloat("height"));
//        person.setWeight((double) rs.getFloat("weight"));
//        person.setPassportID(rs.getString("passport_id"));
        sg.setGroupAdmin(person);
        return sg;
    }
}
