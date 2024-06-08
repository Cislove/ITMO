package Model.IODriver.Writter;

import java.io.FileWriter;
import java.io.IOException;

import static Logger.MyLogger.logger;

/**
 * Класс для записи файлов
 * @author Ильнар Рахимов
 */
public class Writter{
    public Writter(){
    }
    public int writeToFile(String pathToFile, String file){
        try (FileWriter fw = new FileWriter(pathToFile)) {
            fw.write(file);
        }
        catch (IOException e) {
            logger.warning("Ошибка записи в файл: " + e.getMessage());
            return -1;
        }
        return 0;
    }
}
