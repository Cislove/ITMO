import Controller.Executor;

import java.io.IOException;

public class Main_Server {
    public static void main(String[] args) {
        try {
            Executor ex = new Executor();
            ex.execute();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}