package cgb.java.reflect;
import java.lang.reflect.Constructor;

import cgb.java.annotation.Configuration;

@Configuration
class DataSourceConfig{
	static{
		System.out.println("static");
	}
	public DataSourceConfig() {
		
		System.out.println("DataSourceConfig()");
	}
}

public class TestReflect01 {

	public static void main(String[] args)throws Exception {
		//1.获取类DataSourceConfig的类全名(包名+类名)
		String file="cgb/java/reflect/DataSourceConfig.class";
		String classFile=file.replace("/", ".");
		System.out.println(classFile);
		String pkgClass=
		classFile.substring(0,
		classFile.lastIndexOf("."));
		System.out.println(pkgClass);
		//2.将类加载到内存
		Class<?> c1=Class.forName(pkgClass);//cgb.java.reflect.DataSourceConfig
		//3.判定此类上是否由注解Configuration
		boolean flag=
		c1.isAnnotationPresent(Configuration.class);
		if(!flag)return;
		//4.假如有Configuration注解,系统会获取此类
		//的构造方法对象
		Constructor<?> con1=
		c1.getDeclaredConstructor();
		//5.设置构造方法对象可访问
		con1.setAccessible(true);
		//6.基于Class对象构建类的实例对象
		Object obj=con1.newInstance();
		System.out.println(obj);
		
		
		
		
	}
}
