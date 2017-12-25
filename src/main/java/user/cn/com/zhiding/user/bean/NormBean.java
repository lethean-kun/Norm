package cn.com.zhiding.user.bean;

import java.math.BigDecimal;

/**
 * normbean 工具类
 * @author gaoqj
 *
 */
public class NormBean {
	
	private BigDecimal originalScore;
	
	private BigDecimal averageScore;
	
	private BigDecimal standardDeviation;
	
	private BigDecimal standardZScore;
	
	private Long moduleId;
	
	private Long apId;
	
	private Long toolsId;

	public BigDecimal getOriginalScore() {
		return originalScore;
	}

	public void setOriginalScore(BigDecimal originalScore) {
		this.originalScore = originalScore;
	}

	public BigDecimal getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(BigDecimal averageScore) {
		this.averageScore = averageScore;
	}

	public BigDecimal getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(BigDecimal standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public BigDecimal getStandardZScore() {
		return standardZScore;
	}

	public void setStandardZScore(BigDecimal standardZScore) {
		this.standardZScore = standardZScore;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getApId() {
		return apId;
	}

	public void setApId(Long apId) {
		this.apId = apId;
	}

	public Long getToolsId() {
		return toolsId;
	}

	public void setToolsId(Long toolsId) {
		this.toolsId = toolsId;
	}
	
	

	
}
