package Model.IODriver.XMLParser;

import Model.IODriver.XMLConverter.XMLCollection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Класс парсер XML файла в {@link XMLCollection}
 * @author Ильнар Рахимов
 */
public class Parser {
    XmlMapper mapper = new XmlMapper();
    public Parser(){
    }
    /**
     * Метод для парсинга xml файла в коллекцию
     * @param xml xml файл
     * @return спаршенную коллекцию
     */
    public XMLCollection parser(String xml){
        try {
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(xml, XMLCollection.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
