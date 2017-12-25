package cn.com.zhiding.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.zhiding.norm.entity.Module;
import cn.com.zhiding.norm.mapper.ModuleMapper;
import cn.com.zhiding.norm.mapper.ToolsModuleMapper;
import cn.com.zhiding.product.entity.Product;
import cn.com.zhiding.product.entity.ProductType;
import cn.com.zhiding.product.mapper.ProductMapper;
import cn.com.zhiding.product.mapper.ProductTypeMapper;
import cn.com.zhiding.product.service.ProductService;
import cn.com.zhiding.user.entity.UserProductRelations;
import cn.com.zhiding.user.mapper.UserProductRelationsMapper;
import cn.com.zhiding.util.FigureTerm;
import cn.com.zhiding.util.MapUtil;
import cn.com.zhiding.util.StringsUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	ProductMapper pMapper;
	
	@Resource
	ProductTypeMapper pTypeMapper;
	
	@Resource
	UserProductRelationsMapper uprMapper;
	
	@Resource
	ModuleMapper moMapper;
	
	@Resource
	ToolsModuleMapper tmMapper;
	@Override
	public List<ProductType> findAllProductType() {
		return pTypeMapper.queryAllProdcutType();
	}
	
	
	@Override
	public List<UserProductRelations> findUserPros(Long userid) {
		return uprMapper.queryProsByUserid(userid);
	}


	@Override
	public Map<String, Set<Module>> findProModules(Long proid) {
		//返回的结果集，以moduleType为key,维度信息为value
		Map<String, Set<Module>> moduleMap = new HashMap<String, Set<Module>>();
		//查询产品
		Product pro = pMapper.selectByPrimaryKey(proid);
		//产品下面的工具id和方案id
		Map<String,List<Long>> proMap =  StringsUtil.getTOrSId(pro);
		List<Module> motList = new ArrayList<Module>();//工具维度
		List<Module> mosList = new ArrayList<Module>();//方案维度
		for(String s :proMap.keySet()){
			if("t".equals(s)){//工具
				List<Long> idList = proMap.get("t");
				motList =  moMapper.queryTModulesByPro(idList);//维度
			}else{//方案
				List<Long> idList = proMap.get("s");
				mosList =  moMapper.querySModulesByPro(idList);//维度
				//取出方案对应的工具下的维度
				List<Module> solModList = moMapper.querySolMod(proMap.get("s"));
				for(Module id:solModList){
					mosList.add(id);
				}
			}
		} 
		Set<String> keys = moduleMap.keySet();
		//遍历工具下面的维度,如果key中存在维度对应的moduleType，则添加到value中，否则新建
		for(Module mt:motList){
			if(keys.contains(FigureTerm.getFTLevel(mt.getModuletype()).getFTLevel())){
				moduleMap.get(FigureTerm.getFTLevel(mt.getModuletype()).getFTLevel()).add(mt);
			}else{
				moduleMap.put(FigureTerm.getFTLevel(mt.getModuletype()).getFTLevel(), new HashSet<Module>());
				moduleMap.get(FigureTerm.getFTLevel(mt.getModuletype()).getFTLevel()).add(mt);
			}
		}
		//遍历方案下面的维度,如果key中存在维度对应的moduleType，则添加到value中，否则新建
		for(Module ms:mosList){
			if(keys.contains(FigureTerm.getFTLevel(ms.getModuletype()).getFTLevel())){
				moduleMap.get(FigureTerm.getFTLevel(ms.getModuletype()).getFTLevel()).add(ms);
			}else{
				moduleMap.put(FigureTerm.getFTLevel(ms.getModuletype()).getFTLevel(), new HashSet<Module>());
				moduleMap.get(FigureTerm.getFTLevel(ms.getModuletype()).getFTLevel()).add(ms);
			}
		}
		//对map按照key排序
		return MapUtil.sortMapByKeyOfOne(moduleMap);
	}

}
