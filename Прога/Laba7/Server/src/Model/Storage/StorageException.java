package Model.Storage;

public class StorageException extends Exception{
    public StorageException(){
        super("неизвестна");
    }
    public StorageException(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
