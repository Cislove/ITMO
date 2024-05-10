package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;

/**
 * Интерфейс для взаимодействия с безаргументными командами
 * @author Ильнар Рахимов
 */
public interface Command{
    Pair<Integer, String> execute();
}