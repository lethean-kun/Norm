package cn.com.zhiding.norm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.zhiding.norm.entity.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(Long moduleId);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Long moduleId);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKeyWithBLOBs(Module record);

    int updateByPrimaryKey(Module record);
       
    /**
     * 查询工具对应的维度
     * @param toolsid
     * @return 
     */
    List<Module> queryTModulesByPro(List<Long> toolsid);
    
    /**
     * 查询方案对应的维度
     * @param sidList
     * @return
     */
    List<Module> querySModulesByPro(List<Long> sidList);
    
    /**
     * 查询产品的维度级别,并且从按照自然顺序排序
     * @param ids
     * @return
     */
    List<String> queryModTypeOfPro(List<Long> ids);
    
    /**
     * 按照维度类型从指定维度中选取符合要求的维度
     * @param ids
     * @param moduleType
     * @return
     */
    List<Long> queryModByType(@Param("ids")List<Long> ids,@Param("moduleType")String moduleType);
    
    /**
     * 查询方案对应的工具下的维度
     * @param id
     * @return
     */
    List<Module> querySolMod(List<Long> id);
    
    /**
     * 根据工具id查询工具下的所有维度
     * @param toolsId
     * @return 单一工具对应的维度
     */
    List<Module> quertModulesByTool(Long toolsId);
    
}