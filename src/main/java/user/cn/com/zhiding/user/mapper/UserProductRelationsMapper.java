package cn.com.zhiding.user.mapper;

import java.util.List;

import cn.com.zhiding.user.entity.UserProductRelations;

public interface UserProductRelationsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserProductRelations record);

    int insertSelective(UserProductRelations record);

    UserProductRelations selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserProductRelations record);

    int updateByPrimaryKey(UserProductRelations record);
    

   /**
    * 获取用户的产品
    * @param userId
    * @return
    */
   List<UserProductRelations> queryProsByUserid(Long userId);
}