package cn.com.zhiding.norm.mapper;

import java.util.List;
import java.util.Map;

import cn.com.zhiding.norm.entity.Norm;

public interface NormMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Norm record);

    int insertSelective(Norm record);

    Norm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Norm record);

    int updateByPrimaryKey(Norm record);    
    
     /**
       *
       * @param  map 模糊查询 里面包括用户id，常模名称，状态
       * @return 常模列表
       */
     List<Norm> selectByUserId(Map<String,Object> map);
     
     /**
      * 查询集合数量
      * @param map
      * @return
      */
     Integer countNormList(Map<String,Object> map);

      /**
       *
       * @param id 常模id
       * @return 带常模明细list的常模
       */
     Norm selectByPrimaryKeyOut(Long id);     
     
     /**
      * 根据常模版本查询，用于检测常模版本唯一
      * @param version 常模版本
      * @param proid 产品id
      * @return
      */
     List<Norm> selectByVersion(String version,Long proId);

    /**
     * 获取需要发送邮件的人员
     * @return
     */
    List<Norm> getNeedSent();

  	
}