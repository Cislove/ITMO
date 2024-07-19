package Model;

import static Logger.MyLogger.logger;

import Model.CommandHandler.Commands.OnServerCommands.*;
import Model.CommandHandler.Commands.OnServerCommands.CommandsList;
import Model.CommandHandler.Commands.OnServerCommands.MinByGroupAdmin;
import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Commands.ToClientCommands.*;
import Model.CommandHandler.SwitcherServer;
import Model.IODriver.IOHandler;
import Model.IODriver.IOHandlerWithDatabase;
import Model.IODriver.Reader.Reader;
import Model.IODriver.Writter.Writter;
import Model.IODriver.XMLConverter.XMLCollection;
import Model.Storage.*;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.ClosedFieldValidator;
import Model.Validation.IDHandler;
import Model.Validation.ValidateException;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;

public class EntryBlock implements IModel{
    //Switcher commandHandler;
    SwitcherServer commandServerHandler;
    ForkJoinPool fjp;
    /**
     * Метод реализующий исполнение класса
     * @return ответ модели
     */
    public Pair<Integer, String> execute(String request){
        Pair<Integer, String> response;
        //response = commandHandler.execute(request);
        //return response;
        return null;
    }
    @Override
    public Response executeServer(Request request){
        Response response;
        //System.out.println(request.command);
        //System.out.println(request.args);
        response = fjp.invoke(new Task(commandServerHandler, request));
        //response = commandServerHandler.execute(request);
        return response;
    }
    /**
     * Метод реализующий загрузку данных о группах из XML файла
     * @return строку - итоги работы метода
     */
//    private String writeList(IStorage st, IOHandler ioHandler, IDHandler idHandler){
//        int idEl = 1;
//        try {
//            ClosedFieldValidator validator = new ClosedFieldValidator(st, idHandler);
//            XMLCollection collection = ioHandler.readListFromFile("Main.xml");
//            for(StudyGroup el: collection.getCollection()){
//                validator.StudyGroupIDValidation(el.getId());
//                validator.StudyGroupNameValidation(el.getName());
//                validator.CoordinatesXCordValidation(el.getCoordinates().getXCord());
//                validator.CoordinatesYCordValidation(el.getCoordinates().getYCord());
//                validator.StudyGroupCreationDateValidation(el.getCreationDate());
//                validator.StudyGroupStudentsCountValidation(el.getStudentsCount());
//                if(el.getGroupAdmin() != null){
//                    validator.PersonNameValidation(el.getGroupAdmin().getName());
//                    validator.PersonBirthdayValidation(el.getGroupAdmin().getBirthday());
//                    validator.PersonHeightValidation(el.getGroupAdmin().getHeight());
//                    validator.PersonWeightValidation(el.getGroupAdmin().getWeight());
//                    validator.PersonPassportIDValidation(el.getGroupAdmin().getPassportID());
//                }
//                st.addElement(el);
//                idEl++;
//            }
//            st.setmData(collection.getmDATA());
//        }
//        catch (ValidateException | NullPointerException e){
//            logger.warning("Ошибка загрузки данных из файла\n" + idEl + " элемент: " + e.getMessage());
//            return "Ошибка загрузки данных из файла\n" + idEl + " элемент: " + e.getMessage();
//        }
//        catch (IOException e){
//            logger.info("Ошибка загрузки данных из файла: " + e.getMessage());
//        }
//        logger.info("Загрузка коллекции из файла прошла успешно");
//        return "Данные из файла успешно загружены\n";
//    }
    /**
     * Метод реализующий подготовку модели к работе
     * @return строку - итоги работы метода
     */
    public Pair<Integer, String> start(){
        fjp = new ForkJoinPool();
        String response = "Сервер запущен!!!\n";
        logger.info("Сервер запущен");
        Reader r = new Reader();
        Writter w = new Writter();
        //IDHandler idHandler = new IDHandler();
        IOHandlerWithDatabase ioHandler;
        try {
            ioHandler = new IOHandlerWithDatabase(r, w, "jdbc:postgresql://localhost:5432/postgres", "root", "123");
            //ioHandler = new IOHandlerWithDatabase(r, w, "jdbc:postgresql://localhost:5432/studs", "s409442", "MPwTkptB0wEgf7BM");
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
            //throw new RuntimeException("Не получилось подключиться к базе данных\n");
        }
        DataManager dataManager;
        try {
            dataManager = new DataManager(ioHandler);
        }
        catch (SQLException ex) {
            throw new RuntimeException("Не получилось считать данные из базы данных\n");
        }
        CommandsList list = new CommandsList();
        CommandsList listServer = new CommandsList();
        //commandHandler = new Switcher();
        commandServerHandler = new SwitcherServer(dataManager);
        list.register("help", "вывести справку по доступным командам");
        list.register("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        listServer.register("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        list.register("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        listServer.register("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        list.register("add {element}", "добавить новый элемент в коллекцию");
        listServer.register("add {element}", "добавить новый элемент в коллекцию");
        list.register("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        listServer.register("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        list.register("remove_by_id id", "удалить элемент из коллекции по его id");
        listServer.register("remove_by_id id", "удалить элемент из коллекции по его id");
        list.register("clear", "очистить коллекцию");
        listServer.register("clear", "очистить коллекцию");
        list.register("save", "сохранить коллекцию в файл");
        list.register("execute_script file_name", "считать и исполнить скрипт из указанного файла");
        list.register("exit", "завершить программу (без сохранения в файл)");
        list.register("remove_first", "удалить первый элемент из коллекции");
        listServer.register("remove_first", "удалить первый элемент из коллекции");
        list.register("head", "вывести первый элемент коллекции");
        listServer.register("head", "вывести первый элемент коллекции");
        list.register("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        listServer.register("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        list.register("min_by_group_admin", "вывести любой объект из коллекции, значение поля groupAdmin которого является минимальным");
        listServer.register("min_by_group_admin", "вывести любой объект из коллекции, значение поля groupAdmin которого является минимальным");
        list.register("group_counting_by_id", "сгруппировать элементы коллекции по значению поля id, вывести количество элементов в каждой группе");
        listServer.register("group_counting_by_id", "сгруппировать элементы коллекции по значению поля id, вывести количество элементов в каждой группе");
        list.register("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        listServer.register("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        //Command helpCommand = new HelpCommand(list);
        //Command infoCommand = new InfoCommand(dataManager);
        ServerArgumentCommand registerServerCommand = new RegisterUserCommandServer(dataManager);
        ServerArgumentCommand logInServerCommand = new LogInUserCommandServer(dataManager);
        ServerCommand infoServerCommand = new InfoServerCommand(dataManager);
        //Command showCommand = new ShowCommand(dataManager);
        ServerCommand showServerCommand = new ShowServerCommand(dataManager);
        //Command clearCommand = new ClearCommand(dataManager);
        ServerCommand clearServerCommand = new ClearServerCommand(dataManager);
        //Command saveCommand = new SaveCommand(st, ioHandler);
        //Command exitCommand = new ExitCommand();
        //Command removeFirstCommand = new removeFirstCommand(dataManager);
        ServerCommand removeFirstServerCommand = new removeFirstServerCommand(dataManager);
        //Command headCommand = new HeadCommand(dataManager);
        ServerCommand headServerCommand = new HeadServerCommand(dataManager);
        //Command minByGroupAdmin = new MinByGroupAdmin(dataManager);
        ServerCommand minByGroupAdminServer = new MinByGroupAdminServer(dataManager);
        //Command groupCountingById = new GroupCountingByidCommand(dataManager);
        ServerCommand groupCountingByIdServer = new GroupCountingByidServerCommand(dataManager);
        //ArgumentCommand addCommand = new AddCommand(dataManager);
        ServerArgumentCommand addServerCommand = new AddCommandServer(dataManager);
        //ArgumentCommand updCommand = new UpdateCommand(dataManager);
        ServerArgumentCommand updServerCommand = new UpdateCommandServer(dataManager);
        //ArgumentCommand removeByIdCommand = new RemoveByIdCommand(dataManager);
        ServerArgumentCommand removeByIdServerCommand = new RemoveByIdCommandServer(dataManager);
        //ArgumentCommand executeScriptCommand = new ExecuteScriptCommand(ioHandler, commandHandler);
        //ArgumentCommand addIfMin = new AddIfMinCommand(dataManager);
        ServerArgumentCommand addIfMinServer = new AddIfMinCommandServer(dataManager);
        //ArgumentCommand filterContainsName = new FilterContainsNameCommand(dataManager);
        ServerArgumentCommand filterContainsNameServer = new FilterContainsNameCommandServer(dataManager);
//        commandHandler.CommandsRegister("help", helpCommand);
//        commandHandler.CommandsRegister("info", infoCommand);
        commandServerHandler.ArgumentCommandsRegister("register", registerServerCommand);
        commandServerHandler.ArgumentCommandsRegister("logIn", logInServerCommand);
        commandServerHandler.CommandsRegister("info", infoServerCommand);
        //commandHandler.CommandsRegister("show", showCommand);
        commandServerHandler.CommandsRegister("show", showServerCommand);

        //commandHandler.CommandsRegister("clear", clearCommand);
        commandServerHandler.CommandsRegister("clear", clearServerCommand);

        //commandHandler.CommandsRegister("save", saveCommand);
        //commandHandler.CommandsRegister("exit", exitCommand);
        //commandHandler.CommandsRegister("remove_first", removeFirstCommand);
        commandServerHandler.CommandsRegister("remove_first", removeFirstServerCommand);

        //commandHandler.CommandsRegister("head", headCommand);
        commandServerHandler.CommandsRegister("head", headServerCommand);

        //commandHandler.CommandsRegister("min_by_group_admin", minByGroupAdmin);
        commandServerHandler.CommandsRegister("min_by_group_admin", minByGroupAdminServer);

        //commandHandler.CommandsRegister("group_counting_by_id", groupCountingById);
        commandServerHandler.CommandsRegister("group_counting_by_id", groupCountingByIdServer);

        //commandHandler.ArgumentCommandsRegister("add", addCommand);
        commandServerHandler.ArgumentCommandsRegister("add", addServerCommand);

        //commandHandler.ArgumentCommandsRegister("update", updCommand);
        commandServerHandler.ArgumentCommandsRegister("update", updServerCommand);

        //commandHandler.ArgumentCommandsRegister("remove_by_id", removeByIdCommand);
        commandServerHandler.ArgumentCommandsRegister("remove_by_id", removeByIdServerCommand);

        //commandHandler.ArgumentCommandsRegister("execute_script", executeScriptCommand);

        //commandHandler.ArgumentCommandsRegister("add_if_min", addIfMin);
        commandServerHandler.ArgumentCommandsRegister("add_if_min", addIfMinServer);

        //commandHandler.ArgumentCommandsRegister("filter_contains_name", filterContainsName);
        commandServerHandler.ArgumentCommandsRegister("filter_contains_name", filterContainsNameServer);
        //response += writeList(st, ioHandler, idHandler);
        return new Pair<>(0, response);
    }
}
