package cgb.java.cglib;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
public class TestCGLIB01 {
	public static void main(String[] args) {
		//为目标对象产生代理对象
		Enhancer en=new Enhancer();
		//设置代理对象的父类
		en.setSuperclass(FilterService.class);
		//设置方法拦截
		en.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj,//proxy
					Method method, Object[] args,MethodProxy proxy) 
					throws Throwable {
				//System.out.println("obj="+obj.getClass().getName());
				//核心业务+扩展业务
				System.out.println("filter start");
				Object result=proxy.invokeSuper(obj, args);
				System.out.println("filter end");
				return result;
			}//invoke
		});
		//创建代理对象
		FilterService proxy=(FilterService)en.create();
		//执行代理对象方法
		proxy.filter();
	}
}
