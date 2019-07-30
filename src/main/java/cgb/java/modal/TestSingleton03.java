package cgb.java.modal;
import java.text.SimpleDateFormat;
import java.util.Date;
class DateUtils00{//线程安全
	public static  String format(Date date){
		SimpleDateFormat sdf=
				new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	public static Date parse(String dateStr)
	throws Exception{
		SimpleDateFormat sdf=
		new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(dateStr);
	}
}
class DateUtils01{//线程不安全(不可取)
	//SimpleDateFormat对象是一个线程不安全的对象
	private static SimpleDateFormat sdf=
	new SimpleDateFormat("yyyy-MM-dd");
	public static  String format(Date date){
		return sdf.format(date);
	}
	public static Date parse(String dateStr)
	throws Exception{
		return sdf.parse(dateStr);
	}
}
class DateUtils02{
	private static ThreadLocal<SimpleDateFormat>
	    sdf=new ThreadLocal<SimpleDateFormat>(){
		//当在不同线程访问工具类中的format,parse方式
		//此方法会被调用，每个线程调用一次，此方法返回
		//的对象会绑定到当前线程
		protected SimpleDateFormat initialValue() {
			System.out.println("initialValue");
			return new SimpleDateFormat("yyyy/MM/dd");
		};
	};
	public static  String format(Date date){
		String dateStr=sdf.get().format(date);
		System.out.println(dateStr);
		return dateStr;
	}
	public static Date parse(String dateStr)
	throws Exception{
		return sdf.get().parse(dateStr);
	}
}
public class TestSingleton03 {
	public static void main(String[] args) {
		DateUtils02.format(new Date());
		DateUtils02.format(new Date());
		new Thread(){
			public void run() {
				DateUtils02.format(new Date());
				DateUtils02.format(new Date());
			};
		}.start();
	}
}
