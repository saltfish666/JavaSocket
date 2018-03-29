import java.io.IOException;
import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

        System.out.println("Server doing....");
        DatagramSocket server = new DatagramSocket(12355);

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket storedPacket = new DatagramPacket(buf, buf.length);
            server.receive(storedPacket);//阻塞式方法


            String clientIp = storedPacket.getAddress().getHostAddress();
            int clientPort = storedPacket.getPort();//去。。UDP协议连端口都传递？？难道说传递到哪个进程不是之前想的那样？
            //之前没有注意到UDP协议中有这个。。。
            String clientData = new String(storedPacket.getData(), 0, storedPacket.getLength());

            System.out.println(clientIp + ":" + clientPort + " says: " + clientData);
        }

        //server.close();
    }
}
