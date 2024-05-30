package Model.IODriver.XMLConverter;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

import static Logger.MyLogger.logger;

/**
 * Класс конвертирующий {@link XMLCollection} в XML файл
 * @author Ильнар Рахимов
 */
public class Converter {
    XmlMapper mapper = new XmlMapper();

    /**
     * Метод для конвертации коллекции в xml формат
     * @param collection коллекция
     * @return строку - конвертированную в xml формат коллекцию
     */
    public String Serialize(XMLCollection collection){
        try {
            return mapper.writeValueAsString(collection);
        } catch (IOException e) {
            logger.warning("Ошибка сериализации: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
