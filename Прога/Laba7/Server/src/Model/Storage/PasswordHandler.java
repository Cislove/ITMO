package Model.Storage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHandler {
    public static String hashPassword(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return byteToString(hash);
        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    public static String byteToString(byte[] hash) {
        StringBuffer str = new StringBuffer();
        for (byte b : hash) {
            str.append(String.format("%02x", b));
        }
        return str.toString();
    }
}
