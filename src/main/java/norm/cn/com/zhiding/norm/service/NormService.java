package cn.com.zhiding.norm.service;

import cn.com.zhiding.norm.entity.Norm;
import cn.com.zhiding.norm.entity.NormDetailed;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NormService {
	/**
	 * 登录后获取常模列表
	 * @param map 模糊查询 里面包括用户id，常模名称，状态
	 * @author zzk
	 * @return
	 */
	public List<Norm> getNormList(Map<String,Object> map);
	
	/**
	 * 查询列表数量
	 * @param map
	 * @return
	 */
	public Integer countNormList(Map<String,Object> map);
	
	/**
	 * 计算常模值
	 * @param userId 用户id
	 * @param proId	产品id
	 * @return int  0-正在计算，1-没有测评数据，2-参数错误，3-未知错误
	 */
	public int figureNorm(Long normId);

	/**
	 * Excel导出
	 * @param normId 常模id
	 * @param response
	 * @author zzk
	 */
	public void exportNorm(Long normId, HttpServletResponse response) throws IOException;

	/**
	 * 通过常模id查询常模所有信息
	 * @param normId 常模id
	 * @return
	 */
	public Norm selectByPrimaryKeyOut(Long normId);
	
	/**
	 * 保存常模信息
	 * @param userid 用户id
	 * @param normName 常模名称
	 * @param version	常模版本
	 * @param description 描述
	 * @param moduleIds 维度id字符串
	 * @param productId 产品id
	 * @return 常模id
	 * @author gaoqj
	 */
	public Long insertNorm(Long userid,String normName,String version,String description,String moduleIds,Long productId);
	
	/**
	 * 更新常模信息
	 * @param id 常模id
	 * @param normName 常模名称
	 * @param version 常模版本
	 * @param description 常模描述
	 * @return
	 */
	public int modifyNorm(Long id,String normName,String version,String description);
	
	/**
	 * 检测版本唯一
	 * @param id 常模id
	 * @param version 常模版本
	 * @return false-不唯一，true-唯一
	 */
	public boolean checkNormVersion(Long id,Long proID,String version);


	/**
	 * 对常模明细 进行按级别分类
	 * @param norm 常模
	 * @author zzk
	 * @return
	 */
	public Map<String,List<NormDetailed>> getNormDetailedMap(Norm norm);

	/**
	 * 根据常模id 删除常模
	 * @param normId 常模id
	 * @author zzk
	 * @return
	 */
	public int deleteById(Long normId);
	
	/**
	 * 根据常模 更新常模
	 * @param norm 常模
	 * @author zzk
	 * @return
	 */
	public int updateNorm(Norm norm);

}
