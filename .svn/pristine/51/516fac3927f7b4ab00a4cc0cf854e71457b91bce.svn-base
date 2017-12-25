package cn.com.zhiding.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 思路过程：
 * 1、将字符串转化成字节数组(para.getBytes())。字符串中每个字符转换为对应的ASCII值作为字节数组中的一个元素
 * 2、将字节数组通过固定算法转换为16个元素的有符号哈希值字节数组
 * 3、将哈希字节数组的每个元素通过0xff与运算转换为无符号16进制的字符串
 * 4、将不足两位的无符号16进制的字符串加0
 * 5、通过StringBuffer.append()函数将16个长度为2的无符号进制字符串合并为1个32为String类型的MD5码
 * @author gaoqj
 * @version 2017-9-25 
 */
public class MD5Util {
	
	/**
	 * 
	 * @param para
	 * @return
	 */
	public static String getEncryption(String para){
		StringBuffer strCode = new StringBuffer(); 
		try {
			//获取MD5加密算法的对象
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//获取要加密的对象
			byte[]  digest = md5.digest(para.getBytes());
			for(byte b : digest){
				String hexString = Integer.toHexString(b & 0xff);
				if(hexString.length() < 2){
					hexString = "0" + hexString;
				}
				strCode.append(hexString);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strCode.toString().toUpperCase();
	}
	
	public static void main(String[] args){
		System.out.println(getEncryption("123456"));
	}

}
