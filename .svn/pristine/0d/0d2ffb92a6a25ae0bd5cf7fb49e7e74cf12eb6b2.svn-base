package cn.com.zhiding.norm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.zhiding.norm.entity.ActivityPersons;
import cn.com.zhiding.norm.service.ActivityPersonsService;
import cn.com.zhiding.util.Result;
import cn.com.zhiding.util.StringsUtil;


/**
 * @author gaoqj
 */
@Controller
@RequestMapping("ap")
public class ActivityPersonController {
	
	private Logger log = Logger.getLogger(ActivityPersonController.class);
	
	@Autowired
	ActivityPersonsService apService;
	@RequestMapping("/getAp")
	@ResponseBody
	private Result getActivityPerson(HttpServletRequest request ,HttpServletResponse response){
		log.info("进入方法："+this.getClass());
		Result result = new Result();
		String ids = request.getParameter("ids");
		if(!StringsUtil.isBlank(ids)){
			result.setMessage(Result.PARAMERROR);
			result.setStatus(Result.FAILED);
			return result;
		}
		List<Long> idlist = new ArrayList<Long>();
		if(ids.contains(",")){
			String [] idss = ids.split(",");
			for(String id :idss){
				idlist.add(Long.valueOf(id));
			}
		}else{
			idlist.add(Long.valueOf(ids));
		}
		List<ActivityPersons> aplist = apService.getApList(idlist);
		result.setStatus(Result.SUCCESS);
		result.setMessage(Result.SUCCESSMSG);
		result.setData(aplist);
		return result;
	}

}
