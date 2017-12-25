package cn.com.zhiding.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	
	/**
	 * 写入到文件
	 * @param content 要写入文件的内容
	 * @return 0-SUCCESS,1-FAILED
	 */
	public static int WriteToFile(String content){
		BufferedWriter bw = null;
		FileWriter fw = null;
		String filePath = "";
		if(judgeOS() == 0){
			filePath = PropertiesUtil.getUtilValue("winnormtest");
		}else{
			filePath = PropertiesUtil.getUtilValue("linnormtest");
		}
		File file = new File(filePath);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			fw = new FileWriter(file,false);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	} 
	
	/**
	 * 判断操作系统
	 * @return 0-windows,1-linux
	 */
	public static int judgeOS(){
		String osname = System.getProperty("os.name");
		if(osname.toLowerCase().startsWith("win")){
			return 0;
		}
		return 1;
	} 
	
	public static void main(String [] args){
		if(judgeOS() == 0){
			System.out.println("操作系统是windows");
		}else{
			System.out.println("操作系统是linux");
		}
	}

}
