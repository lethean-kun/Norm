package cn.com.zhiding.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.com.zhiding.norm.entity.Module;
import cn.com.zhiding.norm.entity.NormDetailed;
import cn.com.zhiding.user.bean.NormBean;

public class MapUtil implements Comparator<String> {

	/**
	 * 该方法针对map对key进行排序
	 */
	@Override
	public int compare(String o1, String o2) {
		int i1 = 0;//代表o1
		int i2 = 0;//代表o2
		i1 = Integer.valueOf(FigureTerm.getModuleType(o1).getModuleType());
		i2 = Integer.valueOf(FigureTerm.getModuleType(o2).getModuleType());
		return i2-i1;
	}
	/**
	 * 按照key排序Map
	 * @param map
	 * @return
	 */
	public static Map<String,Set<Module>> sortMapByKeyOfOne(Map<String,Set<Module>> map){
		if(map == null || map.isEmpty()){
			return null;
		}
		
		Map<String, Set<Module>> sortMap = new TreeMap<String, Set<Module>>(new MapUtil());
		sortMap.putAll(map);
		return sortMap;
		
	}
	
	/**
	 * 按照key排序Map
	 * @param map
	 * @return
	 */
	public static Map<String,List<NormDetailed>> sortMapByKeyOfTwo(Map<String,List<NormDetailed>> map){
		if(map == null || map.isEmpty()){
			return null;
		}
		Map<String, List<NormDetailed>> sortMap = new TreeMap<String, List<NormDetailed>>(new MapUtil());
		sortMap.putAll(map);
		return sortMap;
	}
	
	/**
	 * 把按照维度分组的常模按照ap分组
	 * @param nbMapByMod
	 * @return 新的map
	 * @author gaoqj
	 */
	public static Map<Long,List<NormBean>> groupMapByAp(Map<Long,List<NormBean>> nbMapByMod){
		Map<Long,List<NormBean>> nbMapByAp = new HashMap<Long,List<NormBean>>();	
		for(Long key:nbMapByMod.keySet()){
			List<NormBean> nbList = nbMapByMod.get(key);
			for(NormBean bean:nbList){
				if(nbMapByAp.containsKey(bean.getApId())){
					nbMapByAp.get(bean.getApId()).add(bean);
				}else{
					nbMapByAp.put(bean.getApId(), new ArrayList<NormBean>());
					nbMapByAp.get(bean.getApId()).add(bean);
				}
			}
		}	
		return nbMapByAp;
	}

}
