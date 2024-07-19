package Model.Storage.StorageObject;

import java.io.Serial;
import java.io.Serializable;

public class StudyGroupWithUser implements Serializable {
    @closedField
    @Serial
    private static final long serialVersionUID = 4;
    public StudyGroup group;
    public String owner;

    public StudyGroupWithUser(){

    }
    public StudyGroupWithUser(String owner, StudyGroup group){
        this.owner = owner;
        this.group = group;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return group.toString() + "Владелец: " + owner + "\n";
    }

    public int compareTo(StudyGroupWithUser o) {
        return group.compareTo(o.group);
    }
}
