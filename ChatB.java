import java.io.IOException;
import java.net.*;
import java.io.*;

public class ChatB {
    public static void main(String[] args) throws SocketException,UnknownHostException,IOException {
        int myport = 12377;

        byte[] targetIp = {127,0,0,1};
        int targetPort = 12366;

        new Thread(new send(targetIp,targetPort)).start();
        new Thread(new recive(myport)).start();

    }
}
