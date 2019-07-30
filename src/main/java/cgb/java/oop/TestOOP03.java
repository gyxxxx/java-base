package cgb.java.oop;
import java.util.HashMap;
public class TestOOP03 {
	private static class Container{
		private static Container instance=
				new Container();
		private HashMap<String,Object>
		map=new HashMap<>();
		private Container() {
			map.put("A", 100);
			map.put("B", 200);
		}
		public static Container getInstance() {
			return instance;
		}
		public void doPrint(){
			System.out.println(map);
		}
	}
	public static void main(String[] args) {
		Container c=Container.getInstance();
		c.doPrint();;
	}
}
