package cgb.java.oop;

public class TestOOP04 {
	static class Shape{
		private String name;
	/*	public Shape() {
			// TODO Auto-generated constructor stub
		}*/
		public Shape(String name){
			this.name=name;
		}
		public Shape getShape(){
			return this;
		}
	}
	static class Point extends Shape{
		int x;
		int y;
		{//实例代码块
			this.x=100;
			this.y=200;
		}
		/**
		 * this 应用在构造方法，实例方法，实例代码块内部
		 * this 永远指向调用此方法或代码块的的对象
		 * @param x
		 * @param y
		 */
		public Point(int x,int y){
			super("点");//默认super()
			this.x=x;
			this.y=y;
		}
		public Point getPoint(){
			return this;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	public static void main(String[] args) {
		Point p1=new Point(10,20);
		System.out.println(p1);
		Point p2=p1.getPoint();//return this
		System.out.println(p1==p2);//true
		Shape p3=p1.getShape();//return this
		System.out.println(p2==p3);//true
		Shape s1=new Shape("图形");
		Shape p4=s1.getShape();//return 
		System.out.println(p3==p4);//false
	}
}
