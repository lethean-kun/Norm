package cn.com.zhiding.norm.mapper;

import java.util.List;

import cn.com.zhiding.norm.entity.Module;
import cn.com.zhiding.norm.entity.Solutions;

public interface SolutionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Solutions record);

    int insertSelective(Solutions record);

    Solutions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Solutions record);

    int updateByPrimaryKey(Solutions record);
    
    /**
     * 查询方案对应的维度
     * @param sidList
     * @return
     */
    List<Module> querysModulesByPro(List<Long> sidList);
}