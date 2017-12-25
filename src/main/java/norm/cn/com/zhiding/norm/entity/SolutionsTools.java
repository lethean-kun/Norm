package cn.com.zhiding.norm.entity;

public class SolutionsTools {
    private Long id;

    private Long solutionsId;

    private Long toolId;

    private Long toolsNormId;

    private Integer isSolutions;

    private Integer isDel;

    private Integer sortNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSolutionsId() {
        return solutionsId;
    }

    public void setSolutionsId(Long solutionsId) {
        this.solutionsId = solutionsId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public Long getToolsNormId() {
        return toolsNormId;
    }

    public void setToolsNormId(Long toolsNormId) {
        this.toolsNormId = toolsNormId;
    }

    public Integer getIsSolutions() {
        return isSolutions;
    }

    public void setIsSolutions(Integer isSolutions) {
        this.isSolutions = isSolutions;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}