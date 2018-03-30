import java.io.IOException;
import java.net.*;
import java.io.*;

public class ChatA {
    public static void main(String[] args) throws SocketException,UnknownHostException,IOException {
        int myport = 12366;
        System.out.println("you port is: " + myport);

        byte[] targetIp = {127,0,0,1};
        int targetPort = 12377;
        System.out.println("target IP:" + targetIp + ", target port:" + targetPort);

        new Thread(new send(targetIp,targetPort)).start();
        new Thread(new recive(myport)).start();

    }
}

//用于发送数据的线程
class send implements Runnable{

    private DatagramSocket sendSocket;
    private byte[] targetIp;
    private int targetPort;

    //接受 IP 和 port；
    public send(byte[] targetIp,int targetPort) {
        this.targetIp = targetIp;
        this.targetPort = targetPort;
    }

    @Override
    public void run() {

        System.out.println("now you can type message:");
        System.out.println("type quit() or exit() to stop input;");

        try {
            sendSocket = new DatagramSocket();
        }catch (SocketException e){};

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            //读取数据
            String str = "";
            try {
                str = buf.readLine();
            }catch (IOException e){};

            if(str.equals("exit()") || str.equals("quit()")){
                break;
            }

            DatagramPacket strPacket;
            try {
                //把字符串封装成数据包
                strPacket = new DatagramPacket(str.getBytes(), str.getBytes().length,
                                               InetAddress.getByAddress(targetIp), targetPort);

                try {
                    //发送数据
                    sendSocket.send(strPacket);
                }catch (IOException e){};

            }catch (UnknownHostException e){};

        }
    }
}

//接受数据的线程
class recive implements Runnable{

    private DatagramSocket reciveSocket;
    private int bindPort;

    public recive(int bindPort){
        this.bindPort = bindPort;
    }

    @Override
    public void run(){

        //监听端口
        try {
            reciveSocket = new DatagramSocket(bindPort);
        }catch (SocketException e){};


        while (true) {

            //准备存储数据包的空数据包
            byte[] buf = new byte[1024];
            DatagramPacket sourcePacket = new DatagramPacket(buf, buf.length);

            //阻塞，等待数据包
            try {
                reciveSocket.receive(sourcePacket);
            }catch (IOException e){};

            //unpack 数据包
            String sourceIp = sourcePacket.getAddress().getHostAddress();
            int sourcePort = sourcePacket.getPort();
            String sourceData = new String(sourcePacket.getData(), 0, sourcePacket.getLength());
            System.out.println(sourceIp + ":" + sourcePort + " says: " + sourceData);
        }
    }
}