package cn.com.zhiding.util;

public class Result {
	
	//参数错误
	public static final String PARAMERROR = "参数错误!";
	//未查询到数据
	public static final String NULL = "未查询到数据!";
	//未知错误
	public static final String UNKNOWNERROR = "未知错误！";
	
	public static final String LOGINERROR = "用户名或者密码错误！";
	
	public static final String SUCCESSMSG = "成功！";
	
	public static final String UPLOADFAILED = "上传失败！";
	
	public static final String INVALIDSESSION = "Session失效！请重新登录";
	//成功
	public static final int SUCCESS = 0;
	//失败
	public static final int FAILED = -1;
	//Session 失效
	public static final int NOSESSION = 1;
	
	public static final int DEFAULTPAGESIZE = Integer.valueOf(PropertiesUtil.getUtilValue("defaultpagesize"));
	
	public static final int DEFAULTPAGENO = 1;
	//消息
	private String message;
	//状态
	private int status;
	//数据
	private Object data;
	//页码数量
	private Integer pageNo;
	//每页数据量
	private Integer pageSize;
	//数据量
	private Integer dataCount;
	//当前页
	private Integer currentPage;
	


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(pageSize == null || pageSize == 0){
			this.pageSize = DEFAULTPAGESIZE;
		}
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * 求分页数量
	 * @param count 数据量
	 * @return
	 */
	public static int getPageSize(Integer count){
		int pageno = count/DEFAULTPAGESIZE;
		if(count%DEFAULTPAGESIZE >0 ){
			pageno += 1;
		}
		return pageno;
	}
	
	/**************适用于layer页面加载*******************/
	
	private int code;//等价于status
	
	private String msg;//等价于message
	
	private int count;//等价于dataCount



	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
	
}
