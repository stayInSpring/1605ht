package cn.tarena.ht.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 这个类对应的user_info_p这个表
 * @author ysq
 *
 */
public class UserInfo extends BasePojo{
	
	private String userInfoId;//用户信息表的主键	
	private String name;//用户的真实姓名		
	private String cardNo;//用户的身份证	
	private String managerId;//当前用户的领导id	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joinDate;//入职日期
	private Double salary;//薪资
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;//生日	
	private String gender;  //姓别	
	private String station;//职务	
	private String telephone;//电话
	private String userLevel;//用户级别		
	private String remark;//标注，备注
	private int orderNo;//排序
	//表示和领导的关联关系
	private UserInfo manager;
	
	
	public UserInfo getManager() {
		return manager;
	}
	public void setManager(UserInfo manager) {
		this.manager = manager;
	}
	public String getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	

}
