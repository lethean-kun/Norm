package cn.com.zhiding.norm.entity;

import java.util.Date;

public class Module {
    private Long moduleId;

    private String modulename;

    private String moduleinternalname;

    private String moduletype;

    private Long reporttypeId;

    private Date createdate;

    private Date modifydate;

    private String status;

    private Long moduleOldid;

    private Long toolOldtype;

    private String moduledesc;
    
    private ToolsModule tm;
    
    private SolutionsModule sm;

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public String getModuleinternalname() {
        return moduleinternalname;
    }

    public void setModuleinternalname(String moduleinternalname) {
        this.moduleinternalname = moduleinternalname == null ? null : moduleinternalname.trim();
    }

    public String getModuletype() {
        return moduletype;
    }

    public void setModuletype(String moduletype) {
        this.moduletype = moduletype == null ? null : moduletype.trim();
    }

    public Long getReporttypeId() {
        return reporttypeId;
    }

    public void setReporttypeId(Long reporttypeId) {
        this.reporttypeId = reporttypeId;
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

    public Long getModuleOldid() {
        return moduleOldid;
    }

    public void setModuleOldid(Long moduleOldid) {
        this.moduleOldid = moduleOldid;
    }

    public Long getToolOldtype() {
        return toolOldtype;
    }

    public void setToolOldtype(Long toolOldtype) {
        this.toolOldtype = toolOldtype;
    }

    public String getModuledesc() {
        return moduledesc;
    }

    public void setModuledesc(String moduledesc) {
        this.moduledesc = moduledesc == null ? null : moduledesc.trim();
    }

	public ToolsModule getTm() {
		return tm;
	}

	public void setTm(ToolsModule tm) {
		this.tm = tm;
	}

	public SolutionsModule getSm() {
		return sm;
	}

	public void setSm(SolutionsModule sm) {
		this.sm = sm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moduleId == null) ? 0 : moduleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		return true;
	}
	
	
	
    
	
    
}