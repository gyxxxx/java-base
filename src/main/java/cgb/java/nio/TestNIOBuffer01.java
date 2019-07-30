package cgb.java.nio;
import java.nio.ByteBuffer;
import org.junit.Test;
public class TestNIOBuffer01 {
	 private void doPrint(int pos,int limit,int cap){
		 System.out.println("position:"+pos);
		 System.out.println("limit:"+limit);
		 System.out.println("capacity:"+cap);
	 }
	 @Test
	 public void testBuffer01(){
		 //构建一个缓冲区对象(在JVM内存中分配一块区域)
		 ByteBuffer buf=ByteBuffer.allocate(1024);
		 System.out.println("===数据写入前===");
		 doPrint(buf.position(),buf.limit(),buf.capacity());
         //向缓冲区写入数据
		 byte []data="hello".getBytes();
		 buf.put(data);//放入缓冲区
		 System.out.println("===数据写入后===");
		 doPrint(buf.position(),buf.limit(),buf.capacity());
		 //切换模式(底层同时会移动指针,position位置会发生变换)
		 buf.flip();
		 System.out.println("===读数据之前===");
		 doPrint(buf.position(),buf.limit(),buf.capacity());
		 byte c1=buf.get();
		 System.out.println((char)c1);
		 System.out.println("===读数据之后===");
		 doPrint(buf.position(),buf.limit(),buf.capacity());
	 }
}
