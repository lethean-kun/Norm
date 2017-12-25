package cn.com.zhiding.norm.entity;

import java.util.Date;

public class ModuleRelations {
    private Long modulerelationId;

    private Long parentId;

    private Long sonId;

    private Double weight;

    private Date createdate;

    private Date modifydate;

    private String status;

    private Long toolId;

    private Long solutionId;

    private Integer orientation;

    private Integer caltype;

    public Long getModulerelationId() {
        return modulerelationId;
    }

    public void setModulerelationId(Long modulerelationId) {
        this.modulerelationId = modulerelationId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getSonId() {
        return sonId;
    }

    public void setSonId(Long sonId) {
        this.sonId = sonId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Integer getCaltype() {
        return caltype;
    }

    public void setCaltype(Integer caltype) {
        this.caltype = caltype;
    }
}