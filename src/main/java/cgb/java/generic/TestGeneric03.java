package cgb.java.generic;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//类型擦除？(运行时所有泛型会转换为Object类型)
public class TestGeneric03 {
	public static void main(String[] args)throws Exception{
		List<String> list=new ArrayList<>();
		list.add("A");
		list.add("B");
		//list.add(new Date());
		//?请通过反射技术将new Date()对象写入到list集合
		//1)获取反射的起点对象Class
		Class<?> cls=list.getClass();
		//2)获取add方法对象Method
		Method method=
		cls.getDeclaredMethod("add",Object.class);
		//3)通过反射执行add方法对象,invoke
		Object flag=method.invoke(list, new Date());
		System.out.println(list);//A,B,日期
		System.out.println(flag);
	}
}
