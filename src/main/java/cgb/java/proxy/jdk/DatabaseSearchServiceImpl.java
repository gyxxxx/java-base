package cgb.java.proxy.jdk;

public class DatabaseSearchServiceImpl implements SearchService{
	@Override
	public void search(String msg) {
		// TODO Auto-generated method stub
		System.out.println("search "+msg+" from database system");
	}
}
