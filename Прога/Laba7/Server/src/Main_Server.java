import Controller.Executor;
import Controller.ExecutorMT;

import java.io.IOException;

public class Main_Server {
    public static void main(String[] args) {
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        try {
//            Executor ex = new Executor();
//            ex.execute();
            ExecutorMT executor = new ExecutorMT();
            executor.execute();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}