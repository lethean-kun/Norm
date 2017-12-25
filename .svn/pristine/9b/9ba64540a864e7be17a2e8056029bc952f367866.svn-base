package cn.com.zhiding.util;

public class RegexUtil {
	
	private static final String EMAILREGEX="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	
	/**
	 * 校验是否是邮箱
	 * @param email 邮箱
	 * @return 是 true，不是false
	 */
	public static boolean checkEmail(String email){
		return email.matches(EMAILREGEX);
	}
	
	public static void main(String[] args){
		String email = "gao";
		System.out.println(checkEmail(email));
	}

}
