package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.OnServerCommands.ArgumentCommand;
import Model.CommandHandler.Commands.Pair;
import Model.PasswordEncryptor;
import Model.ResponseLogic.Response;
import Model.Storage.DataManager;
import Model.Storage.StorageObject.User;

import java.util.List;

public class RegisterUserCommandServer implements ServerArgumentCommand {
    DataManager dataManager;

    public RegisterUserCommandServer(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public Pair<Integer, Response> execute(User user, List<Object> arguments) {
//        String login = (String) arguments.get(1);
//        String password = (String) arguments.get(2);
        try {
            dataManager.registerUser(user);
            return new Pair<>(0, new Response(0));
        }
        catch (Exception e) {
            return new Pair<>(0, new Response(1));
        }
    }

    @Override
    public void update() {

    }
}
