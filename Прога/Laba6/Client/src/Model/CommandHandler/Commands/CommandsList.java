package Model.CommandHandler.Commands;

import java.util.ArrayList;

/**
 * Класс список всех команд и их описаний. Нужен для работы {@link HelpCommand}
 * @author Ильнар Рахимов
 */
public class CommandsList {
    private final ArrayList<Pair<String, String>> list;
    public CommandsList(){
        list = new ArrayList<>();
    }

    public ArrayList<Pair<String, String>> getList() {
        return list;
    }

    public void register(String command, String description){
        list.add(new Pair<>(command, description));
    }
}
