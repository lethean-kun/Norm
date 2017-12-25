package cn.com.zhiding.data;

/**
 * 根据类型切换数据源
 * @author gaoqj
 *
 */
public class DataSourceSwitch {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDataSourceType(String dataSourceType){
		contextHolder.set(dataSourceType);
	}
	public static String getDataSourceType(){
		return (String)contextHolder.get();
	}
	public static void rmDataSourceType(){
		contextHolder.remove();
	}
}
