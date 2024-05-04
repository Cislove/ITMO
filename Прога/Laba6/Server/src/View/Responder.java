package View;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Класс описывающий передатчик ответов пользователю. Имеет полный функционал для превращения готового ответа в необходимый вид
 * @author Ильнар Рахимов
 */
public class Responder {
    ByteBuffer buffer;
    DatagramChannel dc;

    public void ConsolePrint(String s) {
        System.out.println(s);
    }

    public void ClientPrint(byte[] arr, SocketAddress addr) throws IOException {
        dc.send(ByteBuffer.wrap(arr).flip(), addr);
    }
    public void OpenChannel(){
        try{
            dc = DatagramChannel.open();
        }
        catch (IOException e){
            try {
                dc.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
    public void CloseChannel(){
        try {
            dc.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
