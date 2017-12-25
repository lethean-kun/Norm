package cn.com.zhiding.norm.mapper;

import cn.com.zhiding.norm.entity.ExamAdvanceResult;

public interface ExamAdvanceResultMapper {
    int deleteByPrimaryKey(Long arResultId);

    int insert(ExamAdvanceResult record);

    int insertSelective(ExamAdvanceResult record);

    ExamAdvanceResult selectByPrimaryKey(Long arResultId);

    int updateByPrimaryKeySelective(ExamAdvanceResult record);

    int updateByPrimaryKey(ExamAdvanceResult record);
    
    /**
     * 获取高阶维度的分数
     * @param moduleId
     * @param ExamResult
     * @return
     */
    ExamAdvanceResult findExamResultByApIdAHMid(Long moduleId,Long ExamResult);
}