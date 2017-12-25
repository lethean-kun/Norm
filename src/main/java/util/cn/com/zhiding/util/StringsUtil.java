package cn.com.zhiding.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.zhiding.product.entity.Product;

/**
 * 字符串工具类
 * @author gaoqingjia
 *
 */
public class StringsUtil {
	
	/**
	 * 适用于字符串是否为空
	 * @param str
	 * @return 空-false,非空-true
	 */
	public static boolean isBlank(String str){
		if(str == null || "".equals(str.trim()))
			return false;
		return true;
	}
	
	/**
	 * 获取产品中的工具或者方案id
	 * @param pro
	 * @return key s-方案，t-工具
	 */
	public static Map<String,List<Long>> getTOrSId(Product pro){
		Map<String,List<Long>> proMap = new HashMap<String,List<Long>>();
		List<Long> idList = new ArrayList<Long>();
		Long id = 0l;
		switch(isTOrS(pro.getCombinationId())){
			case 1:
				id = Long.valueOf(pro.getProducts().split("\\_")[1]);
				idList.add(id);
				proMap.put("t", idList);
				break;
			case 2:
				id = Long.valueOf(pro.getProducts().split("\\_")[1]);
				idList.add(id);
				proMap.put("s", idList);
				break;
			case 3:
				String[] pros = {pro.getProducts()};;
				if(pro.getProducts().contains(",")){
					pros = pro.getProducts().split(",");
				}
				List<Long> sidList = new ArrayList<Long>();
				for(String p:pros){
					if(p.startsWith("t")){//工具
						id = Long.valueOf(pro.getProducts().split("\\_")[1]);
						idList.add(id);
					}else{//方案
						id = Long.valueOf(pro.getProducts().split("\\_")[1]);
						sidList.add(id);
					}
				}
				proMap.put("t",idList);
				proMap.put("s",sidList);
				break;
			case 4:
				id = Long.valueOf(pro.getProducts().split("\\_")[1]);
				idList.add(id);
				proMap.put("t", idList);
				break;
			case 5:
				id = Long.valueOf(pro.getProducts().split("\\_")[1]);
				idList.add(id);
				proMap.put("s", idList);
				break;
			default:
				idList = null;
		}	
		return proMap;
	}
	
	
	/**
	 * 判断产品类型是工具或者方案
	 * @return 1--工具，2--方案，3--组合产品，4--定制工具，5--定制方案，0--没有匹配
	 */
	public static Integer isTOrS(String combinationId){
		if(combinationId.startsWith("t")){//工具
			return 1;
		}else if(combinationId.startsWith("s")){//方案
			return 2;
		}else if(combinationId.startsWith("m")){//组合产品
			return 3;
		}else if(combinationId.startsWith("ct")){//定制工具
			return 4;
		}else if(combinationId.startsWith("cs")){//定制方案
			return 5;
		}else{
			return 0;
		}
	}
	
	/**
	 * 按照英文逗号切割字符串
	 * @param para
	 * @return
	 */
	public static List<String> splitByComma(String para){
		List<String> resultList = new ArrayList<String>();
		if(para == null || "".equals(para)){
			return null;
		}
		if(para.contains(",")){
			String [] strs = para.split(",");
			for(String s:strs){
				resultList.add(s);
			}
		}else{
			resultList.add(para);
		}
		return resultList;
	}
	
	
	public static void main(String[] args) {
		String str1 = null;
		System.out.println(str1);
	}

}
