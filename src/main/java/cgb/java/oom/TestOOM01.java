package cgb.java.oom;

public class TestOOM01 {

	public static void main(String[] args) {
		//OutOfMemoryError
		int[] array=new int[Integer.MAX_VALUE];
	}
}
