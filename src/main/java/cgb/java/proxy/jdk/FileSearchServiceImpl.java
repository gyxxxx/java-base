package cgb.java.proxy.jdk;

import cgb.java.annotation.RequiresTime;

public class FileSearchServiceImpl implements SearchService {

	//@RequiresTime
	@Override
	public void search(String msg) {
		//long start=System.nanoTime();
		System.out.println("search "+msg+" from file system");
		//long end=System.nanoTime();
		//System.out.println("search time:"+(end-start));
	}//违背OCP原则(开闭原则：对扩展开放对修改关闭)

}
