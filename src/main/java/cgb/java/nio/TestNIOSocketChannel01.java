package cgb.java.nio;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import org.junit.Test;

//NIO(NewIO)中同步阻塞式应用
public class TestNIOSocketChannel01 {
	@Test
	public void doServer()throws Exception{//服务端
		//构建缓冲区对象
		ByteBuffer buf=ByteBuffer.allocate(1024);
		//构建服务端Channel对象
		ServerSocketChannel ssChannel=
		ServerSocketChannel.open();
		//绑定监听端口
		ssChannel.bind(new InetSocketAddress(9898));
		//等待客户端连接
		SocketChannel sChannel=ssChannel.accept();
		//读取客户端数据
		sChannel.read(buf);
		//输出读取的数据
		buf.flip();
		String content=new String(buf.array());
		System.out.println("clien say:"+content);
		buf.clear();
		//释放资源
		sChannel.close();
		ssChannel.close();
	}
	@Test
	public void doClient()throws Exception{//客户端
		//1.构建缓冲区对象
		ByteBuffer buf=ByteBuffer.allocate(100);
		//2.构建客户端Channel对象
		SocketChannel sChannel=SocketChannel.open();
	    //3.连接服务端
	    sChannel.connect(new InetSocketAddress("127.0.0.1",9898));
		//4.向服务端写数据
	    Scanner sc=new Scanner(System.in);
	    System.out.println("client input:");
	    String content=sc.nextLine();
	    buf.put(content.getBytes());
	    buf.flip();
	    sChannel.write(buf);
		//5.释放资源
	    sc.close();
	    sChannel.close();
	}
}





