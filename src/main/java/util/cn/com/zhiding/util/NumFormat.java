package cn.com.zhiding.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * 数字类型转换类
 * @author gaoqj
 *
 */
public class NumFormat {
	
	public static void main(String [] args){
		BigDecimal bd = new BigDecimal("0.0000000001");

		System.out.println("数字类型转换："+formatSCM(bd));
		
		
	}
	/**
	 * BigDecimal类型转换为字符串
	 * 
	 * @return
	 */
	public static String formatSCM(BigDecimal bd){
		DecimalFormat format = (DecimalFormat)NumberFormat.getInstance();
		format.applyPattern("0.0000000000");
		return format.format(bd);
	}

}
