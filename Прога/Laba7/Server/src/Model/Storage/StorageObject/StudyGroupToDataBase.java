package Model.Storage.StorageObject;

public class StudyGroupToDataBase extends StudyGroup{
    int userId;
    public StudyGroupToDataBase(){
        super();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
