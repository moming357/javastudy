package IO;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: zhenL
 * @Description: 读取.properties配置文件
 */
public class PropDemo {
	
	@Test
	public void test() throws IOException {
		
		Properties prop = new Properties();
		
		// 使用ClassLoader来读取
		prop.load(PropDemo.class.getClassLoader().getResourceAsStream("config.properties"));
		
		prop.setProperty("name", "jim");
		
		String property1 = prop.getProperty("name");
		
		// 若没有YY这个key，则使用defaultValue,若没有设置，则返回null
		String property2 = prop.getProperty("YY","abc");
		
		System.out.println(property1);
		System.out.println(property2);
	}
}
