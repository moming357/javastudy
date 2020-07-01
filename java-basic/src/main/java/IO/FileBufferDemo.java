package IO;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @Author: zhenL
 * @Description: 缓冲操作流
 */
public class FileBufferDemo {

	private static final String FILE_PATH = "C:\\Users\\94061\\IdeaProjects\\java-basic\\src\\main\\java\\IO\\txt\\test.log";
	
	@Test(description = "BufferedReader实例")
	public void test(){
		File file = new File(FILE_PATH);
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			// 文件读取
			String val = null;
			// 一行一行读取文件内容
			while ((val = reader.readLine()) != null) {
				System.out.println(val);
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	@Test(description = "BufferedWriter实例")
	public void test1(){
		File file = new File(FILE_PATH);
		try (Writer writer = new BufferedWriter(new FileWriter(file))){
			String str = "Hello,world!!";
			writer.write(str);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(description = "BufferedInputStream实例")
	public void test2(){
		File file = new File(FILE_PATH);
		try(InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
			byte[] bytes = new byte[128];
			int len = 0;
			
			while ((len =inputStream.read(bytes)) != -1) {
				String val = new String(bytes,0,len);
				System.out.println("val = " + val);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(description = "BufferedOutputStream实例")
	public void test3(){
		File file = new File(FILE_PATH);
		try(OutputStream outputStream =new BufferedOutputStream(new FileOutputStream(file))) {
			String str = "hello world";
			outputStream.write(str.getBytes());
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}
