package cn.com.zhiding.user.bean;

import java.util.List;

import cn.com.zhiding.norm.entity.ModuleRelations;

/**
 * 维度工具类
 * @author gaoqj
 *
 */
public class ModuleBean {
	
	private Long moduleId;//维度id
	
	private String moduleType;//维度类型
	
	private List<Long> sonIds;//子维度集合
	
	private int sort;//计算顺序

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public List<Long> getSonIds() {
		return sonIds;
	}

	public void setSonIds(List<Long> sonIds) {
		this.sonIds = sonIds;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	

}
