package Model;

import Model.CommandHandler.Commands.*;
import Model.CommandHandler.Switcher;
import Model.IODriver.IOHandler;
import Model.IODriver.Reader.Reader;
import Model.IODriver.Writter.Writter;
import Model.NetworkLogic.Handler;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageWithStreamAPI;
import Model.Validation.ClosedFieldValidator;
import Model.Validation.IDHandler;
import Model.Validation.ValidateException;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Класс описывающий связующий блок между моделью и контроллер.
 * Отвечает за передачу запросов на {@link Switcher} и запуск модели
 * @author Ильнар Рахимов
 */
public class EntryBlock implements IModel {
    Switcher commandHandler;
    /**
     * Метод реализующий исполнение класса
     * @return ответ модели
     */
    public Pair<Integer, String> execute(String request){
        Pair<Integer, String> response;
        response = commandHandler.execute(request);
        return response;
    }
    /**
     * Метод реализующий подготовку модели к работе
     * @return строку - итоги работы метода
     */
    public Pair<Integer, String> start(){
        String response = "Добро пожаловать в программу!!!\n";
        Handler server = new Handler();
        try {
            server.setServer("localhost", 6597);
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
        Reader r = new Reader();
        Writter w = new Writter();
        //IDHandler idHandler = new IDHandler();
        IOHandler ioHandler = new IOHandler(r, w);
        CommandsList list = new CommandsList();
        commandHandler = new Switcher();
        list.register("help", "вывести справку по доступным командам");
        list.register("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        list.register("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        list.register("add {element}", "добавить новый элемент в коллекцию");
        list.register("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        list.register("remove_by_id id", "удалить элемент из коллекции по его id");
        list.register("clear", "очистить коллекцию");
        //list.register("save", "сохранить коллекцию в файл");
        list.register("execute_script file_name", "считать и исполнить скрипт из указанного файла");
        list.register("exit", "завершить программу (без сохранения в файл)");
        list.register("remove_first", "удалить первый элемент из коллекции");
        list.register("head", "вывести первый элемент коллекции");
        list.register("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        list.register("min_by_group_admin", "вывести любой объект из коллекции, значение поля groupAdmin которого является минимальным");
        list.register("group_counting_by_id", "сгруппировать элементы коллекции по значению поля id, вывести количество элементов в каждой группе");
        list.register("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        Command helpCommand = new HelpCommand(list);
        Command infoCommand = new InfoCommand(server);
        Command showCommand = new ShowCommand(server);
        Command clearCommand = new ClearCommand(server);
        Command exitCommand = new ExitCommand();
        Command removeFirstCommand = new removeFirstCommand(server);
        Command headCommand = new HeadCommand(server);
        Command minByGroupAdmin = new MinByGroupAdmin(server);
        Command groupCountingById = new GroupCountingByidCommand(server);
        ArgumentCommand addCommand = new AddCommand(server);
        ArgumentCommand updCommand = new UpdateCommand(server);
        ArgumentCommand removeByIdCommand = new RemoveByIdCommand(server);
        ArgumentCommand executeScriptCommand = new ExecuteScriptCommand(ioHandler, commandHandler);
        ArgumentCommand addIfMin = new AddIfMinCommand(server);
        ArgumentCommand filterContainsName = new FilterContainsNameCommand(server);
        commandHandler.CommandsRegister("help", helpCommand);
        commandHandler.CommandsRegister("info", infoCommand);
        commandHandler.CommandsRegister("show", showCommand);
        commandHandler.CommandsRegister("clear", clearCommand);
        //commandHandler.CommandsRegister("save", saveCommand);
        commandHandler.CommandsRegister("exit", exitCommand);
        commandHandler.CommandsRegister("remove_first", removeFirstCommand);
        commandHandler.CommandsRegister("head", headCommand);
        commandHandler.CommandsRegister("min_by_group_admin", minByGroupAdmin);
        commandHandler.CommandsRegister("group_counting_by_id", groupCountingById);
        commandHandler.ArgumentCommandsRegister("add", addCommand);
        commandHandler.ArgumentCommandsRegister("update", updCommand);
        commandHandler.ArgumentCommandsRegister("remove_by_id", removeByIdCommand);
        commandHandler.ArgumentCommandsRegister("execute_script", executeScriptCommand);
        commandHandler.ArgumentCommandsRegister("add_if_min", addIfMin);
        commandHandler.ArgumentCommandsRegister("filter_contains_name", filterContainsName);
        //response += writeList(st, ioHandler, idHandler);
        return new Pair<>(0, response);
    }
}
