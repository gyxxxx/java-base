package cgb.java.proxy.jdk;

import java.util.List;

public class TestProxy02 {

	public static void main(String[] args) {
		DaoProxy dProxy=new DaoProxy();
		SearchDao dao=
		dProxy.newProxy(SearchDao.class);
		System.out.println(dao.getClass().getName());
		List<String> list=dao.search("tedu");
		System.out.println(list);
		
	}
}
