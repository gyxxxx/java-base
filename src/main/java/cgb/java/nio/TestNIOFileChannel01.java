package cgb.java.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestNIOFileChannel01 {

	@Test
	public void testFileChannel()throws Exception{
		//构建一个Buffer对象(缓冲区)：JVM内存
		ByteBuffer buf=ByteBuffer.allocate(1024);
		//构建一个文件通道对象(可以读写数据)
		FileChannel fChannel=
		FileChannel.open(Paths.get("data.txt"),
		StandardOpenOption.READ);//读模式
		//将文件内容读到缓冲区(ByteBuffer)
		fChannel.read(buf);
		System.out.println("切换buf模式，开始从buf读数据");
		System.out.println(buf.position());
		//从Buffer中取数据
		buf.flip();
		System.out.println(buf.position());
		System.out.println(new String(buf.array()));
		buf.clear();//不是是清除，而将数据标记为脏数据(无用数据)
		//释放资源
		fChannel.close();
	}
}
