package cn.com.zhiding.norm.controller;


import cn.com.zhiding.norm.entity.Norm;
import cn.com.zhiding.norm.entity.NormDetailed;
import cn.com.zhiding.norm.service.NormService;
import cn.com.zhiding.user.entity.User;
import cn.com.zhiding.util.ExcelUtil;
import cn.com.zhiding.util.Result;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * @author zzk
 */
@Controller
@RequestMapping("/norm")
public class NormController {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private NormService normService;

    /**
     * 通过产品类型获取常模列表
     * @param request
     * @param normname 模糊查询常模名称
     * @param status 状态
     *
     * @return
     */
    @RequestMapping("/getNormList")
    @ResponseBody
    public Result getNormList(HttpServletRequest request,String normname,Integer status,Integer page){

        Result result = new Result();
        User user = (User) request.getSession().getAttribute("u");
        Map<String,Object> map = new HashMap<>();
        
        if(page == null || page == 0){
        	page = Result.DEFAULTPAGENO;
        }
        map.put("userId",user.getId());
        map.put("normname",normname);
        map.put("status",status);
        map.put("start", (page-1)*Result.DEFAULTPAGESIZE);
        map.put("pagesize", Result.DEFAULTPAGESIZE);
        List<Norm> normList = normService.getNormList(map);
        Integer count = normService.countNormList(map);
        if(normList != null && normList.size() != 0){
            result.setStatus(Result.SUCCESS);
            result.setData(normList);
            result.setCurrentPage(page);
            result.setPageNo(Result.getPageSize(count));
            result.setDataCount(count);
        }else{
            result.setStatus(Result.FAILED);
            result.setMessage(Result.NULL);
        }
        return result;
    }
    
    /**
     * 通过产品类型获取常模列表
     * @param request
     * @param normname 模糊查询常模名称
     * @param status 状态
     *
     * @return
     */
    @RequestMapping("/getNormListLayer")
    @ResponseBody
    public Result getNormListLayer(HttpServletRequest request,String normname,Integer status,Integer page,Integer limit){

        Result result = new Result();
        User user = (User) request.getSession().getAttribute("u");
        Map<String,Object> map = new HashMap<>();
        
        if(page == null || page == 0){
        	page = Result.DEFAULTPAGENO;
        }
        map.put("userId",user.getId());
        map.put("normname",normname);
        map.put("status",status);
        map.put("start", (page-1)*Result.DEFAULTPAGESIZE);
        map.put("pagesize", limit);
        List<Norm> normList = normService.getNormList(map);
        Integer count = normService.countNormList(map);
        if(normList != null && normList.size() != 0){
            result.setCode(Result.SUCCESS);
            result.setData(normList);
            result.setCount(count);
            result.setMsg(Result.SUCCESSMSG);
        }else{
            result.setCode(Result.FAILED);
            result.setMsg(Result.NULL);
        }
        return result;
    }

    /**
     * 获取常模明细 包含所有明细信息
     * @param request
     * @param response
     * @param id 常模ID
     * @return
     */
    @RequestMapping(value = "/getNormDetailed/{id}",method = RequestMethod.GET)
    public String getNormDetailed(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @PathVariable Long id) {
        log.info("进入方获取常模明细");
        Norm norm = normService.selectByPrimaryKeyOut(id);
        Map<String,List<NormDetailed>> ndMap = normService.getNormDetailedMap(norm);
        request.setAttribute("Norm",norm);
        request.setAttribute("normDetailed", ndMap);
        return "norm_detail_page";
    }

    /**
     * 跳转导入数据页面
     * @param request
     * @param response
     * @param version
     * @param id
     * @return
     */
    @RequestMapping("/rtInPage/{id}")
    public String rtInPage(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable  Long id){

        request.setAttribute("id", id);
        return "import_evaluationData_page";
    }

    /**
     * 导入数据
     * @param request
     * @param response
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importData/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result importData(HttpServletRequest request,
                             HttpServletResponse response,
                             MultipartFile file,
                             @PathVariable  Long id) throws Exception {
        log.info("导入数据处理");
    	Result result = new Result();
        Norm norm = normService.selectByPrimaryKeyOut(id);
        String normVersion = null;
        Long userId = null;
        if(norm!=null) {
            normVersion = norm.getVersion();
            userId = norm.getUserId();
            if (userId == null) {
                log.info("userId为空");
            }
        }
        //获取从浏览器上传文件，以用户id+normVersion为key，上传数据List为value
        int status = ExcelUtil.readXls(file,userId,normVersion);
        //取出数据
        log.info(userId+"&&"+normVersion);

        if(status == 1){
            List<Long> idsList = ExcelUtil.IdsMap.get(userId+"&&"+normVersion);
            norm.setSampleSize(idsList.size());
            normService.updateNorm(norm);
        	result.setStatus(Result.SUCCESS);
        	result.setMessage(Result.SUCCESSMSG);
        	result.setData(idsList.size());
        }else if(status == 0){
            norm.setSampleSize(0);
            normService.updateNorm(norm);
        	result.setStatus(Result.FAILED);
        	result.setMessage("上传文件为空！");
        }else {
            norm.setSampleSize(0);
            normService.updateNorm(norm);
            result.setStatus(Result.FAILED);
            result.setMessage("文件第"+status+"格式转化出错!");
        }
        return result;
    }

    /**
     * 明细导出
     * @param request
     * @param response
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("/exportNorm/{id}")
    @ResponseBody
    public void exportNorm(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable Long id) throws IOException {
        log.info("进入导出常模明细方法");
        normService.exportNorm(id,response);
    }

    /**
     * 模板下载
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadTemplate")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,
                                               HttpServletResponse response
                                               ) throws IOException {

            String realPath = request.getServletContext().getRealPath("upload/");
            String fileName = "导入数据模板.xls";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                return download(fileName, file);
            }

        return null;
    }

    /**
     * 文档下载
     * @param fileName
     * @param file
     * @return
     * @throws IOException
     */
    public ResponseEntity<byte[]> download(String fileName, File file) throws IOException{
        String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        //返回实体中，第一个参数为返回的数据，相当于out.write(map.toString())，第三参数是状态码，可以设置返回404，上述设置等价于返回200.
        return new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }
    
    /**
     * 常模添加
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addnorm")
    @ResponseBody
    public Result addNorm(HttpServletRequest request,HttpServletResponse response){
    	log.info("常模添加方法addnorm()>>>>>>");
    	Result result = new Result();
    	User user = (User)request.getSession().getAttribute("u");
    	if(user == null){
    		result.setStatus(Result.NOSESSION);
    		return result;
    	}
    	log.info("登录人员信息：id:"+user.getId()+",名字："+user.getName());
    	Long userid = user.getId();
    	String normName = request.getParameter("normName");
    	String version= request.getParameter("version");
    	String description= request.getParameter("description");
    	String moduleIds= request.getParameter("moduleIds");
    	Long productId= Long.valueOf(request.getParameter("productName"));
    	if(!normService.checkNormVersion(null,productId, version)){//检验常模版本是否重复
    		result.setStatus(Result.FAILED);
    		result.setMessage("常模版本已经被使用,请重新输入！");
    		return result;
    	}
    	Long insert = normService.insertNorm(userid,normName, version, description, moduleIds, productId);
    	if(insert == null){
    		result.setStatus(Result.FAILED);
    		result.setMessage(Result.UNKNOWNERROR);
    		return result;
    	}
    	result.setStatus(Result.SUCCESS);
		result.setData(insert);
    	return result;
    }

    /**
     * 常模更新页面跳转
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/goupdatenorm/{id}")
    public String goUpdateNorm(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id){
    	Norm norm = normService.selectByPrimaryKeyOut(id);
    	String moduleName = "";
    	for(NormDetailed nd :norm.getNdList()){
    		moduleName += nd.getModule().getModulename()+",";
    	}
    	moduleName = moduleName.substring(0, moduleName.length()-1);
    	request.setAttribute("normId", norm.getId());
    	request.setAttribute("normName", norm.getNormName());
    	request.setAttribute("version",norm.getVersion());
    	request.setAttribute("description",norm.getDescription());
    	request.setAttribute("proid", norm.getProductId());
    	request.setAttribute("moduleName", moduleName);
    	return "norm_update_page";
    }
    
    /**
     * 常模更新--只能更新normname,version,description
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/doupdatenorm/{id}")
    @ResponseBody
    public Result doUpdateNorm(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id){
    	Result result = new Result();
    	String normName = request.getParameter("normName");
    	String version = request.getParameter("version");
    	String description = request.getParameter("description");
    	Long proId = Long.valueOf(request.getParameter("proid"));
    	if(!normService.checkNormVersion(id,proId, version)){//检验常模版本是否重复
    		result.setStatus(Result.FAILED);
    		result.setMessage("常模版本已经被使用,请重新输入！");
    		return result;
    	}
    	int modify = normService.modifyNorm(id, normName, version, description);
    	if(modify != 0){
    		result.setStatus(Result.FAILED);
    		result.setMessage(Result.UNKNOWNERROR);
    		return result;
    	}
    	result.setStatus(Result.SUCCESS);
    	return result;
    }

    /**
     * 删除常模
     * @param id
     * @return
     */
    @RequestMapping("/deleteNorm/{id}")
    @ResponseBody
    public Result deleteNorm(@PathVariable Long id){
        Result result = new Result();
        if (normService.deleteById(id) == 0){
            result.setStatus(0);
            result.setMessage("删除成功！");
            return result;
        }
        result.setStatus(-1);
        result.setMessage("删除失败！");
        return result;
    }
 
    /**
     * 常模计算
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/figureNorm/{id}")
    @ResponseBody
    public Result figureNorm(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id){
    	Result result = new Result();
    	int reInt =normService.figureNorm(id);
    	if (reInt == 0) {
			result.setStatus(Result.SUCCESS);
		}else{
			result.setStatus(Result.FAILED);
			if(reInt == 1){
				result.setMessage("没有测评数据！");
			}else if(reInt == 2){
				result.setMessage(Result.PARAMERROR);
			}else{
				result.setMessage(Result.UNKNOWNERROR);
			}
		}
    	return result;
    }

}
