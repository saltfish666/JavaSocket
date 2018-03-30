import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPServer {
    public static void main(String[] args) throws SocketException,UnknownHostException,IOException {
        System.out.println("server is going .....");

        int myport = 12401;

        //创建 服务端对象
        ServerSocket serverSocket = new ServerSocket(myport);

        //获取和客户端连接的套接字
        Socket socket = serverSocket.accept();

        String clientIP = socket.getInetAddress().getHostAddress();
        int clientPort  = socket.getPort();

        //获取字节流对象
        InputStream in = socket.getInputStream();

        byte[] buf = new byte[1024];
        //read(byte[] b)   从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。 返回长度
        int len = in.read(buf);
        String str = new String(buf,0,len);
        System.out.println(clientIP + ":" +clientPort+" says  "+ str);

        socket.close();

        serverSocket.close();
    }
}

