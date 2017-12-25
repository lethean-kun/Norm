package cn.com.zhiding.norm.entity;

import cn.com.zhiding.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Norm {
    private Long id;

    private Long userId;

    private Long productId;

    private String normName;

    private String version;

    private Integer sampleSize;

    private String description;

    private int isSend;

    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date figureTime;

    private Date createDate;

    private Integer status;//状态0-待计算,1-计算中,2-计算完成,3-计算失败,4-已删除

    private Date startTime;

    private Date endTime;

    private String figureMsg;


    private String productName; 

	private List<NormDetailed> ndList;

    public int getIsSend() {
        return isSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNormName() {
        return normName;
    }

    public void setNormName(String normName) {
        this.normName = normName == null ? null : normName.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(Integer sampleSize) {
        this.sampleSize = sampleSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getFigureTime() {
        return figureTime;
    }

    public void setFigureTime(Date figureTime) {
        this.figureTime = figureTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getFigureMsg() {
        return figureMsg;
    }

    public void setFigureMsg(String figureMsg) {
        this.figureMsg = figureMsg == null ? null : figureMsg.trim();
    }
    
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<NormDetailed> getNdList() {
  		return ndList;
  	}

  	public void setNdList(List<NormDetailed> ndList) {
  		this.ndList = ndList;
  	}
  	
  	public Norm(){}

	public Norm(Long id, Long userId, Long productId, String normName, String version, Integer sampleSize,
			String description, Date figureTime, Date createDate, Integer status, Date startTime, Date endTime,
			String figureMsg,Integer isSend) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.normName = normName;
		this.version = version;
		this.sampleSize = sampleSize;
		this.description = description;
		this.figureTime = figureTime;
		this.createDate = createDate;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.figureMsg = figureMsg;
		this.isSend = isSend;
	}
  	
  	
}