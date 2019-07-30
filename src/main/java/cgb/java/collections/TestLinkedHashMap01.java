package cgb.java.collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 * LinkedHashMap
 * 1)继承HashMap,是一个特殊的hashmap
 * 2)在HashMap基础上添加了一个链表结构(双向链表)，通过
 * 此链表可以按照一定的规则记录元素顺序。
 * 3)在HashMap基础实现了LRU(最近最少使用算法)算法设计
 * 
 * 如何理解这个规则？(LinkedHashMap记录元素顺序的规则)
 * 1)按照添加顺序进行记录
 * 2)按照访问顺序进行记录
 */
public class TestLinkedHashMap01 {
	public static void main(String[] args) {
		@SuppressWarnings("serial")
		HashMap<String,Object> map=
		//new HashMap<>();//不能保证key添加顺序
		//new LinkedHashMap<>();
	    new LinkedHashMap<String,Object>(
	    		3,//initialCapacity
	    		0.75f,//loadFactor
	    		false){//accessOrder
		    //此方法会在容器满的移除元素
			@Override
		    protected boolean removeEldestEntry(
		    		java.util.Map.Entry eldest) {
		    	return size()>3;
		    }
		};
		map.put("B", 200);
		map.put("A", 100);
		map.put("E", 500);
		System.out.println(map);
		map.get("B");
		map.get("A");
		System.out.println(map);//EBA
		map.put("D", "300");
		System.out.println(map);//EBAD
	}
}
