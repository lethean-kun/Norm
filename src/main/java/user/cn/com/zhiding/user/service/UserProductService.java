package cn.com.zhiding.user.service;

import java.util.List;

import cn.com.zhiding.user.bean.UserBean;

public interface UserProductService {

	public List<UserBean> getUserProduct(Long userId);
}
