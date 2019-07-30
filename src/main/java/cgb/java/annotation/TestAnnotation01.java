package cgb.java.annotation;

import java.io.File;

/**
 * 1.注解是什么？
 * 
 * 1)JAVA中的一个特殊的类
 * 2)JAVA中的一种元数据(描述数据的数据):相当于一个生活中标签
 * 3)所有注解的源程序都是.java,编译完成都会生成.class
 * 
 * 2.注解有什么作用？
 * 1)描述类,属性,方法,参数.
 * 2)描述注解(注解也是一个特殊的类)
 * 
 * 3.为什么要使用注解？
 * 
 * 1)通过编译器识别类成员写是否符合要求(编译时有效注解)
 * 2)通过反射机制识别注解并应用注解,以更好实现框架编程.
 * 
 * 4.注解如何使用呢？(我们一般使用运行时有效的注解)
 * 1)定义注解?(借助@interface关键字)
 * 2)描述类成员
 * 3)使用反射获取注解,并结合业务使用注解.
 */
@ComponentScan("cgb.java")
class AppConfig{
}
public class TestAnnotation01 {
	static void doScanClass(Class<?> cfgClass){
		//2.获取类上的注解
		//2.1判断类上是否有注解
		 boolean flag=
		 cfgClass.isAnnotationPresent(ComponentScan.class);
	    //2.2获取类上注解
		 if(flag){
		 ComponentScan cs=
		 cfgClass.getDeclaredAnnotation(ComponentScan.class);
		 String pkg=cs.value();
		 System.out.println(pkg);
		 pkg=pkg.replace(".", "/");
		 System.out.println(pkg);
		 //File f=new File(pkg);
		 //.................
		 }
	}
	public static void main(String[] args)throws Exception {
	   //1.应用反射起点(Class对象):字节码对象
	   Class<?> c1=AppConfig.class;
	   Class<?> c2=Class.forName("cgb.java.annotation.AppConfig");
	   Class<?> c3=new AppConfig().getClass();
	   System.out.println(c1==c2);
	   System.out.println(c2==c3);
	   //2.加载配置文件扫描指定包中的类
	   doScanClass(c1);

	}
}




