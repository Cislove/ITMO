package Model.Validation;

public class ParseException extends Exception{
    String message;
    public ParseException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
