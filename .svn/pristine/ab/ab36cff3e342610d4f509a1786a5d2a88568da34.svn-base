package cn.com.zhiding.norm.mapper;

import java.util.List;

import cn.com.zhiding.norm.entity.Tools;
import cn.com.zhiding.norm.entity.ToolsWithBLOBs;

public interface ToolsMapper {
    int deleteByPrimaryKey(Long toolId);

    int insert(ToolsWithBLOBs record);

    int insertSelective(ToolsWithBLOBs record);

    ToolsWithBLOBs selectByPrimaryKey(Long toolId);

    int updateByPrimaryKeySelective(ToolsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ToolsWithBLOBs record);

    int updateByPrimaryKey(Tools record);
    
    /**
     * 根据工具类型查询工具id
     * @param toolsIds
     * @return
     */
    List<Tools> queryToolsType(List<Long> toolsIds);
}