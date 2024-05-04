package View;


import java.io.*;
import java.nio.ByteBuffer;

/**
 * Класс описывающий обработчик запросов к вьюхе от контроллера
 * @author Ильнар Рахимов
 */
public class Handler {
    private final Responder res;
    private final Receiver rec;
    private Packet request;
    public Handler(int port) throws IOException {
        rec = new Receiver(port);
        rec.OpenChannel();
        res = new Responder();
        res.OpenChannel();
    }
    /**
     * Метод отправляющий пользователю ответ и считывающий запрос
     * @return строку - запрос
     */
    public String update(String str){
        res.ConsolePrint(str);
        return rec.consoleIn();
    }
    private byte[] serializer(Object data) throws SerializeException {
        TransmittedPacket packet = new TransmittedPacket(data);
        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(bos);) {
            out.writeObject(packet);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage());
        }
    }
    private ReceivedPacket deserialize(byte[] arr) throws SerializeException{
        ByteBuffer buffer = ByteBuffer.wrap(arr);
        try(ByteArrayInputStream bis = new ByteArrayInputStream(arr); ObjectInputStream in = new ObjectInputStream(bis);){
            return (ReceivedPacket) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            throw new SerializeException(e.getMessage());
        }
    }
    /**
     * Метод отправляющий пользователю ответ
     */
    public void send(String str){
        res.ConsolePrint(str);
    }
    private ReceivedPacket acceptClient(){
        try {
            request = rec.clientIn();
            return deserialize(request.getData());
        }
        catch(IOException | SerializeException e){
            return null;
        }
    }
    private int sendClient(Object data){
        try {
            byte[] response = serializer(data);
            res.ClientPrint(response, request.getSocket());
            return 0;
        }
        catch (IOException | SerializeException e){
            return sendClient("Простите, сервер не смог обработать вашу команду\n");
        }
    }
}
