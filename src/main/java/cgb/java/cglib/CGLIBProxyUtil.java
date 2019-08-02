package cgb.java.cglib;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
public class CGLIBProxyUtil {
	/**
	 * 处理方法回调的一个处理器
	 * @author ta
	 */
	public static class ServiceCallbackHandler 
	          implements MethodInterceptor{
		@Override
		public Object intercept(Object obj,//proxy
				Method method, 
				Object[] args, 
				MethodProxy proxy) throws Throwable {
			System.out.println("filter start");
			Object result=proxy.invokeSuper(obj, args);
			System.out.println("filter end");
			return result; 
		}
	}
	public static Object newProxy(Class<?> targetClass,
			Callback callback){
		//1.构建Enhancer对象(用于创建代理对象的一个API对象)
		Enhancer en=new Enhancer();
		//2.设置superClass(代理对象要继承的那个类)
		en.setSuperclass(targetClass);
		//3.设置回调接口(Callback)
		en.setCallback(callback);
		//4.创建代理对象(这个代理对象会拦截所有方法的执行)
		return en.create();
	}
}