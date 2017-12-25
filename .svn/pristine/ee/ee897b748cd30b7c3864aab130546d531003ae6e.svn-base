package cn.com.zhiding.norm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.zhiding.norm.entity.ExamResult;

public interface ExamResultMapper {
    int deleteByPrimaryKey(Long dResultId);

    int insert(ExamResult record);

    int insertSelective(ExamResult record);

    ExamResult selectByPrimaryKey(Long dResultId);

    int updateByPrimaryKeySelective(ExamResult record);

    int updateByPrimaryKey(ExamResult record);
    
    /**
     * 根据维度id、工具id和测评人id获取测评结果，仅限一级维度
     * @param moduleId 维度id
     * @param activitypersonId 测评人id
     * @return
     */
    ExamResult findExamResultByApIdAMid(Long moduleId,Long activitypersonId,Long toolsId);
    
    /**
     * 根据工具id,apid查询成绩
     * @param list
     * @param toolsId
     * @return 全部测评人员的成绩
     */
    List<ExamResult> queryExamResulByApIds(@Param("list")List<Long> list,@Param("toolsId") Long toolsId);
}