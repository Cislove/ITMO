package Model;

import Model.CommandHandler.Commands.Pair;

/**
 * Интерфейс взаимодействия с {@link EntryBlock}
 * @author Ильнар Рахимов
 */
public interface IModel {
    public Pair<Integer, String> execute(String request);
    public Pair<Integer, String> start();
}

