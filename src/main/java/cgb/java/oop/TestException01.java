package cgb.java.oop;

public class TestException01 {

	static int doMethod01(int n){
		System.out.println("doMethod01");
		try{ 
		  //@Before
		  n++;
		  return n;//@AfterReturning
		}finally{
		  n++;//@After
		}
	}
	static int doMethod02(int n){
		try{
		if(n<1) //@Before
		throw new IllegalArgumentException("n的值不能小于1");
		return n+10;
		}catch(Exception e){
		return n;//@AfterReturning
		}finally{
		n++; //@After
		}
	}
	static void doMethod03(int a,int b){
		int result=-1; //@Before
		try{
		result=a/b;
		}catch(RuntimeException e){
		result=-2;
		throw e; //@Afterthrowing
		}finally{
		 System.out.println(result);//@After
		}
	}
	public static void main(String[] args) {
		//int result=doMethod02(-1);
		//System.out.println(result);//11,
		doMethod03(10,0);
	}
}
