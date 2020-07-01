package reflect;

/**
 * @Author: zhenL
 * @Description:
 */
public class Person {
	public String name;
	
	public int age;
	
	private String info;
	
	private long amount;
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(final int age) {
		this.age = age;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(final String info) {
		this.info = info;
	}
	
	public long getAmount() {
		return amount;
	}
	
	public void setAmount(final long amount) {
		this.amount = amount;
	}
	
	public void sayHello() {
		System.out.println("hello java reflect");
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Person{");
		sb.append("name='").append(name).append('\'');
		sb.append(", age=").append(age);
		sb.append(", info='").append(info).append('\'');
		sb.append(", amount=").append(amount);
		sb.append('}');
		return sb.toString();
	}
}
