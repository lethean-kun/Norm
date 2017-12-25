package cn.com.zhiding.user.entity;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public class SZhidingEmail implements java.io.Serializable {

	// Fields

	private Long Id;//邮件id
	private String emailAddr;//邮件地址+
	private Date crtDt;//创建时间+
	private Date updateDt;//修改时间
	private String status;//发送状态0-未发送 1-已发送 2-取消+
	private Long zdyyResultId;//测评者id
	private String emailFrom;//邮件发送人
	private String name;//测试人姓名
	private String testUrl;//直接登录的测试地址
	private String testCorp;//测试邀请的公司名
	private String normalUrl;//需要登录的测试地址
	private String seqCode;//登录序列号
	private String password;//登录密码
	private Timestamp sendDate;//发送时间
	private String sendStatus;//发送回馈状态 0成功 1失败
	private String sendContent;//发送内容+
	private String sendEmailTitle;//发送邮件标题+
	private String sendEmail;//发送邮箱+
	private String sendAccount;//发送账号+
	private String sendPassword;//发送账号密码 +
	private String sendWay;//发送方式（51、zd）+51
	private int sendRecount;//重发次数
	private String examinDate;//作答日期范围
	private String examTime;//测评时长

	private Long enterpriseId;
	private Long activityid;//活动id

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getSendEmailTitle() {
		return sendEmailTitle;
	}

	public void setSendEmailTitle(String sendEmailTitle) {
		this.sendEmailTitle = sendEmailTitle;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getSendAccount() {
		return sendAccount;
	}

	public void setSendAccount(String sendAccount) {
		this.sendAccount = sendAccount;
	}

	public String getSendPassword() {
		return sendPassword;
	}

	public void setSendPassword(String sendPassword) {
		this.sendPassword = sendPassword;
	}

	public String getSendWay() {
		return sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	public int getSendRecount() {
		return sendRecount;
	}

	public void setSendRecount(int sendRecount) {
		this.sendRecount = sendRecount;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getZdyyResultId() {
		return this.zdyyResultId;
	}

	public void setZdyyResultId(Long zyddResultId) {
		this.zdyyResultId = zyddResultId;
	}

	public String getEmailFrom() {
		return this.emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTestUrl() {
		return this.testUrl;
	}

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}

	public String getTestCorp() {
		return this.testCorp;
	}

	public void setTestCorp(String testCorp) {
		this.testCorp = testCorp;
	}

	public String getNormalUrl() {
		return this.normalUrl;
	}

	public void setNormalUrl(String normalUrl) {
		this.normalUrl = normalUrl;
	}

	public String getSeqCode() {
		return this.seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getExaminDate() {
		return examinDate;
	}

	public void setExaminDate(String examinDate) {
		this.examinDate = examinDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getActivityid() {
		return activityid;
	}

	public void setActivityid(Long activityid) {
		this.activityid = activityid;
	}
	

}