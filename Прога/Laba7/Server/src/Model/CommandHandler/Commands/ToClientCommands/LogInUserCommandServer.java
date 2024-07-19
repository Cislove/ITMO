package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.OnServerCommands.ArgumentCommand;
import Model.CommandHandler.Commands.Pair;
import Model.PasswordEncryptor;
import Model.ResponseLogic.Response;
import Model.Storage.DataManager;
import Model.Storage.StorageObject.User;

import java.util.List;

public class LogInUserCommandServer implements ServerArgumentCommand {
    DataManager dataManager;

    public LogInUserCommandServer(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public Pair<Integer, Response> execute(User user, List<Object> arguments) {
//        String login = (String) arguments.get(0);
//        String password = (String) arguments.get(1);
        //System.out.println(login + " aaaa " + password);
        try {
            if(dataManager.verificationUser(user))
                return new Pair<>(0, new Response(0));
            return new Pair<>(0, new Response(1));
        }
        catch(Exception e) {
            return new Pair<>(0, new Response(1));
        }
    }

    @Override
    public void update() {

    }
}
