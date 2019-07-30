package cgb.java.proxy.jdk;

public class TestProxy01 {

	public static void main(String[] args) {
		//1.初始化目标对象
		SearchService service=
		new FileSearchServiceImpl();
		//new DatabaseSearchServiceImpl();
		//2.创建代理对象
		service=(SearchService)
		JdkServiceProxyUtil.newProxy(service);
		System.out.println(service.getClass().getName());
		//3.执行代理对象方法
		service.search("tmooc");
	}
}
