package Model.IODriver;

import Model.IODriver.Reader.Reader;
import Model.IODriver.Writter.Writter;

import java.io.IOException;

/**
 * Класс обработчик взаимодействий с файлами
 * @author Ильнар Рахимов
 */
public class IOHandler{
    private final Reader reader;
    private final Writter writter;
    public IOHandler(Reader reader, Writter writter){
        this.reader = reader;
        this.writter = writter;
    }

    public int writeToFile(String File) {
        return 0;
    }

    public String readFile(String name) throws IOException {
        String response;
        response = reader.ReadFromFile(name);
        //System.out.println(response);
        if(response == null){
            throw new IOException("Ошибка чтения из файла, проверьте путь до него и правда доступа\n");
        }
        return response;
    }

}
