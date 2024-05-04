package Model.Storage.StorageObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация указывающая, что поле может иметь значение null
 * @author Ильнар Рахимов
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface mayBeNull {

}
