package Model.Storage;

import java.util.HashMap;

public class PassportIdStorage {
    private HashMap<String, Boolean> passportIds;
    public PassportIdStorage() {
        passportIds = new HashMap<>();
    }
    public void addPassportId(String passportId) {
        passportIds.put(passportId, true);
    }
    public void removePassportId(String passportId) {
        passportIds.remove(passportId);
    }
    public boolean checkPassportId(String passportId) {
        return !passportIds.get(passportId);
    }
    public void clearPassportIds() {
        passportIds.clear();
    }
    public void updatePassportId(String passportId, boolean flag) {
        passportIds.replace(passportId, flag);
    }
}
