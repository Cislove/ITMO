package Model.CommandHandler;


import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Commands.ToClientCommands.ServerArgumentCommand;
import Model.CommandHandler.Commands.ToClientCommands.ServerCommand;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Logger.MyLogger.logger;

/**
 * Класс обработчик запросов пользователя. Считывает запросы и вызывает исполнение нужных команд
 * @author Ильнар Рахимов
 */
public class SwitcherServer {
    private final HashMap<String, ServerCommand> commandMap = new HashMap<>();
    private final ArrayList<String> lastCommands = new ArrayList<>();
    private int operationMode = 0;
    private final HashMap<String, ServerArgumentCommand> argumentCommandMap = new HashMap<>();

    public void CommandsRegister(String commandName, ServerCommand command) {
        commandMap.put(commandName, command);
    }
    public void ArgumentCommandsRegister(String commandName, ServerArgumentCommand command){
        argumentCommandMap.put(commandName, command);
    }
    public Response execute(Request request) {
        String command = request.command;
        logger.info("Вызов команды: " + command);
        List<Object> arguments = request.args;
        if(command.equals("") && arguments == null){
            logger.info("Подключение нового клиента");
            return new Response("");
        }
        if(operationMode == 0){
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
                if(commandMap.get(command) != null){
                    lastCommands.add(command);
                    return executor(commandMap.get(command));
                }
                else{
                    return new Response("Такой команды не существует\n");
                }
            }
        }
        else{
            return executor(argumentCommandMap.get(lastCommands.get(lastCommands.size() - 1)), arguments);
        }
    }
    private Response executor(ServerCommand command){
        Pair<Integer, Response> res = command.execute();
        return res.getRight();
    }
    private Response executor(ServerArgumentCommand command, List<Object> arguments){
        Pair<Integer, Response> res = command.execute(arguments);
        return res.getRight();
    }

    public void setOperationMode(int operationMode) {
        if(operationMode == 1){
            argumentCommandMap.get(lastCommands.get(lastCommands.size() - 1)).update();
        }
        this.operationMode = operationMode;
        //argumentCommandMap.get(lastCommands.get(lastCommands.size() - 1)).update();
    }
}
