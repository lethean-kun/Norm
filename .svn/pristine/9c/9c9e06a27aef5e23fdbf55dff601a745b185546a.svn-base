package cn.com.zhiding.user.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import cn.com.zhiding.user.entity.User;
import cn.com.zhiding.user.mapper.UserMapper;
import cn.com.zhiding.user.service.ILoginService;
import cn.com.zhiding.util.MD5Util;

@Service
public class LoginServiceImpl implements ILoginService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public User emailLogin(String email, String password) {
		return userMapper.emailLogin(email, MD5Util.getEncryption(password));
	}

	@Override
	public User loginNameLogin(String loginName, String password) {
		// TODO Auto-generated method stub
		return userMapper.loginNameLogin(loginName, MD5Util.getEncryption(password));
	}

	@Override
	public boolean checkEmail(String email) {
		int count = userMapper.emailOnly(email);
		if(count == 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean loginDate(User user) {
		userMapper.updateByPrimaryKeySelective(user);
		return false;
	}



}
