package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.StorageException;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;

import java.sql.SQLException;

/**
 * Класс реализации команды "clear"
 * @author Ильнар Рахимов
 */
public class ClearCommand implements Command{
    DataManager dataManager;
    public ClearCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, String> execute(User user) {
        try{
            for(int i = 0; i < dataManager.getCollection().size(); i++){
                dataManager.rmGroup(i, user);
            }
        }
        catch (StorageException ignored){

        }
        catch (SQLException e){
            return new Pair<>(0, "Произошла ошибка во время удаления\n");
        }
        return new Pair<>(0, "Все ваши элементы удалены\n");
    }
}
