package cgb.java.io;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
public class TestBIO02 {
	
	@Test
	public void doServer()throws Exception{	
		ServerSocket server=
		new ServerSocket(9090);
		System.out.println("server start");
		//等待客户端的连接
		Socket socket=server.accept();//阻塞式方法
		System.out.println("client connection");
		ObjectInputStream ois=
		new ObjectInputStream(socket.getInputStream());
		String content=ois.readUTF();
		System.out.println("server read "+content);
		socket.close();
		server.close();
	}
	@Test
	public void doClient()throws Exception{
		Socket socket=new Socket();
		socket.connect(new InetSocketAddress("127.0.0.1", 9090));
		OutputStream out=socket.getOutputStream();
		ObjectOutputStream oos=
		new ObjectOutputStream(out);//装饰模式
		oos.writeUTF("hello server");
		oos.close();
		socket.close();
	}
}








