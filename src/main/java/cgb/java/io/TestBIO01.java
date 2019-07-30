package cgb.java.io;

import java.util.Scanner;

public class TestBIO01 {

	public static void main(String[] args) {
		System.out.println("please input:");
		Scanner sc=new Scanner(System.in);
		String content=sc.nextLine();
		System.out.println("result:"+content);
	}
}
