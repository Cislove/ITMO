import Controller.Executor;

import java.io.IOException;

public class Main {
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