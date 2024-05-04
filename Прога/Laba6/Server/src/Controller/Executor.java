package Controller;

import Model.CommandHandler.Commands.Pair;
import Model.EntryBlock;
import Server.View.Handler;

import java.io.IOException;

/**
 * Класс исполнитель всей программы. Отвечает за связывание модели и вьюхи
 * @author Ильнар Рахимов
 */
public class Executor implements IExecutor {
    final private EntryBlock model;
    final private Handler view;
    public Executor() throws IOException{
        model = new EntryBlock();
        view = new Handler(6597);
    }
    /**
     * Метод реализующий исполнение программы
     */
    @Override
    public void execute() {
        Pair<Integer, String> response = model.start();
        String request;
        do {
            request = view.update(response.getRight());
            response = model.execute(request);
        } while (response.getLeft() != -1);
        view.send(response.getRight());
        System.exit(0);
    }
}
