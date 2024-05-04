package View;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс описывающий приемник запросов пользователя. Имеет полный функционал для превращения запроса в необходимый вид
 * @author Ильнар Рахимов
 */
public class Receiver implements acceptable {
    DatagramChannel dc;
    InetSocketAddress addr;
    ByteBuffer buffer;
    private final Scanner scn = new Scanner(System.in);
    /**
     * Функция считывания запросов пользователя
     * @return строку - запрос
     */
    public Receiver(int port) throws IOException {
        addr = new InetSocketAddress(port);
        buffer = ByteBuffer.allocate(1000000);
        dc.bind(addr);
    }
    public void setPort(int port) throws IOException {
        addr = new InetSocketAddress(port);
        dc.bind(addr);
    }
    @Override
    public String consoleIn() {
        String str = "";
        try {
            str = scn.nextLine();
        }
        catch (NoSuchElementException e){
            System.out.println("Завершение работы");
            System.exit(0);
        }
        return str;
    }
    public Packet clientIn() throws IOException {
        SocketAddress sa = dc.receive(buffer);
        return new Packet(buffer.array(), sa);
    }
    public void OpenChannel() throws IOException {
        try{
            dc = DatagramChannel.open();
        }
        catch (IOException e){
            try {
                dc.close();
            } catch (IOException ex) {
                throw new IOException(ex);
            }
            throw new IOException(e);
        }
    }
    public void CloseChannel() throws IOException {
        try {
            dc.close();
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
    }
}
