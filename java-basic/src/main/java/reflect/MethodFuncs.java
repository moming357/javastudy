package reflect;

/**
 * @Author: zhenL
 * @Description:
 */
public class MethodFuncs {
	public MethodFuncs() {
	
	}
	
	public MethodFuncs(String name) {
		System.out.println("name=" + name);
		System.out.println("MethodFuncs Init");
	}
	
	public void foo1() {
		System.out.println("foo1");
	}
	
	public void foo2() {
		System.out.println("foo2");
	}
	
	public void foo3() {
		System.out.println("foo3");
	}
	
	public Integer sum(Integer i, Integer j) {
		return i + j;
	}
	
	private int sum1(int i, int j) {
		return i + j;
	}
}
