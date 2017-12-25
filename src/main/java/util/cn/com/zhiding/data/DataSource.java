package cn.com.zhiding.data;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  

/**
 * 配置于applicationContext中，线程局部变量ThreadLocal contextHolder保存当前需要的数据类型
 * 当DataSourceSwitch.setDataSourceType(DataSourceInstances.XXX)保存当前需要的数据源类型的时候
 * DataSource会从当前线程变量的数据源类型，从而决定使用何种数据源
 * @author gaoqj
 *
 */
public class DataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceSwitch.getDataSourceType();
	}

}
