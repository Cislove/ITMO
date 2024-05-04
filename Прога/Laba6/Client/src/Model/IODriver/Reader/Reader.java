package Model.IODriver.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс для чтения файлов
 * @author Ильнар Рахимов
 */
public class Reader{
    public String ReadFromFile(String pathToFile) {
        StringBuilder file = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(pathToFile))){
            String line;
            while((line = reader.readLine()) != null){
                file.append(line).append("\n");
            }
        }
        catch (IOException | NullPointerException e){
            return null;
        }
        return file.toString();
    }
}
