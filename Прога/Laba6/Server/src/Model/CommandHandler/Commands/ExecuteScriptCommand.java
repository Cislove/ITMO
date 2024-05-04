package Model.CommandHandler.Commands;

import Model.CommandHandler.Switcher;
import Model.IODriver.IOHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс реализации команды "execute_script"
 * @author Ильнар Рахимов
 */
public class ExecuteScriptCommand implements ArgumentCommand{
    private final IOHandler ioHandler;
    private final Switcher switcher;
    private final Set<String> runningScripts = new HashSet<>();
    private boolean flag = true;
    public ExecuteScriptCommand(IOHandler ioHandler, Switcher switcher){
        this.ioHandler = ioHandler;
        this.switcher = switcher;
    }

    @Override
    public void update() {

    }

    @Override
    public Pair<Integer, String> execute(String arguments){
        if(runningScripts.contains(arguments)){
            return new Pair<>(3, "!!!Ошибка выполнения скрипта: Скрипт остановлен на моменте вызова бесконечной рекурсии\n");
        }
        runningScripts.add(arguments);
        String[] script;
        try {
            script = ioHandler.readFile(arguments).split("\n");
        }
        catch (IOException e){
            return new Pair<>(0, e.getMessage());
        }
        StringBuilder response = new StringBuilder().append("Запуск скрипта ").append(arguments).append("\n");
        if(runningScripts.size() == 1 && flag){
            flag = false;
            response.insert(0, "!!!Внимание в скрипте присутствует рекурсивный вызов других скриптов\n");
        }
        for(int i = 0; i < script.length; i++){
            Pair<Integer, String> res = switcher.execute(script[i]);
            if(res.getLeft() == 2){
                response.append("!!!Внимание, ошибка в строке ").append(i).append(": ");
                switcher.setOperationMode(0);
            }
            response.append(res.getRight());
            if(res.getLeft() == -1){
                return new Pair<>(-1, response.toString());
            }
            if(res.getLeft() == 3){
                return new Pair<>(3, response.toString());
            }
        }
        runningScripts.remove(arguments);
        response.append("\nСкрипт ").append(arguments).append(" выполнен\n");
        return new Pair<>(0, response.toString());
    }
}
