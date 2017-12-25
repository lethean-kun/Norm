package cn.com.zhiding.norm.mapper;

import java.util.List;

import cn.com.zhiding.norm.entity.NormDetailed;

public interface NormDetailedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NormDetailed record);

    int insertSelective(NormDetailed record);

    NormDetailed selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NormDetailed record);

    int updateByPrimaryKey(NormDetailed record);
    
    
    /**
     * 根据常模id，查询常模明细表
     * @param normId
     * @return
     */
    List<NormDetailed> queryNormDetailedByNormId(Long normId);
}