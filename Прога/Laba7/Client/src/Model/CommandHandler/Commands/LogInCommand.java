package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;

import java.io.IOException;
import java.util.LinkedList;

public class LogInCommand implements ArgumentCommand{
    private Handler server;

    public LogInCommand(Handler server) {
        this.server = server;
    }

    @Override
    public Pair<Integer, String> execute(String arguments) {
        String[] argument = arguments.split(" ");
        LinkedList<Object> args = new LinkedList<>();
        try {
            args.add(argument[0]);
            args.add(argument[1]);
        }
        catch (Exception e) {
            return new Pair<>(0, "Введите логин и пароль\n");
        }
        try {
            int res = (int) server.sendRequestAndGetResponse(new Request("logIn", args));
            if(res == 0){
                LoginAndPassword.login = argument[0];
                LoginAndPassword.password = argument[1];
                return new Pair<>(0, "Вы успешно вошли\n");
            }
            else{
                return new Pair<>(0, "Неправильный логин или пароль, попробуйте еще раз\n");
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
