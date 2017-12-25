package cn.com.zhiding.norm.entity;

public class ExamResult {
    private Long dResultId;

    private Long activitypersonId;

    private Long toolId;

    private Long moduleId;

    private String moduleName;

    private Double totalmark;

    private Double mark1;

    private Double mark2;

    private Double avgdimemark;

    private Double zMark;

    private Double tMark;

    private Double pMark;

    private Double pModified;

    private String level;

    private Double avgquesmark;

    private Integer tenpoint;

    public Long getdResultId() {
        return dResultId;
    }

    public void setdResultId(Long dResultId) {
        this.dResultId = dResultId;
    }

    public Long getActivitypersonId() {
        return activitypersonId;
    }

    public void setActivitypersonId(Long activitypersonId) {
        this.activitypersonId = activitypersonId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public Double getTotalmark() {
        return totalmark;
    }

    public void setTotalmark(Double totalmark) {
        this.totalmark = totalmark;
    }

    public Double getMark1() {
        return mark1;
    }

    public void setMark1(Double mark1) {
        this.mark1 = mark1;
    }

    public Double getMark2() {
        return mark2;
    }

    public void setMark2(Double mark2) {
        this.mark2 = mark2;
    }

    public Double getAvgdimemark() {
        return avgdimemark;
    }

    public void setAvgdimemark(Double avgdimemark) {
        this.avgdimemark = avgdimemark;
    }

    public Double getzMark() {
        return zMark;
    }

    public void setzMark(Double zMark) {
        this.zMark = zMark;
    }

    public Double gettMark() {
        return tMark;
    }

    public void settMark(Double tMark) {
        this.tMark = tMark;
    }

    public Double getpMark() {
        return pMark;
    }

    public void setpMark(Double pMark) {
        this.pMark = pMark;
    }

    public Double getpModified() {
        return pModified;
    }

    public void setpModified(Double pModified) {
        this.pModified = pModified;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Double getAvgquesmark() {
        return avgquesmark;
    }

    public void setAvgquesmark(Double avgquesmark) {
        this.avgquesmark = avgquesmark;
    }

    public Integer getTenpoint() {
        return tenpoint;
    }

    public void setTenpoint(Integer tenpoint) {
        this.tenpoint = tenpoint;
    }
}