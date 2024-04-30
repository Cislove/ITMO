package Model.CommandHandler.Commands;

/**
 * Класс реализации команды "help"
 * @author Ильнар Рахимов
 */
public class HelpCommand implements Command{
    CommandsList list;
    public HelpCommand(CommandsList list){
        this.list = list;
    }
    @Override
    public Pair<Integer, String> execute() {
        StringBuilder s = new StringBuilder();
        for(Pair<String, String> i: list.getList()){
            s.append(i.getLeft()).append(": ").append(i.getRight()).append("!\n");
        }
        return new Pair<>(0, s.toString());
    }
}
