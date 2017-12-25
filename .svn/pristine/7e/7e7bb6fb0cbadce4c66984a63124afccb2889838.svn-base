package cn.com.zhiding.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 解决线程中不能使用spring注入问题
 * @author gaoqj
 *
 */
public class SpringContextUtil implements ApplicationContextAware{
	
	public static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		SpringContextUtil.context = arg0;
	}
	
	/**
	 * 获取spring注入的bean
	 * @param beanName
	 * @return
	 */
	public static Object getSpringBean(String beanName){
		if(context == null){
			return null;
		}
		return context.getBean(beanName);
	}
	
	

}
