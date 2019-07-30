package cgb.java.generic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 如下两种写法都不正确
 * List<Object> list1=new ArrayList<String>();
 * List<String> list2=new ArrayList<Object>();
 * 如何修改
 * List<? extends Object> list1=new ArrayList<String>();
 * List<? super String> list2=new ArrayList<Object>();
 * */
public class TestGeneric02 {
	static void doPrint(List<? extends Object> list1){
		System.out.println(list1);
	}
	static void doShow(List<? super String> list2){
		System.out.println(list2);
	}
	static void doDisplay(List<?> list){//?代表任意的一种类型
		System.out.println("doDisplay");
		System.out.println(list);
	}
	public static void main(String[] args) {
		List<Integer> list1=
				new ArrayList<Integer>();
		list1.add(100);
		list1.add(200);
		doPrint(list1);
		List<String> list2=
		new ArrayList<String>();
		list2.add("ABCD");
		doPrint(list2);
		//========================
		doShow(list2);
		List<Object> list3=new ArrayList<>();
		list3.add(new Date());
		doShow(list3);
		
		doDisplay(list3);
		doDisplay(list2);
		doDisplay(list1);
		
	}
}




