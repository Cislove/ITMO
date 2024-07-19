package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.RequestLogic.Request;
import View.Handler;

import java.io.IOException;
import java.util.LinkedList;

public class RegisterCommand implements ArgumentCommand{
    private Model.NetworkLogic.Handler server;

    public RegisterCommand(Model.NetworkLogic.Handler server) {
        this.server = server;
    }

    @Override
    public Pair<Integer, String> execute(String arguments) {
        String[] argument = arguments.split(" ");
        LinkedList <Object> args = new LinkedList<>();
        args.add(argument[0]);
        args.add(argument[1]);
        try {
            int res = (int) server.sendRequestAndGetResponse(new Request("register", args));
            if(res == 0){
                LoginAndPassword.login = argument[0];
                LoginAndPassword.password = argument[1];
                return new Pair<>(0, "Вы успешно зарегистрировались\n");
            }
            else{
                return new Pair<>(0, "Неправильный логин или пароль\n");
            }
        }
        catch (IOException | ClassNotFoundException e) {
            return new Pair<>( 0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }

    @Override
    public void update() {

    }
}
