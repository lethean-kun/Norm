package cn.com.zhiding.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 读取XX.properties的工具类
 * @author gaoqj
 *
 */
public class PropertiesUtil {
	//util.properties
	private static final String UTILPROPERTIES ="util.properties";
	
	private Properties properties;
	
	private FileInputStream inputFile;
	
	private FileOutputStream outputFile;
	
	/**
	 * 初始化----无参构造
	 */
	public PropertiesUtil(){
		properties = new Properties();
	}
	
	/**
	 * 初始化----构造
	 * @param filepath 文件路径
	 */
	public PropertiesUtil(String filepath){
		properties = new Properties();
		try {
			InputStream input = new FileInputStream(filepath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
			properties.load(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("找不到"+filepath+"文件");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println(filepath+"不支持UTF-8编码");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(filepath+"读取错误！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据key获取value
	 * @param key 键值
	 * @return value key对应的值
	 */
	public String getValue(String key){
		String value = "";
		if(properties.containsKey(key)){
			value = properties.getProperty(key);
		}
		return value;
	}
	
	/**
	 * 根据文件名和key值获取value
	 * @param filepath
	 * @param key
	 * @return value key对应的值
	 */
	public String getValue(String filepath,String key){
		String value = "";
		try {
			inputFile = new FileInputStream(filepath);
			properties.load(inputFile);
			inputFile.close();
			if(properties.containsKey(key)){
				value = properties.getProperty(key);
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件"+filepath+"找不到");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件"+filepath+"读写异常");
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 清除properties文件中所有的key和value
	 */
	public void clear(){
		properties.clear();
	}
	
	/**
	 * 更改或者添加一个key-value;
	 * 当key存在时，更改；不存在时，添加
	 * @param key
	 * @param value
	 */
	public void setValue(String key,String value){
		properties.setProperty(key, value);
	}
	
	/**
	 * 更改或者添加文件
	 * 当文件存在时，更改文件数据；不存在时，添加文件
	 * @param fileName 文件路径+文件名
	 * @param description 文件描述
	 */
	public void saveFile(String fileName,String description){
		try {
			outputFile = new FileOutputStream(fileName);
			properties.store(outputFile, description);
			outputFile.close();
		} catch (FileNotFoundException e) {
			System.out.println(fileName+"路径有误,请检查无误后再次操作！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(fileName+"读写有误！请稍后再试！");
			e.printStackTrace();
		}
	
	}
	
	public  Properties getProperties(){
		return properties;
	}
	
	public void setProperties(Properties properties){
		this.properties = properties;
	}
	
	/**
	 * 获取文件所有属性
	 * @param fileName 文件名
	 * @return
	 */
	public static Properties getProperties(String fileName){
		PropertiesUtil pu = new PropertiesUtil();
		Properties p = new Properties();
		try {
			p.load(pu.getClass().getClassLoader().getResource(fileName).openStream());
		} catch (IOException e) {
			System.out.println("文件读取异常！");
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 * 获取指定文件的属性值
	 * @param key 键值
	 * @return key对应的值
	 */
	public static String getUtilValue(String key){
		return getProperties(UTILPROPERTIES).getProperty(key);
	}
	
	public static void main(String [] args){
		System.out.println(getUtilValue("normaccuracy"));
	}
	
}
