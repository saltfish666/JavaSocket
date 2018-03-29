import java.io.IOException;
import java.net.*;
import java.io.*;

public class Client1 {
    public static void main(String[] args) throws SocketException,UnknownHostException,IOException {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("UDP going.......");

        int port = 12351;
        int serverPort = 12355;
        byte[] serverIp = {127,0,0,1};

        DatagramSocket client = new DatagramSocket(port);

        while (true) {

            String str = buf.readLine();
            DatagramPacket strPacket = new DatagramPacket(str.getBytes(), str.getBytes().length
                    , InetAddress.getByAddress(serverIp), serverPort);

            client.send(strPacket);
        }

        //client.close();
    }
}