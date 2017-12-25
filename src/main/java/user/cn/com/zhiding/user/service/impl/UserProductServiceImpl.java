package cn.com.zhiding.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.zhiding.user.bean.UserBean;
import cn.com.zhiding.user.entity.UserProductRelations;
import cn.com.zhiding.user.mapper.UserProductRelationsMapper;
import cn.com.zhiding.user.service.UserProductService;

@Service
public class UserProductServiceImpl implements UserProductService {

	@Resource
	UserProductRelationsMapper uprMapper;
	@Override
	public List<UserBean> getUserProduct(Long userId) {
		List<UserBean> ubList = new ArrayList<UserBean>();
		List<UserProductRelations> uprList = uprMapper.queryProsByUserid(userId);
		for(UserProductRelations upr:uprList){
			if(upr.getProductType() == 1){//工具
				
			}else if(upr.getProductType() == 2){}
		}
		return ubList;
	}

}
