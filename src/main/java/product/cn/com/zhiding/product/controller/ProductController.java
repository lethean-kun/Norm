package cn.com.zhiding.product.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.zhiding.norm.entity.Module;
import cn.com.zhiding.product.entity.ProductType;
import cn.com.zhiding.product.service.ProductService;
import cn.com.zhiding.user.entity.User;
import cn.com.zhiding.user.entity.UserProductRelations;
import cn.com.zhiding.util.Result;

@Controller
@RequestMapping("product")
public class ProductController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ProductService pService;
	
	@RequestMapping("/allType")
	@ResponseBody
	public Result getAllType(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		List<ProductType> ptList = pService.findAllProductType();
		result.setData(ptList);
		result.setStatus(Result.SUCCESS);
		return result;
	}
	
	@RequestMapping("userPros")
	@ResponseBody
	public Result getUserPros(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute("u");
		if(user == null){
			result.setStatus(Result.NOSESSION);
			return result;
		}
		List<UserProductRelations> uPros = pService.findUserPros(user.getId());
		result.setData(uPros);
		result.setMessage(Result.SUCCESSMSG);
		result.setStatus(Result.SUCCESS);
		return result;
	}
	
	@RequestMapping("/proModules")
	@ResponseBody
	public Result getProModules(HttpServletRequest request,HttpServletResponse response){
		log.info("进入获取产品维度的方法");
		Long start = System.currentTimeMillis();
		Result result = new Result();
		Long proId = Long.valueOf(request.getParameter("proid"));
		Map<String,Set<Module>> moduleMap = pService.findProModules(proId);
		result.setData(moduleMap);
		result.setStatus(Result.SUCCESS);
		Long end = System.currentTimeMillis();
		log.info("ProductController.getProModules()耗时:"+(end-start)+"ms");
		return result;
	}

}
