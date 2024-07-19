package Model.CommandHandler;


import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Commands.ToClientCommands.ServerArgumentCommand;
import Model.CommandHandler.Commands.ToClientCommands.ServerCommand;
import Model.PasswordEncryptor;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;
import Model.Storage.DataManager;
import Model.Storage.StorageObject.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Logger.MyLogger.logger;

/**
 * Класс обработчик запросов пользователя. Считывает запросы и вызывает исполнение нужных команд
 * @author Ильнар Рахимов
 */
public class SwitcherServer {
    DataManager dataManager;
    private final HashMap<String, ServerCommand> commandMap = new HashMap<>();
    private final ArrayList<String> lastCommands = new ArrayList<>();
    private int operationMode = 0;
    private final HashMap<String, ServerArgumentCommand> argumentCommandMap = new HashMap<>();

    public SwitcherServer(DataManager dataManager) {
        this.dataManager = dataManager;
    }

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
        String login = (String) arguments.get(0);
        String password = (String) arguments.get(1);
        arguments.remove(0);
        arguments.remove(0);
        //System.out.println("swicher\n");
        if(operationMode == 0){
            if(argumentCommandMap.get(command) != null){
                lastCommands.add(command);
                if(arguments.isEmpty()){
                    //System.out.println("swicher1\n");
                    return executor(argumentCommandMap.get(command), new User(login, PasswordEncryptor.encryptPassword(password)), null);
                }
                else{
                    //System.out.println("swicher2\n");
                   // System.out.println(request.command);
                    //System.out.println(request.args);
                    return executor(argumentCommandMap.get(command),new User(login, PasswordEncryptor.encryptPassword(password)), arguments);
                }
            }
            else{
                if(commandMap.get(command) != null){
                    //System.out.println("swicher3\n");
                    lastCommands.add(command);
                    return executor(commandMap.get(command), new User(login, PasswordEncryptor.encryptPassword(password)));
                }
                else{
                    return new Response("Такой команды не существует\n");
                }
            }
        }
        else{
            return executor(argumentCommandMap.get(lastCommands.get(lastCommands.size() - 1)), new User(login, PasswordEncryptor.encryptPassword(password)), arguments);
        }
    }
    private Response executor(ServerCommand command, User user){
        Pair<Integer, Response> res = command.execute(user);
        return res.getRight();
    }
    private Response executor(ServerArgumentCommand command, User user, List<Object> arguments){
        Pair<Integer, Response> res = command.execute(user, arguments);
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
