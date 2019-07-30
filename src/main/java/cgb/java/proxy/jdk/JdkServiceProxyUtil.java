package cgb.java.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cgb.java.annotation.RequiresTime;
public class JdkServiceProxyUtil {
	
	 /**
	  * 此对象由代理对象调用，是负责
	  * 处理具体代理业务的对象。
	  * @author ta
	  */
	 static class ServiceProxyHandler implements InvocationHandler{
		private Object target;
		public ServiceProxyHandler(Object target) {
			this.target=target;
		}
		/**当调用代理对象方法时，代理对象会自动调用
		InvocationHandler对象的invoke方法
		*/
		@Override
		public Object invoke(Object proxy,//代理对象
				Method method, //接口方法对象
				Object[] args //实际参数
				) throws Throwable {
			//获取目标方法对象
			Method targetMethod=
			target.getClass().getDeclaredMethod(
					method.getName(),
					method.getParameterTypes());
			//获取目标方法对象上注解RequiresTime
			boolean flag=targetMethod.isAnnotationPresent(RequiresTime.class);
			//System.out.println("flag="+flag);
			Object result=null;
			if(flag){
			long start=System.nanoTime();
			//调用目标方法
			result=method.invoke(target,args);
			long end=System.nanoTime();
			System.out.println("search time:"+(end-start));
			return result;
			}
			result=method.invoke(target, args);
			return result;//目标方法的执行结果
		}
	 }
	  /**
	   * 通过此方法为目标对象创建一个代理对象
	   * @return
	   */
	  public static Object newProxy(Object target){
		  //JDK中的动态代理(设计模式中的一种)
		  return Proxy.newProxyInstance(
				 target.getClass().getClassLoader(),//代理对象要使用类加载器
				 target.getClass().getInterfaces(),//代理对象要实现的接口
				 new ServiceProxyHandler(target));//invocationHandler
	  }
}




