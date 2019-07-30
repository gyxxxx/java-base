package cgb.java.cglib;

import cgb.java.cglib.CGLIBProxyUtil.ServiceCallbackHandler;

public class TestCGLIB02 {

	public static void main(String[] args) {
		FilterService fs=(FilterService)
		CGLIBProxyUtil.newProxy(
				FilterService.class,
				new ServiceCallbackHandler());
		//System.out.println(fs.getClass().getName());
		fs.filter();
	}
}
