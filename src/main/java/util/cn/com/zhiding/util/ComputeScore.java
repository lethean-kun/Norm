package cn.com.zhiding.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import cn.com.zhiding.norm.entity.ExamResult;


/**
 * 不同的工具类型对应着不同的原始分字段
 * 参考51--新算分逻辑_20161221修改.xlsx
 * @author gaoqj
 *
 */
public class ComputeScore {
	
	//计算开方时用到
	private static final BigInteger HUNDRED = BigInteger.valueOf(100);
	//精度
	private static final int SCALE = Integer.valueOf(PropertiesUtil.getUtilValue("normaccuracy"));
	
	/**
	 * 根据不同的工具类型，取存在不同字段的原始分
	 * @param toolsType 工具类型
	 * @param er 作答结果
	 * @return
	 */
	public static BigDecimal getOriginalScore(String toolsType,ExamResult er){
		switch(toolsType){
			case "1":
				return new BigDecimal(er.getTotalmark());
			case "3":
				return new BigDecimal(er.getAvgquesmark());
			case "5":
				return new BigDecimal(er.getAvgdimemark());
			case "6":
				return new BigDecimal(er.getTotalmark());
			case "7":
				return new BigDecimal(er.getTotalmark());
			case "11":
				return new BigDecimal(er.getTotalmark());
			case "12":	
				return new BigDecimal(er.getTotalmark());
			case "13":
				return new BigDecimal(er.getAvgquesmark());
			case "14":
				return new BigDecimal(er.getTotalmark());
			default:
				return new BigDecimal(0.0);
		}
	}
	
	/**
	 * 计算平均分--分数集合
	 * X=(O1+O2+....+On)/n
	 * @param originalScore
	 * @return 集合的平均分
	 */
	public static BigDecimal getAverageScoreByList(List<BigDecimal> originalScore){
		BigDecimal sum = new  BigDecimal("0.0");
		for(BigDecimal bd : originalScore){
			sum = sum.add(bd);
		}
		return sum.divide(new BigDecimal(originalScore.size()),SCALE,BigDecimal.ROUND_HALF_UP);	
	}
	
	/**
	 * 计算平均分--分数合
	 * X=sum/n
	 * @param sum
	 * @param size
	 * @return
	 */
	public static BigDecimal getAverageScoreBySum(BigDecimal sum,int size){
		return sum.divide(new BigDecimal(size),SCALE,BigDecimal.ROUND_HALF_UP);	
	}
	
	
	
	/**
	 * 标准差 
	 * S=sqrt((O1-X)2+(O2-X)2+...+(On-X)2)/n)
	 * @param originalScore
	 * @param average
	 * @return
	 */
	public static BigDecimal getStandardDeviation(List<BigDecimal> originalScore,BigDecimal average){
		BigDecimal sum = new  BigDecimal("0.0");
		for(BigDecimal bd:originalScore){
			sum = sum.add(bd.subtract(average).pow(2));
		}
		return sqrt(sum.divide(new BigDecimal(originalScore.size()),SCALE*2,BigDecimal.ROUND_HALF_DOWN));
	}
	
	/**
	 * 平方根
	 * 
	 * 开方的计算步骤
	 * 1．将被开方数的整数部分从个位起向左每隔两位划为一段，用撇号分开（竖式中的11’56），分成几段，表示所求平方根是几位数；
	 * 2．根据左边第一段里的数，求得平方根的最高位上的数（竖式中的3）；
	 * 3．从第一段的数减去最高位上数的平方，在它们的差的右边写上第二段数组成第一个余数（竖式中的256）；
	 * 4．把求得的最高位数乘以20去试除第一个余数，所得的最大整数作为试商（20×3除256，所得的最大整数是 4，即试商是4）；
	 * 5．用商的最高位数的20倍加上这个试商再乘以试商．如果所得的积小于或等于余数，试商就是平方根的第二位数；如果所得的积大于余数，就把试商减小再试（竖式中（20×3+4）×4=256，说明试商4就是平方根的第二位数）；
	 * 6．用同样的方法，继续求平方根的其他各位上的数．
	 * 如遇开不尽的情况，可根据所要求的精确度求出它的近似值．例如求 的近似值（精确到0.01），可列出上面右边的竖式，并根据这个竖式得到
	 * 以1156为例
	 * 这里1156是四位数，所以它的算术平方根的整数部分是两位数，且易观察出其中的十位数是3．于是问题的关键在于；怎样求出它的个位数a？为此，我们从a所满足的关系式来进行分析．
	 * 根据两数和的平方公式，可以得到
	 * 1156=(30+a)^2=30^2+2×30a+a^2，
	 * 所以 1156－30^2=2×30a+a^2，
	 * 即 256=(30×2+a)a，
	 * 这就是说， a是这样一个正整数，它与30×2的和，再乘以它本身，等于256．
	 * 为便于求得a，可用下面的竖式来进行计算：
	 * 根号上面的数3是平方根的十位数．将 256试除以30×2，得4(如果未除尽则取整数位).由于4与30×2的和64，与4的积等于256，4就是所求的个位数a．竖式中的余数是0，表示开方正好开尽．于是得到 1156=34^2， 或√1156=34. 
	 * @param number 原数值
	 * @return
	 */
	public static BigDecimal sqrt(BigDecimal number){
		if (number.compareTo(BigDecimal.ZERO) < 0)  
			throw new ArithmeticException("sqrt with negative");  
		if(number.doubleValue() == 0.0){
			return new BigDecimal("0.0");
		}
		BigInteger integer = number.toBigInteger();  //整数部分
		StringBuffer sb = new StringBuffer();  //拼接结果
		String strInt = integer.toString();  
		int lenInt = strInt.length();  
		if (lenInt % 2 != 0) {  //数字的位数如果是奇数,在最左边补一个0；偶位数不操作
		strInt = '0' + strInt;  
		    lenInt++;  
		}  
		BigInteger res = BigInteger.ZERO;  
		BigInteger rem = BigInteger.ZERO;  
		for (int i = 0; i < lenInt / 2; i++) {  
		    res = res.multiply(BigInteger.TEN);  
		    rem = rem.multiply(HUNDRED);  
		      
		    BigInteger temp = new BigInteger(strInt.substring(i * 2, i * 2 + 2));  
		    rem = rem.add(temp);        
		    BigInteger j = BigInteger.TEN;  
		    while (j.compareTo(BigInteger.ZERO) > 0) {  
		        j = j.subtract(BigInteger.ONE); 
		        if (((res.add(j)).multiply(j)).compareTo(rem) <= 0) {  
		            break;  
		        }  
		    }  
		      
		    res = res.add(j);  
		    rem = rem.subtract(res.multiply(j));  
		    res = res.add(j);  
		    sb.append(j);  
		}  
		sb.append('.');  
		BigDecimal fraction = number.subtract(number.setScale(0, BigDecimal.ROUND_DOWN));  
		int fracLen = (fraction.scale() + 1) / 2;  
		fraction = fraction.movePointRight(fracLen * 2);  
		String strFrac = fraction.toPlainString();  
		int lenFrac = strFrac.length();  
		if (lenFrac % 2 != 0) {  //数字的位数如果是奇数,在最左边补一个0；偶位数不操作
		strFrac = '0' + strFrac;  
		    lenFrac++;  
		}
		for (int i = 0; i < lenFrac/2; i++) {             
		    res = res.multiply(BigInteger.TEN);  
		    rem = rem.multiply(HUNDRED);  
		    if (i < fracLen) {  
		        BigInteger temp = new BigInteger(strFrac.substring(i * 2, i * 2 + 2));  
		        rem = rem.add(temp);  
		    }  
		      
		    BigInteger j = BigInteger.TEN;  
		    while (j.compareTo(BigInteger.ZERO) > 0) {  
		        j = j.subtract(BigInteger.ONE);  
		        if (((res.add(j)).multiply(j)).compareTo(rem) <= 0) {  
		            break;  
		        }  
		    }  
		    res = res.add(j);  
		    rem = rem.subtract(res.multiply(j));  
		    res = res.add(j);  
		    sb.append(j);  
		}  
		return new BigDecimal(sb.toString()).setScale(SCALE, BigDecimal.ROUND_HALF_UP); 
	}
	
	/**
	 * 标准Z分 
	 * Z=(O-X)/S
	 * @param originalScore 原始分
	 * @param averageScore 平均分
	 * @param standardScore 标准差
	 * @return
	 */
	public static BigDecimal getStandardZScore(BigDecimal originalScore,BigDecimal averageScore,BigDecimal standardDeviation){
		return (originalScore.subtract(averageScore)).divide(standardDeviation, SCALE,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 已知∑合
	 * Z:标准Z分;Q：权重
	 * Z’= (Za*Qa+Zb*Qb+...)/(|a|+|b|+...)
	 * @param sum 和
	 * @param weiSum  权重和
	 * @return
	 */
	public static BigDecimal getWeightingZScoreBySum(BigDecimal sum,BigDecimal weiSum){
		return sum.divide(weiSum, SCALE, BigDecimal.ROUND_HALF_UP);
	}
	
	public static  void main(String args[]){
		Long start = System.currentTimeMillis();
		for(int i = 0;i < 10000;i++){
			List<BigDecimal> originalScore = new ArrayList<>();
			originalScore.add(new BigDecimal("5.1"));
			originalScore.add(new BigDecimal("5.1"));
			originalScore.add(new BigDecimal("5.2"));
			originalScore.add(new BigDecimal("5.3"));
			originalScore.add(new BigDecimal("5.4"));
			originalScore.add(new BigDecimal("5.5"));
			originalScore.add(new BigDecimal("5.5"));
			originalScore.add(new BigDecimal("5.5"));
			originalScore.add(new BigDecimal("5.3"));
			BigDecimal temp = getAverageScoreByList(originalScore);
			System.out.println("originalScore的平均数："+temp);
			System.out.println("originalScore的标准差："+getStandardDeviation(originalScore,temp));
			System.out.println(sqrt(new BigDecimal("25")));
		}
		Long end = System.currentTimeMillis();
		System.out.println("耗时："+(end - start)+"ms");
	}
	
	
}
