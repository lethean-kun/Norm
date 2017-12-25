package cn.com.zhiding.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 菜单切换
 */

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("menu")
public class MenuController {
	private static Logger log = Logger.getLogger(MenuController.class);
	
	@RequestMapping("/switch/{menu}")
	public String menuSwitch(HttpServletRequest request,HttpServletResponse response,@PathVariable int menu){	
	/*	String menu = request.getParameter("menu");*/
		String menuName = "";
		switch (menu){
			case 1:   //常模列表
				menuName = "norm_list_page";
				break;
			case 2:   //产品负责页面
				menuName = "product_response_page";
				break;
			case 3:   //常模添加页面
				menuName = "norm_add_page";
				break;
			case 4:   //常模更新页面
				menuName = "norm_update_page";
				break;
			case 5:   //常模明细
				menuName = "norm_detail_page";
				break;
			case 6:   //导入数据
				menuName = "import_evaluationData_page";
				break;	
			case 100:   //导入数据
				menuName = "norm_list_page2";
				break;
			default:  
				menuName = "login";
		}
		log.info("切换菜单："+menuName+".jsp");
		return menuName;
		
	}

}
