package cn.tarena.ht.pojo;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 这个类对应的是Module_p这个表
 */

public class Module extends BasePojo{
	@JsonProperty(value="id")
	private String moduleId;//权限主键	
	@JsonProperty(value="pId")
	private String parentId; //权限的父权限	
	private String name;//权限名称		
	private int ctype;//权限类型
	private int state;//状态	
	private int orderNo;//排序号	
	private String remark;//备注	
	//加这个字段之后，因为module_p没有checked这个字段，所有mybatis做数据封装的时候
	//会报错，会报无效字段错误,	@Transient这个注解的作用，标识此字段是非数据库字段
	@Transient
	private String checked;
	
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	//	//根据ztree的要求组织的json字段
//	//当以getXxx,截get的时候，Xxx,如果第一个X是大写的话，都会把大写变小写。如果第一个X本身就是小写的话
//	//则不做处理。以后的x不做任何处理。
//	public String getId(){
//		return moduleId;
//	}
//	//根据ztree的要求组织的json字段
//	public String getpId(){
//		return parentId;
//	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
