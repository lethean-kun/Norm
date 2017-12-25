package cn.com.zhiding.norm.service.impl;

import static cn.com.zhiding.util.FigureTerm.getFTLevel;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import cn.com.zhiding.data.DataSourceInstances;
import cn.com.zhiding.data.DataSourceSwitch;
import cn.com.zhiding.norm.entity.ExamResult;
import cn.com.zhiding.norm.entity.Module;
import cn.com.zhiding.norm.entity.ModuleRelations;
import cn.com.zhiding.norm.entity.Norm;
import cn.com.zhiding.norm.entity.NormDetailed;
import cn.com.zhiding.norm.entity.SolutionsTools;
import cn.com.zhiding.norm.entity.Tools;
import cn.com.zhiding.norm.mapper.ExamAdvanceResultMapper;
import cn.com.zhiding.norm.mapper.ExamResultMapper;
import cn.com.zhiding.norm.mapper.ModuleMapper;
import cn.com.zhiding.norm.mapper.ModuleRelationsMapper;
import cn.com.zhiding.norm.mapper.NormDetailedMapper;
import cn.com.zhiding.norm.mapper.NormMapper;
import cn.com.zhiding.norm.mapper.SolutionsToolsMapper;
import cn.com.zhiding.norm.mapper.ToolsMapper;
import cn.com.zhiding.norm.service.NormService;
import cn.com.zhiding.product.entity.Product;
import cn.com.zhiding.product.mapper.ProductMapper;
import cn.com.zhiding.user.bean.ModuleBean;
import cn.com.zhiding.user.bean.NormBean;
import cn.com.zhiding.util.ComputeScore;
import cn.com.zhiding.util.ExcelUtil;
import cn.com.zhiding.util.FileUtil;
import cn.com.zhiding.util.ListUtil;
import cn.com.zhiding.util.MapUtil;
import cn.com.zhiding.util.NumFormat;
import cn.com.zhiding.util.SpringContextUtil;
import cn.com.zhiding.util.StringsUtil;


@Service
public class NormServiceImpl implements NormService{

	Logger log = Logger.getLogger(this.getClass());
    @Resource
    private NormMapper normMapper;
    
    @Resource
    private NormDetailedMapper ndMapper;
    
    @Resource
    private ExamResultMapper erMapper;

    @Resource
    private ProductMapper productMapper;
    
    @Resource
    private ExamAdvanceResultMapper earMapper;
    
    @Resource
    private ModuleMapper modMapper;   
    
    @Resource
    private ToolsMapper toolsMapper;

    @Resource
    private SolutionsToolsMapper stMapper;
    
    @Resource
    private ModuleRelationsMapper mrMapper;
    
    @Override
    public List<Norm> getNormList(Map<String, Object> map) {
        List<Norm> normList = normMapper.selectByUserId(map);
        return normList;
    }
    
	@Override
	public Integer countNormList(Map<String, Object> map) {
		return normMapper.countNormList(map);
	}

    @Override
    public int figureNorm(Long normId) {
    	try{
	    	//获取要计算的常模数据
	    	Norm norm = normMapper.selectByPrimaryKey(normId);
	    	if(norm == null){
	    		log.info("常模id:"+normId+"错误！");
	    		return 2;
	    	}
	    	Product pro = productMapper.selectByPrimaryKey(norm.getProductId());
	    	String version = norm.getVersion();//常模版本
	    	//取出对应的测评者数据
	    	List<Long> idsList =  ExcelUtil.IdsMap.get(norm.getUserId()+"&&"+version);
	        //取出对应的产品
	    	if(idsList == null || idsList.size() == 0){
	    		log.info("用户："+norm.getUserId()+"，版本为:"+version+"的常模,没有测评数据！");
	    		return 1;
	    	}
	    	//java 8 之前
	   /* 	new Thread(new Runnable(){
				@Override
				public void run() {
					figureThread(norm,pro,idsList);
				}
				}).start();*/
	    	//java 8之后，lambda表达式
	    	new Thread(()->figureThread(norm,pro,idsList)).start();
	    	norm.setStatus(1);
	    	norm.setStartTime(new Date());
	    	normMapper.updateByPrimaryKeySelective(norm);
	    	return 0;
    	}catch(Exception e){
    		e.printStackTrace();
    		return 3;
    	}
    }
    
    /**
     * 常模计算线程
     * @param norm 常模对象
     * @param pro	产品对象
     * @param idsList	测评者id集合
     */
    public void figureThread(Norm norm,Product pro,List<Long> idsList){
    	log.info("进入计算常模线程>>>>>>>>>>>>");
    	long start = System.currentTimeMillis();

    	//获取spring注入的bean
    	SqlSessionFactory sessionFactory = (SqlSessionFactory)SpringContextUtil.getSpringBean("sqlSessionFactory");
    	SqlSession sessionBatch = sessionFactory.openSession(ExecutorType.BATCH,false);

    	//工具id集合
    	List<Long> toolsIdsList = new ArrayList<Long>();
    	//方案id集合
    	List<Long> solutionsIdsList = new ArrayList<Long>();
    	//产品下面的维度
    	Set<Long> modSet = new HashSet<Long>(); 
    	//一级维度的对应的测评结果
    	List<ExamResult> erList = null;
    	//维度原始分集合
    	Map<Long,List<ExamResult>> modOriginalScore = new HashMap<Long,List<ExamResult>>();
    	//工具和维度的关系
    	Map<Long,List<Module>> toolsModMap = new HashMap<Long,List<Module>>();
    	//工具和工具类型的对应关系
    	Map<Long,String> tTypeMap = new HashMap<>();
    	//key=维度id，value=<子工具id,权重>
    	Map<Long,Map<Long,Double>> weightMap = new HashMap<>();	
    	//高阶维度计算
    	List<ModuleBean> mbList = new ArrayList<ModuleBean>();
    	//存放常模的计算信息
    	Map<Long,List<NormBean>> moduleNorm = new HashMap<Long,List<NormBean>>();
    	//一级维度集合
    	List<Module> oneLevelModule = new ArrayList<Module>();
    	//高阶维度集合
    	List<Module> highLevelModule = new ArrayList<Module>();
    	String result ="";//用于日志

    	//更新的常模
    	Norm record = new Norm(norm.getId(),norm.getUserId(),norm.getProductId(),norm.getNormName(),
    			norm.getVersion(),norm.getSampleSize(),norm.getDescription(),null,norm.getCreateDate(),
    			norm.getStatus(),new Date(),null,null,0);

    	try{
        	//获取产品对应的工具和方案
        	Map<String,List<Long>> listMap = StringsUtil.getTOrSId(pro);
	    	for(String key:listMap.keySet()){
	    		if("t".equals(key)){//工具
	    			toolsIdsList = listMap.get(key);
	    		}else if("s".equals(key)){//方案
	    			solutionsIdsList = listMap.get(key);
	    		}else{
	    			return;
	    		}
	    	}
	    	//取出方案对应的工具---为了方便接下来查询原始分
	    	List<SolutionsTools> stList = new ArrayList<SolutionsTools>();
	    	if(solutionsIdsList.size() != 0){
	    		stList = stMapper.queryBySolutionId(solutionsIdsList);
	    		for(SolutionsTools st:stList){
	    			toolsIdsList.add(st.getToolId());
	    		}
	    	}
	    	
	    	List<Tools> tsList =  toolsMapper.queryToolsType(toolsIdsList);
	    	for(int i = 0,j = tsList.size();i<j;i++){
	    		Tools tools = tsList.get(i);
	    		tTypeMap.put(tools.getToolId(), tools.getTooltype());
	    	}
	    	//取产品下面的所有维度，并且去重
	    	if(toolsIdsList.size()!= 0){
	    		List<Module> modsTools = modMapper.queryTModulesByPro(toolsIdsList);
	    		for(int i=0,j=modsTools.size();i<j;i++){
	    			modSet.add(modsTools.get(i).getModuleId());
	    		}
	    	}
	    	if(solutionsIdsList.size() != 0){
	    		List<Module> modsSuls = modMapper.querySModulesByPro(solutionsIdsList);
	        	for(int i=0,j=modsSuls.size();i<j;i++){
	        		modSet.add(modsSuls.get(i).getModuleId());
	        	}
	    	}   	
	    	List<Module> oneModList = null;
	    	//工具下面的维度
	    	List<Module> modList = new ArrayList<Module>();
	    	log.info("取一级维度原始分>>>>>>>>>>>>");
	    	//数据库取测评数据
	    	for(int a =0,b=toolsIdsList.size();a<b;a++){
	    		Long toolId = toolsIdsList.get(a);
	    		modList = modMapper.quertModulesByTool(toolId);
	    		toolsModMap.put(toolId, modList);
	    		int oneLevelModuleCount = 0;//某工具下一级维度的数量
	    		oneModList = new ArrayList<>();
	    		//把一级维度和高阶维度分开
	    		for(int i = 0,j = modList.size();i < j;i++){
	    			if("1".equals(modList.get(i).getModuletype())){
	    				oneLevelModule.add(modList.get(i));
	    				oneModList.add(modList.get(i));
	    				oneLevelModuleCount++;
	    			}else if(!"6".equals(modList.get(i).getModuletype())){
	    				highLevelModule.add(modList.get(i));
	    			}else{
	    				log.info("测谎维度："+modList.get(i).getModuleId());
	    			}
	    		}
	    		/**
	    		 * 查询工具下所有人的测评成绩
	    		 * 如果在主库中查询到的成绩的测评人和导入的测评人数量相等，就不去从库查询
	    		 * 测评人员成绩按照一级维度分组
	    		 */
	    		List<ExamResult> erListMaster = erMapper.queryExamResulByApIds(idsList, toolId);
	    		if(erListMaster.size()/oneLevelModuleCount < idsList.size()){
	    			DataSourceSwitch.setDataSourceType(DataSourceInstances.SLAVE);//切库
	    			log.info("切换数据库："+DataSourceInstances.SLAVE);
	    			List<ExamResult> erListSlave = erMapper.queryExamResulByApIds(idsList, toolId);
	    			erListMaster.addAll(erListSlave);
	    			//从从库取完数据,切回主库
		    		DataSourceSwitch.setDataSourceType(DataSourceInstances.MASTER);
		    		log.info("切换数据库："+DataSourceInstances.MASTER);
	    		}
	    		//测评人员成绩按照一级维度分组
	    		for(int m = 0,n=oneModList.size();m<n;m++){
	    			erList = new ArrayList<ExamResult>();//初始化
	    			Long moduleId = oneModList.get(m).getModuleId();
	    			for(int c = 0,d=erListMaster.size();c<d;c++){
	    				if(erListMaster.get(c).getModuleId().equals(moduleId)){
	    					erList.add(erListMaster.get(c));
	    				}
	    			}
	    			modOriginalScore.put(moduleId, erList);//保存低阶常模的原始数据
	    			log.info("维度id:"+moduleId+",测评者数量："+erList.size());
	    			erList = null;
	    		}
         	}
	    	log.info("一级维度的数量:"+modOriginalScore.keySet().size());
	    	//计算维度常模----保存维度常模信息
	    	log.info("一级维度开始计算>>>>>>>>>>>>");
	    	List<NormBean> tempList = null;
	    	List<BigDecimal> originalScoreList = null;
	    	List<NormBean> normBeanList = null;
	    	/**
	    	 * 遍历一级维度,算一级维度的常模
	    	 * 平均值：X=(O1+O2+....+On)/n
	    	 * 标准差：S=sqrt((O1-X)2+(O2-X)2+...+(On-X)2)/n)
	    	 * 标准Z分：Z=(O-X)/S
	    	 */ 
	    	for(int e = 0,f = oneLevelModule.size();e<f;e++){
	    		Long modId = oneLevelModule.get(e).getModuleId(); 
	    		originalScoreList = new ArrayList<BigDecimal>();//一级维度原始分集合    	
	    		List<ExamResult> examList = modOriginalScore.get(modId);//某维度的测评结果
	    		normBeanList = new ArrayList<NormBean>();//存放完整结果
	    		tempList = new ArrayList<NormBean>();//存放中间结果
				BigDecimal originalScore = new BigDecimal("0.0");
				BigDecimal averageScore = new BigDecimal("0.0");
				BigDecimal standardDeviation = new BigDecimal("0.0");
				NormBean bean = null;
				String toolsType = tTypeMap.get(examList.get(0).getToolId());//工具类型，用于区分s_exam_result表中存原始分的字段
				for(int m = 0 ,n=examList.size();m<n;m++){
					ExamResult er1 = examList.get(m);
	    			bean = new NormBean();
	    			bean.setApId(er1.getActivitypersonId());//APid
	    			bean.setModuleId(er1.getModuleId());//维度id
	    			bean.setToolsId(er1.getToolId());//工具id
	    			originalScore = ComputeScore.getOriginalScore(toolsType, er1);//取原始分
	    			bean.setOriginalScore(originalScore);//原始分
	    			originalScoreList.add(originalScore);
	    			tempList.add(bean);
	    			bean = null;
	    		}
	    		averageScore = ComputeScore.getAverageScoreByList(originalScoreList);//平均分
	    		standardDeviation = ComputeScore.getStandardDeviation(originalScoreList, averageScore);//标准差
	    		log.info("维度id:"+modId+",平均值："+averageScore+",标准差："+standardDeviation);
	    		BigDecimal standardZScore = new BigDecimal("0.0");
	    		for(int m = 0 ,n=tempList.size();m<n;m++){
	    			NormBean bean2 = tempList.get(m);
	    			standardZScore = ComputeScore.getStandardZScore(bean2.getOriginalScore(), averageScore, standardDeviation);
	    			bean2.setStandardZScore(standardZScore);//标准Z分
	    			bean2.setAverageScore(averageScore);//平均分
	    			bean2.setStandardDeviation(standardDeviation);//标准差
	    			normBeanList.add(bean2);
	    		}    	
	    		moduleNorm.put(modId, normBeanList);
	    		tempList = null;
	    		normBeanList = null;
	    		originalScoreList = null;
	    	}
	    	//按照apid分组的常模Map
	    	Map<Long,List<NormBean>> nbMapByAp = MapUtil.groupMapByAp(moduleNorm);
	    	
	    	log.info("取需要计算的高阶维度>>>>>>>>>>>>");
			ModuleBean mbean = null;
			Map<Long,Double> sonWeightMap = null;
			//取出需要计算的高阶维度,按照维度moduleType进行正向排序,并且标记计算顺序
			highLevelModule = ListUtil.bubbleSort(highLevelModule);

			for(int m = 0,n = highLevelModule.size();m<n;m++){
				Module mod = highLevelModule.get(m);
				List<ModuleRelations> mrsList =  mrMapper.querySonByParent(mod.getModuleId(),getModTheTools(mod.getModuleId(),toolsModMap));
				List<Long> sonIds = new ArrayList<>();
				ModuleRelations mr = null;
				sonWeightMap = new HashMap<>();
				for(int i = 0,j = mrsList.size();i < j;i++){
					mr = mrsList.get(i);
					sonIds.add(mr.getSonId());
					sonWeightMap.put(mr.getSonId(),mr.getWeight());
				}
				mbean = new ModuleBean();
				mbean.setModuleId(mod.getModuleId());
				mbean.setModuleType(mod.getModuletype());
				mbean.setSonIds(sonIds);
				mbean.setSort(m);
				mbList.add(m,mbean);
				weightMap.put(mod.getModuleId(), sonWeightMap);
				sonWeightMap = null;				
			}
			for(Long modId:weightMap.keySet()){
				Map<Long,Double> map = weightMap.get(modId);
				for(Long sonId:map.keySet()){
					System.out.println("上级维度id:"+modId+"子维度："+sonId);
				}
				
			}
	    	log.info("开始计算高阶维度>>>>>>>>>>>>>>>>>>>>>>>>>");
	    	StringBuffer str = new StringBuffer();
	    	//计算高阶维度的分数
	    	//①按照顺序取出维度
	    	/**
	    	 * 先计算出每个人高阶维度的原始分
	    	 *   方法：取出该高阶维度所有低阶维度的Z分，然后加权（Z1*w1+Z2*w2+......）/(|w1|+|w2|+......)，得到高阶维度原始分
	    	 * 得到高阶维度的原始分之后,按照一级维度求常模值的方式即可得到高阶维度常模值
	    	 */
	    	for(int i = 0,j = mbList.size();i < j;i++){
	    		ModuleBean mbean2 = mbList.get(i);
	    		log.info("高阶维度的计算顺序："+mbean2.getSort());
	    		Map<Long,Double> modWeightMap = weightMap.get(mbean2.getModuleId());//高阶维度对应的子维度已经相应的权重
	    		List<NormBean> nbeanList3 = new ArrayList<NormBean>();
    			for(Long apId : nbMapByAp.keySet()){//遍历人员
    				List<NormBean> nbeanList2 = new ArrayList<NormBean>();
    				List<NormBean> nbeanList = nbMapByAp.get(apId);//每个人对应的常模数据、合成高阶维度原始分
    				for(int m=0,n=mbean2.getSonIds().size();m<n;m++){
    					Long sonid = mbean2.getSonIds().get(m);
						nbLoop:for(int a=0,b=nbeanList.size();a<b;a++){
							NormBean bean = nbeanList.get(a);
							if(bean.getModuleId().equals(sonid)){
								nbeanList2.add(bean);
								break nbLoop;
							}
						}
	    			}
//    				System.out.println("高阶维度id:"+mbean2.getModuleId()+"，样本量："+nbeanList2.size());
	    			if(nbeanList2.size()!=0){
	    				BigDecimal sum = new BigDecimal("0.0");//加权Z分
	    				Double weight = 0.0;//维度对应的权重
	    				BigDecimal weiSum = new BigDecimal("0.0");//权重绝对值之和
	    	    		for(int m=0,n=nbeanList2.size();m<n;m++){//子维度
	    	    			NormBean nbean4 = nbeanList2.get(m);
	    	    			weight = modWeightMap.get(nbean4.getModuleId());//子维度对应的权重
	    	    			sum = sum.add(nbean4.getStandardZScore().multiply(new BigDecimal(weight)));//加权Z分
	    	    			weiSum = weiSum.add(new BigDecimal(Math.abs(weight)));//权重绝对值和
	    	    			str.append("子维度id:"+nbean4.getModuleId()+",标准Z分："+nbean4.getStandardZScore()+"\t");
	    	    		}
//	    	    		System.out.println("维度id："+mbean2.getModuleId()+"，权重之和："+sum);
	    	    		NormBean nbean4 = new NormBean();
	    	    		nbean4.setApId(apId);
	    	    		nbean4.setOriginalScore(ComputeScore.getWeightingZScoreBySum(sum, weiSum));
	    	    		str.append("apid:"+apId+",加权Z分："+ComputeScore.getWeightingZScoreBySum(sum, weiSum)+"\n");
//	    	    		System.out.println(ComputeScore.getWeightingZScoreBySum(sum, weiSum));
	    	    		nbean4.setModuleId(mbean2.getModuleId());
	    	    		nbeanList3.add(nbean4);
	    			}
    	    		nbeanList2 = null;

    			}
    			originalScoreList = new ArrayList<BigDecimal>();
    			for(int m=0,n=nbeanList3.size();m<n;m++){
    				originalScoreList.add(nbeanList3.get(m).getOriginalScore());
    			}
    			BigDecimal averageScore = new BigDecimal("0.0");
    			BigDecimal standardDeviation = new BigDecimal("0.0");
    			if(originalScoreList.size()!=0){//原始分list为null
					averageScore = ComputeScore.getAverageScoreByList(originalScoreList);
					standardDeviation = ComputeScore.getStandardDeviation(originalScoreList, averageScore);
    			}
    			log.info("维度id:"+mbList.get(i).getModuleId()+",平均值："+averageScore+",标准差："+standardDeviation);
				for(int m=0,n=nbeanList3.size();m<n;m++){
					NormBean nbean5 = nbeanList3.get(m);
					BigDecimal standardZScore = ComputeScore.getStandardZScore(nbean5.getOriginalScore(), averageScore, standardDeviation);
					nbean5.setAverageScore(averageScore);
					nbean5.setStandardDeviation(standardDeviation);
					nbean5.setStandardZScore(standardZScore);
					if(nbMapByAp.containsKey(nbean5.getApId())){
						nbMapByAp.get(nbean5.getApId()).add(nbean5);
					}else{
						nbMapByAp.put(nbean5.getApId(), new ArrayList<NormBean>());
						nbMapByAp.get(nbean5.getApId()).add(nbean5);
					}
				}
    			moduleNorm.put(mbean2.getModuleId(), nbeanList3);
    			originalScoreList = null;
	    	}
	    	FileUtil.WriteToFile(str.toString());
	    	log.info("常模计算完毕，开始保存需要计算的常模到数据库>>>>>>>>>>>>");
	    	//保存数据
	    	List<NormDetailed> ndList = ndMapper.queryNormDetailedByNormId(norm.getId());
	    	for(int m=0,n=ndList.size();m<n;m++){
	    		NormDetailed nd = ndList.get(m);
	    		NormBean nb6 = moduleNorm.get(nd.getModuleId()).get(0);
        		nd.setAverage(nb6.getAverageScore());
	    		nd.setStandardScore(nb6.getStandardDeviation());
	    		sessionBatch.update("cn.com.zhiding.norm.mapper.NormDetailedMapper.updateByPrimaryKeySelective", nd);
	    	}
	    	Date date1 = new Date();
	    	record.setFigureTime(date1);
	    	record.setStatus(2);
	    	record.setIsSend(1);
	    	record.setEndTime(date1);
	    	sessionBatch.update("cn.com.zhiding.norm.mapper.NormMapper.updateByPrimaryKeySelective", record);
    		result = "成功！";
    	}catch(Exception e){
    		Date date2 = new Date();
    		record.setFigureTime(date2);
    		record.setStatus(3);
    		record.setIsSend(1);
    		record.setEndTime(date2);
	    	sessionBatch.update("cn.com.zhiding.norm.mapper.NormMapper.updateByPrimaryKeySelective", record);
    		result = "失败！";
    		e.printStackTrace();
    	}finally{
    		//session提交、关闭
    		sessionBatch.commit();
    		sessionBatch.close();
    		//计算出结果之后，把对应的测评数据删除
    		ExcelUtil.IdsMap.remove(norm.getUserId()+"&&"+norm.getVersion());
    		//对象回收
    		weightMap = null;
    		tTypeMap = null;
    		modOriginalScore = null;
    		mbList = null;
    		moduleNorm = null;
    		long end = System.currentTimeMillis();
    		long cost = end - start;
    		log.info("计算的常模："+record.getId()+"-----计算结果："+result+"------花费时间："+cost+"ms!");
    	}
    	
    }

    /**
     * 根据工具和维度的关系,取维度对应的工具id
     * @param moduleId
     * @param toolsModMap
     * @return 工具id
     * @author gaoqj
     */
    public Long getModTheTools(Long moduleId,Map<Long,List<Module>> toolsModMap){
    	if (moduleId == null || toolsModMap == null || toolsModMap.size()==0){
    		return null;
    	}
    	for(Long toolsId : toolsModMap.keySet()){
    		if(toolsModMap.keySet().size() == 1){
    			return toolsId;
    		}
    		List<Module> modList = toolsModMap.get(toolsId);
    		for(Module mod : modList){
    			if(mod.getModuleId() == moduleId){
    				return toolsId;
    			}
    		}
    	}
    	return null;
    }

	//常模明细导出
    @Override
    public void exportNorm(Long normId, HttpServletResponse response) throws IOException {

        String[] titles = {"名称", "平均值", "标准差", "级别"};
        Norm norm = normMapper.selectByPrimaryKeyOut(normId);

        //获取常模对应产品名称
        Product product = productMapper.selectByPrimaryKey(norm.getProductId());
        String productName = product.getName();

        //向浏览器输出excel文件
        try {
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow hssfRow = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中和边框
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //下边框
            hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //左边框
            hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            //上边框
            hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //右边框
            hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            //设置黑体
            HSSFCellStyle hssfCellStyle2 = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            //粗体显示
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

            HSSFCell hssfCell;

            //第一行
            //列索引从0开始
            hssfCell = hssfRow.createCell(0);
            hssfCell.setCellValue("常模名称");

            hssfCellStyle2.setFont(font);
            hssfCell.setCellStyle(hssfCellStyle2);

            hssfCell = hssfRow.createCell(1);
            hssfCell.setCellValue(norm.getNormName());

            hssfCell = hssfRow.createCell(2);
            hssfCell.setCellValue("版本");

            hssfCellStyle2.setFont(font);
            hssfCell.setCellStyle(hssfCellStyle2);

            hssfCell = hssfRow.createCell(3);
            hssfCell.setCellValue(norm.getVersion());

            //第二行
            hssfRow = hssfSheet.createRow(1);
            //列索引从0开始
            hssfCell = hssfRow.createCell(0);
            hssfCell.setCellValue("样本量");

            hssfCellStyle2.setFont(font);
            hssfCell.setCellStyle(hssfCellStyle2);

            hssfCell = hssfRow.createCell(1);
            hssfCell.setCellValue(norm.getSampleSize());
            //列索引从0开始
            hssfCell = hssfRow.createCell(2);
            hssfCell.setCellValue("计算时间");

            hssfCellStyle2.setFont(font);
            hssfCell.setCellStyle(hssfCellStyle2);

            hssfCell = hssfRow.createCell(3);
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            log.info(bartDateFormat.format(norm.getFigureTime()));
            hssfCell.setCellValue(bartDateFormat.format(norm.getFigureTime()));

            //第三行
            hssfRow = hssfSheet.createRow(2);
            hssfCell = hssfRow.createCell(0);//列索引从0开始
            hssfCell.setCellValue("描述");

            hssfCellStyle2.setFont(font);
            hssfCell.setCellStyle(hssfCellStyle2);

            hssfCell = hssfRow.createCell(1);
            hssfCell.setCellValue(norm.getDescription());

            hssfRow = hssfSheet.createRow(3);
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            // 第五步，写入实体数据

            List<NormDetailed> normDetaileds = norm.getNdList();

            if (normDetaileds != null && !normDetaileds.isEmpty()) {
                for (int i = 0; i < normDetaileds.size(); i++) {
                    //从第五行 开始显示数据表
                    hssfRow = hssfSheet.createRow(i + 4);

                    NormDetailed normDetailed = normDetaileds.get(i);

                    // 第六步，创建单元格，并设置值
                    String moduleName = normDetailed.getModule().getModulename();
                    hssfCell = hssfRow.createCell(0);
                    hssfCell.setCellValue(moduleName);
                    hssfCell.setCellStyle(hssfCellStyle);

                    String avg = NumFormat.formatSCM(normDetailed.getAverage());
                    hssfCell = hssfRow.createCell(1);
                    hssfCell.setCellValue(avg);
                    hssfCell.setCellStyle(hssfCellStyle);

                    String standardscore = NumFormat.formatSCM(normDetailed.getStandardScore());
                    hssfCell = hssfRow.createCell(2);
                    hssfCell.setCellValue(standardscore);
                    hssfCell.setCellStyle(hssfCellStyle);

                    String level = normDetailed.getModule().getModuletype();
                    hssfCell = hssfRow.createCell(3);
                    hssfCell.setCellValue(getFTLevel(level).getFTLevel());
                    hssfCell.setCellStyle(hssfCellStyle);


                }
            }

            //xls命名规则：产品名称V版本名称计算时间（样本量）；
            //MAP管培生V20170914(50000)
            //产品名称：智鼎测评系统的产品名称MAP管培生
            //版本名称：常模计算之后的生成的常模数据名称  20170914
            //样本量：一次常模计算的样本数量(数据量)       50000
            String fileName = productName+"V"+norm.getVersion() + "(" +norm.getSampleSize()+")";
            //String fileName = new String((productName + "V" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "(" +norm.getSampleSize()+")").getBytes(), "UTF-8");


            fileName= new String((fileName).getBytes(), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            //获取输出流
            ServletOutputStream out = response.getOutputStream();
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Norm selectByPrimaryKeyOut(Long normId) {
        return normMapper.selectByPrimaryKeyOut(normId);
    }

	@Override
	public Long insertNorm(Long userid,String normName, String version, String description, String moduleIds, Long productId) {
		Date now = new Date();
		Norm norm = new Norm(null,userid,productId,normName,version,0,description,null,now,0,null,null,null,0);
		normMapper.insertSelective(norm);
		List<String> modidList = StringsUtil.splitByComma(moduleIds);
		for(String moduleid : modidList){
			NormDetailed nd = new NormDetailed();
			nd.setCreateDate(now);
			nd.setModuleId(Long.valueOf(moduleid));
			nd.setNormId(norm.getId());
			nd.setStatus(0);
			nd.setAverage(new BigDecimal("0.0"));
			nd.setStandardScore(new BigDecimal("0.0"));
			ndMapper.insertSelective(nd);
		}
		return norm.getId();
	}

	@Override
    public Map<String, List<NormDetailed>> getNormDetailedMap(Norm norm) {
        List<NormDetailed> ndList = norm.getNdList();
        Map<String,List<NormDetailed>> ndMap = new TreeMap<>();
        for(NormDetailed nd:ndList){
            Boolean isHave = false;
            Set<String> key = ndMap.keySet();
            for(String i:key){
                if(getFTLevel(nd.getModule().getModuletype()).getFTLevel().equals(i)) {
                    ndMap.get(i).add(nd);
                    isHave = true;
                    break;
                }
            }if(!isHave) {
                List<NormDetailed> list = new ArrayList<>();
                list.add(nd);
                ndMap.put(getFTLevel(nd.getModule().getModuletype()).getFTLevel(), list);
            }
        }
        /*log.info(MapUtil.sortMapByKeyOfTwo(ndMap));*/
        return MapUtil.sortMapByKeyOfTwo(ndMap);
    }

    @Override
    public int deleteById(Long normId) {
	    int modify = normMapper.deleteByPrimaryKey(normId);
        if(modify>0){
            return 0;
        }
	    return -1;
    }

    @Override
	public int updateNorm(Norm norm) {
    	normMapper.updateByPrimaryKeySelective(norm);
		return 0;
	}

	@Override
	public int modifyNorm(Long id, String normName, String version, String description) {
		Norm norm = normMapper.selectByPrimaryKey(id);
		norm.setNormName(normName);
		norm.setVersion(version);
		norm.setDescription(description);
		int modify = normMapper.updateByPrimaryKeySelective(norm);
		if(modify >0){
			return 0;
		}
		return -1;
	}

	@Override
	public boolean checkNormVersion(Long id,Long proID, String version) {
		
		List<Norm> normList = normMapper.selectByVersion(version,proID);
		if(normList.size() == 0){
			return true;
		}
		if(normList.size() == 1 && normList.get(0).getId() == id){
			return true;
		}
		return false;
	}

}
