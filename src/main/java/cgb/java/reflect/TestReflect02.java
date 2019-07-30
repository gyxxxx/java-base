package cgb.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class Point{
	private int x;
	private int y;
	private Point() {}
	
	private Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}
public class TestReflect02 {
	static Object newInstance01(Class<?> cls)
	throws Exception{
		//1.获取类对象对应的构造方法对象
		Constructor<?> con=
		cls.getDeclaredConstructor();
		//2.设置构造方法可访问
		con.setAccessible(true);
		//3.基于构造方法对象构建类的对象
		return con.newInstance();
	}
	static Object newInstance02(Class<?> cls)
			throws Exception{
		//1.获取类对象对应的构造方法对象
		Constructor<?> con=
		cls.getDeclaredConstructor(int.class,int.class);
		//2.设置构造方法可访问
		con.setAccessible(true);
		//3.基于构造方法对象构建类的对象
		return con.newInstance(10,20);
	}
	static Object doSetDI(Class<?> cls)
	throws Exception{
		//构建对象
		Object obj=newInstance01(cls);
		//调用对象set方法
		String name="X";//解析xml获取的
		int xValue=10;//解析xml获取的
		Method[] ms=cls.getDeclaredMethods();
		for(Method m:ms){
			if(m.getName().equals("set"+name)){
				m.invoke(obj, xValue);
			}
		}
		return obj;
	}
	public static void main(String[] args)
	throws Exception{
		Class<?> cls=
	    Class.forName("cgb.java.reflect.Point");
		Object o1=newInstance01(cls);
		System.out.println(o1);
		//构造注入
		Object o2=newInstance02(cls);
		System.out.println(o2);
		//set注入
		Object o3=doSetDI(cls);
		System.out.println(o3);
	}
}





