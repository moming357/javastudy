package IO;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @Author: zhenL
 * @Description: 文件操作流
 */
public class FileOperationDemo {
	
	private static final String FILE_PATH = "C:\\Users\\94061\\IdeaProjects\\java-basic\\src\\main\\java\\IO\\txt\\test.log";
	
	@Test(description = "FileInputStream实例")
	public void test(){
		File file = new File(FILE_PATH);
		try (InputStream ins = new FileInputStream(file)) {
			byte[] buf = new byte[512];
			
			int len = 0;
			
			while ((len = ins.read(buf)) != -1) {
				String val = new String(buf, 0, len);
				System.out.println(val);
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}
	
	@Test(description = "FileOutputStream实例")
	public void test1(){
		// 定义file
		File file = new File(FILE_PATH);
		try (OutputStream outputStream = new FileOutputStream(file)){
			// 写数据
			String str = "hello world！";
			outputStream.write(str.getBytes());
		} catch (FileNotFoundException ffe) {
			ffe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	@Test(description = "FileReader实例")
	public void test2(){
		
		File file = new File(FILE_PATH);
		try(FileReader reader = new FileReader(file)) {
			
			// 文件读取
			char[] buf = new char[4];
			// 标准的写法
			int len = 0;
			while ((len = reader.read(buf)) != -1) {
				System.out.println("len=" + len);
				// 这里要注意,String的构造,要注意边界
				String val = new String(buf, 0, len);
				System.out.println(val);
			}
			
			// 这里的读取,如果不使用len来进行标识会有问题
			// while (reader.read(buf) != -1) {
			//   String val = new String(buf, 0, buf.length);
			//   System.out.println(val);
			// }
			
			// 理解的读取过程
			// while (len != -1) {
			//   len = reader.read(buf);
			//   if (len != -1) {
			//     String val = new String(buf, 0, len);
			//     System.out.println(val);
			//   }
			// }
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	@Test(description = "FileWriter实例")
	public void test3(){
		// 定位文件描述
		File file = new File(FILE_PATH);
		try (FileWriter fileWriter = new FileWriter(file)){
			// 写数据
			String str = "hello world！";
			fileWriter.write(str);
		} catch (FileNotFoundException ffe) {
			ffe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
