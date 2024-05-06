package Model.CommandHandler.Commands.ToClientCommands;

import View.ResponseLogic.Response;

/**
 * Интерфейс для взаимодействия с безаргументными командами
 * @author Ильнар Рахимов
 */
public interface Command{
    Pair<Integer, Response> execute();
}
