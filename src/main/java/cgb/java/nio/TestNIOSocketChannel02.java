package cgb.java.nio;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;
//NIO(NewIO)中同步非阻塞式应用
public class TestNIOSocketChannel02 {
	@Test
	public void doServer()throws Exception{//服务端
		//1. 获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		//2. 切换非阻塞模式
		ssChannel.configureBlocking(false);
		//3. 绑定连接
		ssChannel.bind(new InetSocketAddress(9898));
		//4. 获取选择器
		Selector selector = Selector.open();
		//5. 将通道注册到选择器上, 并且指定“监听接收事件”
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		//6. 轮询式的获取选择器上已经“准备就绪”的事件
		while(selector.select() > 0){			
		//7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
		Iterator<SelectionKey> it = selector.selectedKeys().iterator();
		while(it.hasNext()){
		//8. 获取准备“就绪”的是事件
		SelectionKey sk = it.next();
		//9. 判断具体是什么事件准备就绪
		if(sk.isAcceptable()){
		//10. 若“接收就绪”，获取客户端连接
		SocketChannel sChannel = ssChannel.accept();
		//11. 切换非阻塞模式
		sChannel.configureBlocking(false);
		//12. 将该通道注册到选择器上
		sChannel.register(selector, SelectionKey.OP_READ);
		}else if(sk.isReadable()){
		//13. 获取当前选择器上“读就绪”状态的通道
		SocketChannel sChannel = (SocketChannel) sk.channel();
		//14. 读取数据
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int len = 0;
		while((len = sChannel.read(buf)) > 0 ){
			  buf.flip();
		System.out.println(new String(buf.array(), 0, len));
		buf.clear();
		}
		}
		//15. 取消选择键 SelectionKey
		it.remove();
		}
		}

	}
	@Test
	public void doClient()throws Exception{//客户端
		//1.构建缓冲区对象
		ByteBuffer buf=ByteBuffer.allocate(100);
		//2.构建客户端Channel对象
		SocketChannel sChannel=SocketChannel.open();
	    //3.连接服务端
	    sChannel.connect(new InetSocketAddress("127.0.0.1",9898));
	    //4.设置同步非阻塞(这是NIO的关键):注意(连接之后进行设置)
	    sChannel.configureBlocking(false);
		//4.向服务端写数据
	    Scanner sc=new Scanner(System.in);
	    System.out.println("client input:");
	    while(sc.hasNext()){
	    String str = sc.next();
		buf.put((new Date().toString() + "\n" + str).getBytes());
	    buf.flip();
	    sChannel.write(buf);
	    buf.clear();
	    }
		//5.释放资源
	    sc.close();
	    sChannel.close();
	}
}





