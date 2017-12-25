package cn.com.zhiding.util;

/**
 * 分页工具类
 * @author gaoqj
 *
 */
public class PageUtil {
	//默认开始位置
	public static final int DEFAULTINDEXSTART = 0;
	
	//默认pageSize
	public static final int DEFAULTPAGESIZE = 10;
	
	
	//起始位置
	private int indexStart;
	//每页的多少条记录
	private int pageSize;
	//一共多少页
	private int totalPage;
	//一共多少条数据
	private int totalCount;
	//第几页
	private int pageNo;
	
	public int getIndexStart() {
		return indexStart;
	}
	
	public void setIndexStart(int indexStart) {
		this.indexStart = indexStart;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	
	

}
