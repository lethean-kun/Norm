package cn.com.zhiding.util;

import java.util.ArrayList;
import java.util.List;

import cn.com.zhiding.norm.entity.Module;

/**
 * List工具类
 * @author gaoqj
 *
 */
public class ListUtil {
	/**
	 * 冒泡排序
	 * @param list
	 * @return
	 */
	public static List<Module> bubbleSort(List<Module> list){
		for(int i = 0,j=list.size()-1;i<j;i++){
			for(int m = 0,n=list.size()-1-i;m<n;m++){
				int type1 = Integer.valueOf(list.get(m).getModuletype());
				int type2 = Integer.valueOf(list.get(m+1).getModuletype());
				if(type1>type2){
					Module mod = list.get(m);
					list.set(m,list.get(m+1));
					list.set(m+1, mod);
				}
			}	
		}
		return list;
	}

	public static void main(String [] args){
		List<Module> modList = new ArrayList<>();
		for(int i = 0;i<10;i++){
			Module mod = new Module();
			mod.setModuleId(Long.valueOf(i));
			if(i%2 == 0){
				mod.setModuletype(""+(10-i));
			}else{
				mod.setModuletype(""+2);
			}
			modList.add(mod);
		}
		modList = bubbleSort(modList);
		for(int i =0,j=modList.size();i<j;i++){
			System.out.println(modList.get(i).getModuleId()+"<===>"+modList.get(i).getModuletype());
		}
	}
}
