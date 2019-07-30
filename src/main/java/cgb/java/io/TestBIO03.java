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
public class TestBIO03 {
	
	@Test
	public void doServer()throws Exception{
		BlockingQueue<Runnable> workQueue=
		new LinkedBlockingQueue<>(3);
		ThreadPoolExecutor tPool=
		new ThreadPoolExecutor(5, 10,30,TimeUnit.SECONDS,workQueue);
		ServerSocket server=new ServerSocket(9090);
		System.out.println("server start");
		//等待客户端的连接
		while(true){
		Socket socket=server.accept();//阻塞式方法
		System.out.println("client connection");
		tPool.execute(new Runnable() {
			@Override
			public void run() {
				try{
				ObjectInputStream ois=
				new ObjectInputStream(socket.getInputStream());
				String content=ois.readUTF();
				System.out.println("server read "+content);
				}catch(Exception e){
				e.printStackTrace();
				}finally{
					//....socket.close();
				}
			}
		});
		}
		//server.close();
	}
	@Test
	public void doClient()throws Exception{
		Socket socket=new Socket();
		socket.connect(new InetSocketAddress("127.0.0.1", 9090));
		OutputStream out=socket.getOutputStream();
		ObjectOutputStream oos=
		new ObjectOutputStream(out);//装饰模式
		oos.writeUTF("hello server "+System.currentTimeMillis());
		oos.close();
		socket.close();
		while(true){}
	}
}








