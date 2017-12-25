package cn.com.zhiding.norm.mapper;

import java.util.List;

import cn.com.zhiding.norm.entity.ModuleRelations;

public interface ModuleRelationsMapper {
    int deleteByPrimaryKey(Long modulerelationId);

    int insert(ModuleRelations record);

    int insertSelective(ModuleRelations record);

    ModuleRelations selectByPrimaryKey(Long modulerelationId);

    int updateByPrimaryKeySelective(ModuleRelations record);

    int updateByPrimaryKey(ModuleRelations record);
    
    /**
     * 根据高阶维度id，工具id查询低阶维度集合
     * @param id
     * @return
     */
    List<ModuleRelations> querySonByParent(Long id,Long toolsId);
    
    /**
     * 查询权重
     * @param parent 高阶维度
     * @param son	子维度
     * @param toolsId  所属工具
     * @return
     */
    Double queryWeight(Long parent,Long son,Long toolsId);
}