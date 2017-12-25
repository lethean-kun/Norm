package cn.com.zhiding.norm.mapper;

import cn.com.zhiding.norm.entity.SolutionsModule;

public interface SolutionsModuleMapper {
    int deleteByPrimaryKey(Long smId);

    int insert(SolutionsModule record);

    int insertSelective(SolutionsModule record);

    SolutionsModule selectByPrimaryKey(Long smId);

    int updateByPrimaryKeySelective(SolutionsModule record);

    int updateByPrimaryKey(SolutionsModule record);
}