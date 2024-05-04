package Model.Storage.StorageObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация указывающая, что поле закрыто для ввода(его значение генерируется программой)
 * @author Ильнар Рахимов
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface closedField {
}
