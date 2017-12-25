package cn.com.zhiding.norm.mapper;

import java.util.List;

import cn.com.zhiding.norm.entity.SolutionsTools;

public interface SolutionsToolsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SolutionsTools record);

    int insertSelective(SolutionsTools record);

    SolutionsTools selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SolutionsTools record);

    int updateByPrimaryKey(SolutionsTools record);
    
    /**
     * 查询方案下面包含的工具
     * @param id
     * @return
     */
    List<SolutionsTools> queryBySolutionId(List<Long> ids);
}