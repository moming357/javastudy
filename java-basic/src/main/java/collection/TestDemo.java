package collection;


import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhenL
 * @Description: 个人笔记文档
 * 文档：Java集合 - List.note
 * 链接：http://note.youdao.com/noteshare?id=b716d8797ba91fe1ac156389d67ca9c4&sub=4CD3A8459946445091503D9B175B1870
 */
public class TestDemo {
	
	
	/**
	 * 测试结果：在插入大批量数据时arrayList性能比LingkList性能好得多，
	 * 但当数据量小的时候插入性能没有很大差异
	 */
	@Test(description = "测试arrayList和LinkedList的插入数据性能")
	public void testInsert(){
		List<Integer> list1 = new ArrayList<>();
		
		
		long start = System.currentTimeMillis();
		for (int i=0;i<=500000;i++){
			list1.add(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("arraylist time:" + (end - start));
		
		List<Integer> list2 = new LinkedList<>();
		
		long start1 = System.currentTimeMillis();
		for (int i=0;i<=500000;i++){
			list2.add(i);
		}
		long end1 = System.currentTimeMillis();
		System.out.println("linkedList time:" + (end1 - start1));
	}
	
	/**
	 * arrayList没有缩容机制，有扩容机制，
	 * 扩容机制int newCapacity = oldCapacity + (oldCapacity >> 1)每次扩容为1.5倍
	 */
	@Test(description = "测试arrayList扩容和缩容机制")
	public void test(){
		//看看我先把ArrayList增大，看看如果我把元素减少会不会他也会自动将空间缩小
		try {
			ArrayList<Integer> array = new ArrayList<Integer>();
			Class clazz = array.getClass();
			Field field = clazz.getDeclaredField("elementData");
			field.setAccessible(true);
			Object obj;
			obj = field.get(array);
			System.out.println("\nbefore added size is " + array.size());
			System.out.println("before init " + Array.getLength(obj));
			
			for (int i = 0; i < 11; i++) {
				array.add(i);
			}
			
			System.out.println("\nafter added size is " + array.size());
			
			obj = field.get(array);
			System.out.println("after added " + Array.getLength(obj));
			
			array.clear();
			
			System.out.println("\nafter removed size is " + array.size());
			obj = field.get(array);
			System.out.println("after removed " + Array.getLength(obj));
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
