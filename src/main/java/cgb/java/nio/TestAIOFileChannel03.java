package cgb.java.nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

public class TestAIOFileChannel03 {

	public static void main(String[] args)throws Exception {
		//创建Buffer(缓冲区)对象
		ByteBuffer buf=ByteBuffer.allocate(1024);
		//创建Channel(通道)对象
		AsynchronousFileChannel fc=
		AsynchronousFileChannel.open(
		Paths.get("pom.xml"),
		StandardOpenOption.READ);
		//读数据到缓冲区
		/*Future<Integer> ft=fc.read(buf,0);
		while(!ft.isDone()){//阻塞线程(判定是否结束了)
			System.out.println("wait");
		}*/
		fc.read(buf,0,"attachment",new CompletionHandler<Integer,Object>() {
		   public void completed(Integer result,Object attachment) {
			   System.out.println(Thread.currentThread().getName()+":complete");
			   byte array[]=new byte[128];
			   buf.flip();
			   buf.get(array);
			   System.out.println(attachment+" array:"+new String(array));
		   };
		   public void failed(Throwable exc,Object attachment) {
			   System.out.println("failed");
		   };
		});
		System.out.println("main do other work");
	}
}
