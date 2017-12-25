package cn.com.zhiding.user.service;


import cn.com.zhiding.user.entity.User;

public interface ILoginService {
	
	/**
	 * 邮箱登录
	 * @param email
	 * @param password
	 * @return 登录对象
	 */
	public User emailLogin(String email,String password);
	
	/**
	 * 用户名登录
	 * @param loginName
	 * @param password
	 * @return 登录对象
	 */
	public User loginNameLogin(String loginName,String password);
	
	/**
	 * 校验邮箱的唯一性
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email);
	
	/**
	 * 更新用户最后一次登录的日期
	 * @param date
	 * @return
	 */
	public boolean loginDate(User user);

	
	
}
