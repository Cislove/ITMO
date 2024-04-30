package Model.Storage.StorageObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация указывающая, что поле нужно вводить через составной ввод
 * @author Ильнар Рахимов
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface fieldWithCompoundInput {
}
