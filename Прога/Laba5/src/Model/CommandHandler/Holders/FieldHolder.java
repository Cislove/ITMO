package Model.CommandHandler.Holders;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.StorageObject.*;
import Model.Validation.Parser;
import Model.Validation.ValidateException;
import Model.Validation.Validator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Класс заполнитель открытых полей {@link StudyGroup} и других вложенных классов. Реализует заполнение этих полей данными пользователя
 * @author Ильнар Рахимов
 */
public class FieldHolder {
    private StudyGroup cash;
    private Stack <Object> stackCash;
    private StudyGroup readyEl;
    private final Validator validator;
    private final Parser parser;
    private Stack<Field> fields;
    private Stack <Field> lastFields;
    private Class lastClass = null;
    private boolean nullFieldFlag;
    public FieldHolder(Validator validator, Parser parser){
        cash = new StudyGroup();
        stackCash = new Stack<>();
        stackCash.push(cash);
        this.validator = validator;
        this.parser = parser;
        readyEl = null;
        fields = new Stack<>();
        lastFields = new Stack<>();
        Collections.addAll(fields, reverse(cash.getClass().getDeclaredFields()));
        checkField();
    }
    private Field[] reverse(Field[] array) {
        Field[] newArray = new Field[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[array.length - 1 - i] = array[i];
        }
        return newArray;
    }

    public StudyGroup getReadyEl() {
        return readyEl;
    }

    protected void finish(){
        nullFieldFlag = false;
        readyEl = cash;
        cash = new StudyGroup();
        fields = new Stack<>();
        lastFields = new Stack<>();
        stackCash = new Stack<>();
        stackCash.push(cash);
        Collections.addAll(fields, reverse(cash.getClass().getDeclaredFields()));
        checkField();
    }
    public Pair<Integer, String> execute(String arguments){
        String response;
        if(nullFieldFlag){
            processNullField(arguments);
        }
        else if(arguments != null){
            if(lastFields.peek().getDeclaringClass() != lastClass && (stackCash.size() != 1)){
                stackCash.pop();
            }
            try {
                writeField(parseField(arguments));
            } catch (ValidateException e) {
                return new Pair<>(2, e.getMessage());
            }
        }
        if(checkField()){
            if(fields.peek().getAnnotation(fieldWithCompoundInput.class) != null){
                if(fields.peek().getAnnotation(mayBeNull.class) != null){
                    nullFieldFlag = true;
                    response = "Вы хотите ввести поле " + fields.peek().getName() + "?(введите yes/no)";
                }
                else{
                    expandField();
                    return execute(null);
                }
            }
            else {
                response = "Введите поле " + fields.peek().getName();
                if (fields.peek().getAnnotation(enumType.class) != null) {
//                    if(fields.peek().getAnnotation(mayBeNull.class) != null && !nullFieldFlag){
//                        nullFieldFlag = true;
//                        response = "Вы хотите ввести поле " + fields.peek().getName() + "?(введите yes/no)";
//                    }
//                    else{
                        nullFieldFlag = false;
                        response += ("(" + Arrays.toString(fields.peek().getType().getEnumConstants()) + ")");
                    //}
                }
            }
            lastFields.push(fields.pop());
            return new Pair<>(1, response + "\n");
        }
        finish();
        return new Pair<>(0, null);
    }
    private boolean checkField(){
        while(!fields.empty()){
            if(fields.peek().getAnnotation(closedField.class) == null){
                return true;
            }
            fields.pop();
        }
        return false;
    }
    private void expandField(){
        try {
            Method method = cash.getClass().getDeclaredMethod(
                    "set" +
                            fields.peek().getName().substring(0, 1).toUpperCase() +
                            fields.peek().getName().substring(1), fields.peek().getType());
            method.invoke(cash, fields.peek().getType().getConstructor().newInstance());
            method = cash.getClass().getDeclaredMethod(
                    "get" +
                            fields.peek().getName().substring(0, 1).toUpperCase() +
                            fields.peek().getName().substring(1));
            stackCash.push(method.invoke(cash));
            fields.pop();
            lastClass = stackCash.peek().getClass();
            Collections.addAll(fields, reverse(stackCash.peek().getClass().getDeclaredFields()));
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    private void processNullField(String arguments){
        if(arguments.equals("yes")) {
            fields.push(lastFields.peek());
            nullFieldFlag = false;
            if(fields.peek().getAnnotation(enumType.class) == null)
                expandField();
            else
                nullFieldFlag = true;
        }
        else if(arguments.equals("no")){
            writeField(null);
            nullFieldFlag = false;
        }
        else{
            fields.push(lastFields.pop());
            nullFieldFlag = false;
        }
    }
    private Object parseField(String arguments) throws ValidateException{
        try {
            Method parseMethod = parser.getClass().getDeclaredMethod(
                    lastFields.peek().getDeclaringClass().getSimpleName() +
                            lastFields.peek().getName().substring(0, 1).toUpperCase() +
                            lastFields.peek().getName().substring(1) +
                            "Parser", String.class);
            Method validateMethod = validator.getClass().getDeclaredMethod(
                    lastFields.peek().getDeclaringClass().getSimpleName() +
                            lastFields.peek().getName().substring(0, 1).toUpperCase() +
                            lastFields.peek().getName().substring(1) +
                            "Validation", lastFields.peek().getType());
            Object field = parseMethod.invoke(parser, arguments);
            if(validateMethod.invoke(validator, field) == Boolean.TRUE){
                return field;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new ValidateException(e.getCause().getMessage());
        }
        return null;
    }
    private void writeField(Object arguments){
        try {
            Method setFieldMethod = stackCash.peek().getClass().getDeclaredMethod(
                    "set" +
                            lastFields.peek().getName().substring(0, 1).toUpperCase() +
                            lastFields.peek().getName().substring(1), lastFields.peek().getType());
            setFieldMethod.invoke(stackCash.peek(), arguments);
            lastFields.pop();
        } catch (NoSuchMethodException | IllegalAccessException  | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
