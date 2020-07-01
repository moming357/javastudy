package IO;

import org.testng.annotations.Test;

import java.io.File;

/**
 * @Author: zhenL
 * @Description: 个人笔记文档
 * 文档：java基础-IO.note
 * 链接：http://note.youdao.com/noteshare?id=5048e52666e1856343c2236e7c51c516&sub=B3AB1C36CCB04D298FB85FC0D297D3C5
 */
public class IODemo {
	
	@Test(description = "文件属性操作(读、写、执行)")
	public void demo1(){
		File file = new File("IO/txt/test.log");
		
		boolean exists = file.exists();
		System.out.println("exists = " + exists);
		
		boolean canRead = file.canRead();
		System.out.println("canRead = " + canRead);
		
		boolean canWrite = file.canWrite();
		System.out.println("canWrite = " + canWrite);
		
		boolean canExecute = file.canExecute();
		System.out.println("canExecute = " + canExecute);
		
		boolean isFile = file.isFile();
		System.out.println("isFile = " + isFile);
		
		boolean isDir = file.isDirectory();
		System.out.println("isDir = " + isDir);
	}
	
	@Test(description = "文件属性操作(获取文件信息：文件名称、路径)")
	public void demo2(){
		File file = new File("IO/txt/test.log");
		final String fileName = file.getName();
		System.out.println("fileName = " + fileName);
		
		File fileAbsoluteFile = file.getAbsoluteFile();
		System.out.println("fileAbsoluteFile = " + fileAbsoluteFile);
		
		String fileAbsolutePath = file.getAbsolutePath();
		System.out.println("fileAbsolutePath = " + fileAbsolutePath);
		
		String fileParent = file.getParent();
		System.out.println("fileParent = " + fileParent);
		File fileParentFile = file.getParentFile();
		System.out.println("fileAbsoluteFile = " + fileAbsoluteFile);
		
		final String filePath = file.getPath();
		System.out.println("fileParent = " + fileParent);
	}
	
	
}
