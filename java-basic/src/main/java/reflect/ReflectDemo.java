package reflect;

import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: zhenL
 * @Description: 个人笔记文档
 * 文档：JAVA反射机制.note
 * 链接：http://note.youdao.com/noteshare?id=bd267665bc72adc16a6d9ecd19c6d505&sub=EAF295E24F9C40A28F4D508E50876276
 */
public class ReflectDemo {
	
	@Test(description = "获取类的属性")
	public void test() throws Exception {
		// 基于Class找到类
		Class<?> clazz = Class.forName("reflect.Person");
		
		/**
		 * 获取类的属性
		 * getField只能获取到公有属性
		 * getDeclaredField能获取到公有的也能获取到私有的
		 */
		Field ageField = clazz.getField("age");
		Field infoField = clazz.getDeclaredField("info");
		
		// 看一下属性的名字
		String agefieldName = ageField.getName();
		String infoFieldName = infoField.getName();
		
		System.out.println(agefieldName);
		System.out.println(infoFieldName);
		
		System.out.println("------------------");
		
		/**
		 * 获取Person类的所有属性,包括公有属性和私有属性,存放在一个数组内
		 * 同样getFields只能获取到共有属性
		 * getDeclaredFields能获取到公有的也能获取到私有的
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
	}
	
	@Test(description = "获取类的方法")
	public void test1() throws Exception {
		Class<?> clazz = Class.forName("reflect.MethodFuncs");
		/**
		 * 获取类的方法
		 * getMethod只能获取到公有方法
		 * getDeclaredMethod能获取到公有的也能获取到私有的
		 */
		Method sumMethod = clazz.getMethod("sum", Integer.class, Integer.class);
		Method sumMethod1 = clazz.getDeclaredMethod("sum1", int.class, int.class);
		
		// 打印方法名
		System.out.println(sumMethod.getName());
		System.out.println(sumMethod1.getName());
		/**
		 * 获取MethodFuncs类的所有方法,包括公有方法和私有方法,存放在一个数组内
		 * 同样getMethods只能获取到共有方法
		 * getDeclaredMethods能获取到公有的也能获取到私有的
		 */
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
	}
	
	@Test(description = "获取类的构造方法")
	public void test2() throws Exception {
		Class<?> clazz = Class.forName("code.reflect.Person");
		/**
		 * 获取类的构造
		 * getConstructor只能获取到公有方法
		 * getDeclaredConstructor能获取到公有的也能获取到私有的
		 */
		Constructor<?> publicConstructor1 = clazz.getConstructor(String.class);
		Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);
		System.out.println(publicConstructor1);
		System.out.println(declaredConstructor);
		/**
		 * 获取Person类的所有构造方法,包括公有方法和私有方法,存放在一个数组内
		 * 同样getConstructors只能获取到公有方法
		 * getDeclaredConstructors能获取到公有的也能获取到私有的
		 */
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}
	}
	
	@Test(description = "获取类的注解")
	public void test3() throws Exception {
		Class<?> clazz = Class.forName("reflect.Person");
		// 获取对类的指定注解NotNull
		NotNull notNullAnnotation = clazz.getAnnotation(NotNull.class);
		System.out.println(notNullAnnotation);
		/**
		 * 获取类中方法的注解
		 * 先获取到该方法，再获取方法的注解
		 * 如果是获取属性的注解，则要先获取到属性
		 */
		Method getNameMethod = clazz.getMethod("getName");
		NotNull methodAnnotation = getNameMethod.getAnnotation(NotNull.class);
		System.out.println(methodAnnotation);
		/**
		 * 获取类的所有注解
		 * 这里用的是getAnnotations，表示只能获取到共有的
		 */
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
		}
		/**
		 * 获取getname方法的所有注解
		 * 这里用的是getDeclaredAnnotations，表示能获取到共有的和私有的
		 */
		Annotation[] declaredAnnotations = getNameMethod.getDeclaredAnnotations();
		for (Annotation declaredAnnotation : declaredAnnotations) {
			System.out.println(declaredAnnotation);
		}
	}
	
	@Test(description = "执行无参方法")
	public void test4() throws Exception {
		// 获取到Person类的Class类对象
		Class<?> clazz = Class.forName("reflect.Person");
		
		// 获取到sayHello方法
		Method helloMethod = clazz.getMethod("sayHello");
		
		// invoke执行通过反射获取的类的方法“sayHello”
		helloMethod.invoke(new Person());
	}
	
	@Test(description = "执行有参方法")
	public void test5() throws Exception {
		Class<?> clazz = Class.forName("reflect.MethodFuncs");
		
		Method sumMethod = clazz.getMethod("sum", Integer.class, Integer.class);
		Method sumMethod1 = clazz.getDeclaredMethod("sum1", int.class, int.class);
		
		// 如果是私有方法需要对私有方法增加可访问的权限
		sumMethod1.setAccessible(true);
		Object result = sumMethod.invoke(new MethodFuncs(), 3, 5);
		Object result1 = sumMethod1.invoke(new MethodFuncs(), 3, 5);
		
		System.out.println(result);
		System.out.println(result1);
	}
	
	@Test(description = "通过 Class 对象的 newInstance() 方法实例化对象")
	public void test6() throws Exception {
		// 获取MethodFuncs的Class类对象
		Class<?> clazz = Class.forName("code.reflect.MethodFuncs");
		
		// 通过newInstance实例化一个对象obj
		// newInstance方法的本质就是帮我们调了一遍MethodFuncs类的构造方法
		Object obj = clazz.newInstance();
		
		// 获取sum方法
		Method sumMethod = clazz.getDeclaredMethod("sum", Integer.class, Integer.class);
		
		// 执行sum方法
		Object result = sumMethod.invoke(obj, 3, 5);
		System.out.println(result);
	}
	
	@Test(description = "通过 Constructor 对象的 newInstance() 方法实例化对象")
	public void test7() throws Exception {
		// 获取MethodFuncs的Class类对象
		Class<?> clazz = Class.forName("code.reflect.MethodFuncs");
		
		Constructor<?> constructor = clazz.getConstructor(String.class);
		Object obj = constructor.newInstance("小明");
		
		// 获取sum方法
		Method sumMethod = clazz.getDeclaredMethod("sum", Integer.class, Integer.class);
		
		// 执行sum方法
		Object result = sumMethod.invoke(obj, 3, 5);
		System.out.println(result);
	}
	
	@Test(description = "对公有属性进行赋值")
	public void test8() throws Exception {
		Class<?> clazz = Class.forName("code.reflect.Person");
		
		Field nameField = clazz.getField("name");
		
		// 通过newInstance实例化对象
		Object instance = clazz.newInstance();
		
		System.out.println("instance before:" + instance);
		
		// 对属性进行赋值
		nameField.set(instance, "张三");
		
		System.out.println("instance after:" + instance);
		
		// 获取属性的值
		Object nameValue = instance.getClass().getField("name").get(instance);
		System.out.println("field get val:" + nameValue);
	}
	
	@Test(description = "对私有属性进行赋值")
	public void test9() throws Exception {
		Class<?> clazz = Class.forName("code.reflect.Person");
		
		// 获取私有属性-->getDeclaredField
		Field nameField = clazz.getDeclaredField("amount");
		
		// 对私有属性增加可访问的权限-->setAccessible
		nameField.setAccessible(true);
		
		// 通过newInstance实例化对象
		Object instance = clazz.newInstance();
		
		System.out.println("instance before:" + instance);
		
		// 给属性赋值
		nameField.set(instance, 1024);
		
		System.out.println("instance after:" + instance);
	}
	
	
}
