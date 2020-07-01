package IO;

import java.io.File;
/**
 * @Author: zhenL
 * @Description:
 *       遍历本地一个目录下.找到后缀名是.log的,且占用空间最大的那个文件删除之
 *      目录下还有目录，使用递归实现
 */

public class IOCase1 {
	public static void main(String[] args) {
		
		deleteLog("C:\\Users\\94061\\IdeaProjects\\java-basic\\src\\main\\java\\IO",".log");
	}
	
	/**
	 * 遍历目录下的文件，删除指定后缀且文件大小最大的文件
	 * @param path   遍历目录的路径
	 * @param endstr 文件后缀
	 */
	public static void deleteLog(String path,String endstr){
		
		// 定位文件描述
		File dir = new File(path);
		
		// 列出该目录下所有的文件对象，存入一个文件对象数组当中
		File[] files = dir.listFiles();
		
		// 初始化最大文件对象和最大文件大小
		long maxFileSize = 0;
		File maxFile = null;
		
		// 遍历文件对象数组
		for (File file:files) {
			// 如果是文件，则找出指定后缀且大小最大的文件
			if (file.isFile()){
				long len = file.length();
				String fileName = file.getName();
				if (fileName.endsWith(endstr)){
					if (len >maxFileSize ){
						maxFileSize = len;
						maxFile = file;
					}
				}
			}
			// 如果是目录，则递归调用deleteLog
			if (file.isDirectory()){
				path =file.getAbsolutePath();
				deleteLog(path,endstr);
			}
		}
		if (maxFile != null){
			System.out.println("删除"+maxFile.getAbsolutePath()+"文件");
			maxFile.delete();
			System.out.println("删除成功");
		}
		else {
			System.out.println("未找到目标文件");
		}
	}
}


