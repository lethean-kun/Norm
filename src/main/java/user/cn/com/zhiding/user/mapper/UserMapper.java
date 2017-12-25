package cn.com.zhiding.user.mapper;

import cn.com.zhiding.user.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 通过登录名登录
     * @param loginName 登录名
     * @param password 密码
     * @return 用户对象
     */
     public User loginNameLogin(String loginName,String password);
    
    /**
     * 通过邮箱登录
     * @param email 邮箱
     * @param password 密码
     * @return 用户对象
     */
     public User emailLogin(String email,String password);
    
    /**
     * 邮箱唯一性校验
     * @param email
     * @return 返回绑定邮箱的用户数量
     */
     public int emailOnly(String email);
}