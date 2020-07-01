package reflect.apply;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Author: zhenL
 * @Description:
 */
public class CaseOneArgHandler {
	private static final String IFACE_CONFIG_FILE = "iface.properties";
	
	private static final String CASE_BASE_PATH = "cases";
	
	public static void callCase(String caseTitle, String caseFile) throws Exception {
		// 1.获取接口的配置
		Properties ifaceConfigProp = new Properties();
		
		// 加载 cases/create_order/iface.properties
		ifaceConfigProp.load(CaseOneArgHandler.class.getClassLoader()
				.getResourceAsStream(CASE_BASE_PATH + "/" + caseTitle + "/" + IFACE_CONFIG_FILE));
		
		// 2.获取case数据的配置
		Properties caseDataProp = new Properties();
		
		// 加载 cases/create_order/iface.properties
		caseDataProp.load(CaseOneArgHandler.class.getClassLoader()
				.getResourceAsStream(CASE_BASE_PATH + "/" + caseTitle + "/" + caseFile));
		
		// 获取每个case中的 接口类全名,比如: reflect.apply.service.impl.OrderServiceImpl
		String ifaceClassName = ifaceConfigProp.getProperty("iface");
		
		// 获取每个case中的 接口的方法名, 比如: createOrder
		String ifaceMethodName = ifaceConfigProp.getProperty("method");
		
		// 反射接口类,拿到接口的类对象
		Class<?> clazz = Class.forName(ifaceClassName);
		
		// 获取这个类对象的方法
		Method[] methods = clazz.getMethods();
		// clazz.getMethod("createOrder", Order.class);
		
		// 实例化接口的类,相当于是 new OrderServiceImpl()
		Object ifaceInstance = clazz.newInstance();
		
		// 遍历方法,找到我们需要的那个方法,createOrder
		for (Method method : methods) {
			// 遍历的方法后的判断
			if (!method.getName().equals(ifaceMethodName)) {
				continue;
			}
			
			// 获取方法的参数的类型的数组, [String.class,Integer,class,Order.class]
			Class<?>[] parameterTypes = method.getParameterTypes();
			
			Class<?> parameterType = parameterTypes[0];
			
			// 还要进行反射, 目的是将我们的测试数据, 跟我们的参数类型的字段进行设置
			Field[] fields = parameterType.getDeclaredFields();
			
			Object paramInstance = parameterType.newInstance();
			
			// 相当于是对Order的字段进行遍历
			for (Field field : fields) {
				// 相当于是取到了 orderId
				String fieldName = field.getName();
				
				// 这时去properties文件中找数据, caseDataProp.getProperty("orderId");
				String propertyValue = caseDataProp.getProperty(fieldName);
				
				// 私有的属性,设置一下权限
				field.setAccessible(true);
				
				// 对属性进行赋值
				field.set(paramInstance, propertyValue);
			}
			
			// 真正的执行调用,相当于是 orderService.createOrder(order);
			method.invoke(ifaceInstance, paramInstance);
		}
	}
}
