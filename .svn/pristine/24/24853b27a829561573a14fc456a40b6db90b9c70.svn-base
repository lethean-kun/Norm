package cn.com.zhiding.product.mapper;

import java.util.List;

import cn.com.zhiding.product.entity.ProductType;

public interface ProductTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);
    
    /**
     * 查询全部产品类型
     * @return 全部产品类型
     */
    List<ProductType> queryAllProdcutType();
}