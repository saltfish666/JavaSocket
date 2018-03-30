import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPClient1 {
    public static void main(String[] args) throws SocketException,UnknownHostException,IOException {
        System.out.println("client is going .....");

        String targetIP = "127.0.0.1";
        int targetPort = 12401;

        //创建和服务器连接的套接字
        Socket client = new Socket(targetIP,targetPort);

        //获取字节流     写字节流
        OutputStream out = client.getOutputStream();
        out.write("hi,I am client1".getBytes());

        client.close();
    }
}
