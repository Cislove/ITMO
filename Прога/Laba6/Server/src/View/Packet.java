package View;

import java.net.SocketAddress;

public class Packet{
    private byte[] arr;
    private SocketAddress addr;
    public Packet(byte[] arr, SocketAddress addr){
        this.arr = arr;
        this.addr = addr;
    }
    public byte[] getData(){
        return arr;
    }
    public SocketAddress getSocket(){
        return addr;
    }
}
