package cn.com.zhiding.product.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.zhiding.norm.entity.Module;
import cn.com.zhiding.product.entity.ProductType;
import cn.com.zhiding.user.entity.UserProductRelations;

public interface ProductService {
	
	/**
	 * 查询所有产品类型
	 * @return
	 */
	public List<ProductType> findAllProductType();
	
	/**
	 * 查询用户负责的产品
	 * @param userid
	 * @return
	 */
	public List<UserProductRelations> findUserPros(Long userid);
	
	/**
	 * 查询产品中的维度
	 * @param proid 产品id
	 * @return
	 */
	public Map<String,Set<Module>> findProModules(Long proid);

}
