package cgb.java.oop;
class A{
	@Override
	public String toString() {
		return A.class.getName();
	}
}
public class TestOOP02 {

	public static void main(String[] args) {
		A a1=new A();
		System.out.println(a1);
		A a2=new A();
		System.out.println(a2);
		System.out.println(a1==a2);
	}
}
