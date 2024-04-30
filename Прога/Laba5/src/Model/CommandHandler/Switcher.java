package Model.CommandHandler;


import Model.CommandHandler.Commands.ArgumentCommand;
import Model.CommandHandler.Commands.Command;
import Model.CommandHandler.Commands.Pair;
import Model.Storage.StorageObject.StudyGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс обработчик запросов пользователя. Считывает запросы и вызывает исполнение нужных команд
 * @author Ильнар Рахимов
 */
public class Switcher {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    private final ArrayList<String> lastCommands = new ArrayList<>();
    private int operationMode = 0;
    private final HashMap<String, ArgumentCommand> argumentCommandMap = new HashMap<>();

    public void CommandsRegister(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    public void ArgumentCommandsRegister(String commandName, ArgumentCommand command){
        argumentCommandMap.put(commandName, command);
    }
    public Pair<Integer, String> execute(String request) {
        if(operationMode == 0){
            String command;
            String arguments;
            if(request.contains(" ")) {
                command = request.substring(0, request.indexOf(" "));
                arguments = request.substring(request.indexOf(" ") + 1);
            }
            else{
                command = request;
                arguments = "";
            }
            if(argumentCommandMap.get(command) != null){
                lastCommands.add(command);
                if(arguments.isEmpty()){
                    return executor(argumentCommandMap.get(command), null);
                }
                else{
                    return executor(argumentCommandMap.get(command), arguments);
                }
            }
            else{
                if(commandMap.get(command) != null && !(arguments.equals(" "))){
                    lastCommands.add(command);
                    return executor(commandMap.get(command));
                }
                else{
                    return new Pair<>(2, "К сожалению такой команды не существует(\n");
                }
            }
        }
        else{
            return executor(argumentCommandMap.get(lastCommands.get(lastCommands.size() - 1)), request);
        }
    }
    private Pair<Integer, String> executor(Command command){
        Pair<Integer, String> res = command.execute();
        switch (res.getLeft()){
            case 2, 1 -> operationMode = 1;
            default -> operationMode = 0;
        }
        return res;
    }
    private Pair<Integer, String> executor(ArgumentCommand command, String arguments){
        Pair<Integer, String> res = command.execute(arguments);
        switch (res.getLeft()){
            case 2, 1 -> operationMode = 1;
            default -> operationMode = 0;
        }
        return res;
    }

    public void setOperationMode(int operationMode) {
        if(operationMode == 1){
            argumentCommandMap.get(lastCommands.get(lastCommands.size() - 1)).update();
        }
        this.operationMode = operationMode;
        //argumentCommandMap.get(lastCommands.get(lastCommands.size() - 1)).update();
    }
}
