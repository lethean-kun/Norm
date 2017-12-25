package cn.com.zhiding.norm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.zhiding.data.DataSourceInstances;
import cn.com.zhiding.data.DataSourceSwitch;
import cn.com.zhiding.norm.entity.ActivityPersons;
import cn.com.zhiding.norm.mapper.ActivityPersonsMapper;
import cn.com.zhiding.norm.service.ActivityPersonsService;

@Service
public class ActivityPersonsServiceImpl implements ActivityPersonsService {
	
	private Logger log = Logger.getLogger(this.getClass()); 
	@Resource
	ActivityPersonsMapper apMapper;
	
	@Override
	public List<ActivityPersons> getApList(List<Long> ids) {
		List<ActivityPersons> apList = new ArrayList<ActivityPersons>();//存放结果
		List<Long> slaveIds = new ArrayList<Long>();
		for(Long id : ids){
			ActivityPersons ap = apMapper.selectByPrimaryKey(id);
			if(ap == null){
				slaveIds.add(id);
			}else{
				apList.add(ap);
			}
		}
		
		if(slaveIds.size() != 0){//如果主库没有查询到数据，切换到分库
			log.info("切换到数据库："+DataSourceInstances.SLAVE);
			DataSourceSwitch.setDataSourceType(DataSourceInstances.SLAVE);
			for(long id : slaveIds){
				ActivityPersons ap = apMapper.selectByPrimaryKey(id);
				if(ap == null){
					System.out.println(id+"这个apid不存在！！");
				}else{
					apList.add(ap);
				}
			}
		}
		return apList;
	}

}
