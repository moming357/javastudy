package IO;


import java.io.*;

/**
 * @Author: zhenL
 * @Description:
 * 实现文件复制, 从指定位置复制到目标位置.
 *  思考,若指定文件不存在?
 *  若目标位置文件已存在?
 *  若目标位置目录不存在?
 *  其他请自行发挥
 */

public class IOCase2 {
	
	public static void main(String[] args) {
		
		String initialFile = "C:\\Users\\94061\\IdeaProjects\\java-basic\\src\\main\\java\\IO\\txt\\test.log";
		String targetDir = "C:\\Users\\94061\\IdeaProjects\\java-basic\\src\\main\\java\\IO\\txt\\test\\test.log";
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			// 读文件
			br = new BufferedReader(new FileReader(initialFile));
			String value;
			String data = "";
			
			while ((value = br.readLine()) != null){
				data += value + "\n";
			}
			
			// 写文件
			bw = new BufferedWriter(new FileWriter(targetDir));
			bw.write(data);
			bw.flush();
			
			// 捕获异常
		}catch (FileNotFoundException ex){
			ex.printStackTrace();
		}catch (IOException ix) {
			ix.printStackTrace();
		}finally {
			try {
				if (null != br && null != bw){
					br.close();
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
