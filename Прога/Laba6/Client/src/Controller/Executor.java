package Controller;

import View.Handler;
import Model.CommandHandler.Commands.Pair;
import Model.EntryBlock;

/**
 * Класс исполнитель всей программы. Отвечает за связывание модели и вьюхи
 * @author Ильнар Рахимов
 */
public class Executor implements IExecutor {
    final private EntryBlock model = new EntryBlock();
    final private Handler view = new Handler();
    /**
     * Метод реализующий исполнение программы
     */
    @Override
    public void execute(int port) {
        int numberOfConnectionAttempts = 1;
        view.send(model.startWelcome().getRight() + "Попытка установления соединения " + numberOfConnectionAttempts + "\n");
        Pair<Integer, String> response = model.startServer(port);
        while (response.getLeft() != 0 && numberOfConnectionAttempts < 3){
            numberOfConnectionAttempts++;
            view.send(response.getRight() + "Попытка установления соединения " + numberOfConnectionAttempts + "\n");
            response = model.startServer(port);
        }
        view.send(response.getRight());
        if(response.getLeft() != 0){
            view.send("В настоящее время сервер не доступен\n");
            System.exit(0);
        }
        response = model.start();
        String request;
        do {
            request = view.update(response.getRight());
            response = model.execute(request);
        } while (response.getLeft() != -1);
        view.send(response.getRight());
        System.exit(0);
    }
}
