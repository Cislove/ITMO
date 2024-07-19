package Model.IODriver.SQLConverter;

import Model.Storage.StorageObject.Coordinates;
import Model.Storage.StorageObject.Person;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.User;

public class Converter {
    public String convertUser(User inst){
        StringBuilder strUser = new StringBuilder();
        strUser.append("(");
        strUser.append("'").append(inst.getLogin()).append("', ");
        strUser.append("'").append(inst.getPassword()).append("'");
        strUser.append(")");
        return strUser.toString();
    }
    public String convertStudyGroup(StudyGroup inst, int userId, Integer personId, int cord_id){
        StringBuilder strStudyGroup = new StringBuilder();
        strStudyGroup.append("(");
        strStudyGroup.append(userId).append(", ");
        strStudyGroup.append("'").append(inst.getName()).append("', ");
        strStudyGroup.append(cord_id).append(", ");
        strStudyGroup.append("'").append(inst.getCreationDate()).append("', ");
        strStudyGroup.append(inst.getStudentsCount()).append(", ");
        strStudyGroup.append("'").append(inst.getFormOfEducation()).append("', ");
        strStudyGroup.append("'").append(inst.getSemesterEnum()).append("', ");
        if(personId != null){
            strStudyGroup.append(personId).append(", ");
        }
        strStudyGroup.append(")");
        return strStudyGroup.toString();
    }
    public String convertCoordinates(Coordinates inst){
        StringBuilder strCord = new StringBuilder();
        strCord.append("(");
        strCord.append(inst.getXCord()).append(", ");
        strCord.append(inst.getYCord()).append(", ");
        strCord.append(")");
        return strCord.toString();
    }
    public String convertPerson(Person inst){
        StringBuilder strPerson = new StringBuilder();
        strPerson.append("(");
        strPerson.append("'").append(inst.getName()).append("', ");
        strPerson.append("'").append(inst.getBirthday()).append("', ");
        strPerson.append(inst.getHeight()).append(", ");
        strPerson.append(inst.getWeight()).append(", ");
        strPerson.append("'").append(inst.getPassportID()).append("', ");
        strPerson.append(")");
        return strPerson.toString();
    }
    public String convertUserUpdate(User inst){
        StringBuilder strUser = new StringBuilder();
        strUser.append("( SET");
        strUser.append("login = '").append(inst.getLogin()).append("', ");
        strUser.append("password = ").append(inst.getPassword()).append(", ");
        strUser.append(")");
        return strUser.toString();
    }
    public String convertStudyGroupUpdate(StudyGroup inst, Integer personId){
        StringBuilder strStudyGroup = new StringBuilder();
        strStudyGroup.append("( SET");
        strStudyGroup.append("Name = '").append(inst.getName()).append("', ");
        strStudyGroup.append("creation_date = '").append(inst.getCreationDate()).append("', ");
        strStudyGroup.append("students_count = ").append(inst.getStudentsCount()).append(", ");
        strStudyGroup.append("form_of_education = '").append(inst.getFormOfEducation()).append("', ");
        strStudyGroup.append("semester = '").append(inst.getSemesterEnum()).append("', ");
        if(personId != null){
            strStudyGroup.append("personId = ").append(personId).append(", ");
        }
        strStudyGroup.append(")");
        return strStudyGroup.toString();
    }
    public String convertCoordinatesUpdate(Coordinates inst){
        StringBuilder strCord = new StringBuilder();
        strCord.append("(SET ");
        strCord.append("x_cord = ").append(inst.getXCord()).append(", ");
        strCord.append("y_cord = ").append(inst.getYCord()).append(", ");
        strCord.append(")");
        return strCord.toString();
    }
    public String convertPersonUpdate(Person inst){
        StringBuilder strPerson = new StringBuilder();
        strPerson.append("(SET ");
        strPerson.append("name = '").append(inst.getName()).append("', ");
        strPerson.append("birthday = '").append(inst.getBirthday()).append("', ");
        strPerson.append("height = ").append(inst.getHeight()).append(", ");
        strPerson.append("weight = ").append(inst.getWeight()).append(", ");
        strPerson.append("passport_id'").append(inst.getPassportID()).append("', ");
        strPerson.append(")");
        return strPerson.toString();
    }
}
