package Model.IODriver;

import Client.Model.IODriver.Reader.Reader;
import Client.Model.IODriver.Writter.Writter;
import Client.Model.IODriver.XMLConverter.Converter;
import Client.Model.IODriver.XMLConverter.XMLCollection;
import Client.Model.IODriver.XMLParser.Parser;

import java.io.IOException;

/**
 * Класс обработчик взаимодействий с файлами
 * @author Ильнар Рахимов
 */
public class IOHandler{
    private final Reader reader;
    private final Writter writter;
    private final Converter converter = new Converter();
    private final Parser parser = new Parser();
    public IOHandler(Reader reader, Writter writter){
        this.reader = reader;
        this.writter = writter;
    }
    /**
     * Метод записывающий коллекцию в файл
     * @param collection коллекция
     * @param name имя файла
     * @return строку - итог выполнения метода
     */
    public String writeListToFile(XMLCollection collection, String name) {
        String response = "Коллекция успешно записана в файл\n";
        String xml = converter.Serialize(collection);
        if(writter.writeToFile(name, xml) == -1){
            response = "Ошибка записи коллекции, проверьте права доступа к файлу\n";
        }
        return response;
    }

    public int writeToFile(String File) {
        return 0;
    }

    /**
     * Метод читающий коллекцию из файла
     * @param name имя файла
     * @return прочитанную коллекцию
     */
    public XMLCollection readListFromFile(String name) throws IOException {
        String xml = reader.ReadFromFile(name);
        if(xml == null){
            throw new IOException("Ошибка чтения из файла, проверьте путь до него и правда доступа\n");
        }
        else {
            XMLCollection collection = parser.parser(xml);
            if (collection == null) {
                throw new IOException("Файл не соответствует требуемому формату\n");
            }
            return collection;
        }
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
