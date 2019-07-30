package cgb.java.generic;

import java.util.Date;

/**
 * 泛型
 * 1.定义
 * 1)JDK1.5以后推出的一种规范,一种参数化类型。
 * 2)是编译时的一种类型，运行时无效。
 * 3)相当于是在类或方法上贴了个标签
 * 2.场景？
 * 1)通过泛型对类中属性类型，方法参数类型，方法返回值类型进行约束
 * 2)是通用性编程的一种手段(对象共性代码进行提取的一种方式)
 * 3.为什么要使用泛型？
 * 将类型问题进行前置，提高运行时的速度。
 * 4.泛型在java中应用？
 * 1)泛型类，例如： 类名<泛型>
 * 2)泛型方法，例如： <泛型>方法返回值
 * 
 */

class Container<T>{//自定义泛型类
	public void add(T obj){}
	public T get(){
		return null;
	}
}
class ContainerUtil{
	//泛型方法
    static <E>void print(E t){
    	System.out.println(t);
    }
    //自定义泛型方法
    static <T>T get(T t){
    	return t;
    }
    
    static <T>T getBean(Class<T> cls){
    	try{
    	 return cls.newInstance();
    	}catch(Exception e){
    	 return null;
    	}
    }   
}
public class TestGeneric01 {

	public static void main(String[] args) {
		Container<Integer> c1=
				new Container<Integer>();
		c1.add(100);
		//c1.add("ABCD");
		ContainerUtil.print(100);
		Date t1=ContainerUtil.get(new Date());
		System.out.println(t1);
		Date t2=
		ContainerUtil.getBean(Date.class);
		System.out.println("t2="+t2);
		
	}
}
