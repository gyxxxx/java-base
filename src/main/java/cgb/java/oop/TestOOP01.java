package cgb.java.oop;
class Shape{}
class Point{
	private int x;
	private int y;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public double distance(Point point){
		int a=(this.x-point.x);
		int b=(this.y-point.y);
		return Math.sqrt(a*a+b*b);//use a
	}
}
class Circle extends Shape{//is a
	//has a
	private Point point;
}

public class TestOOP01 {
	public static void main(String[] args) {
	   Point p1=new Point(3,5);
	   Point p2=new Point(7,5);
	   double dis=p1.distance(p2);
	   System.out.println(dis);
	}
}











