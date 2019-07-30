package cgb.java.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestNIOFileChannel02 {

	@Test
	public void testFileChannel()throws Exception{
		//构建一个Buffer对象(缓冲区)：JVM内存
		ByteBuffer buf=ByteBuffer.allocate(2);
		//构建一个文件通道对象(可以读写数据)
		FileChannel fChannel=
		FileChannel.open(Paths.get("data.txt"),
		StandardOpenOption.READ);//读模式
		//将文件内容读到缓冲区(ByteBuffer)
		int len=-1;
		do{
		 len=fChannel.read(buf);
		 System.out.println("切换buf模式，开始从buf读数据");
		 buf.flip();
		 //判定缓冲区中是否有剩余数据
		 while(buf.hasRemaining()){
		 System.out.print((char)buf.get());//每次都1个字节
		 }
		 System.out.println();
		 buf.clear();//每次读数据应将原数据设置为无效。
		}while(len!=-1);
		//释放资源
		fChannel.close();
	}
}
