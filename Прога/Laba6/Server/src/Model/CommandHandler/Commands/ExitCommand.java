package Model.CommandHandler.Commands;

/**
 * Класс реализации команды "exit"
 * @author Ильнар Рахимов
 */
public class ExitCommand implements Command{
    @Override
    public Pair<Integer, String> execute() {
        return new Pair<>(-1, "Программа завершила работу\n");
    }
}
