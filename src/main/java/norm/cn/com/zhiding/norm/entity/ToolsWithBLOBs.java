package cn.com.zhiding.norm.entity;

public class ToolsWithBLOBs extends Tools {
    private String tooldesc;

    private String toolintro;

    public String getTooldesc() {
        return tooldesc;
    }

    public void setTooldesc(String tooldesc) {
        this.tooldesc = tooldesc == null ? null : tooldesc.trim();
    }

    public String getToolintro() {
        return toolintro;
    }

    public void setToolintro(String toolintro) {
        this.toolintro = toolintro == null ? null : toolintro.trim();
    }
}