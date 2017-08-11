package cn.tarena.ht.pojo;

import java.util.Date;

/**
 * 这个类存放的是通用字段
 * @author ysq
 *
 */
public class BasePojo {
	private String createBy;//创建者信息
	private String createDept;//创建部门信息
	private Date createTime;//部门的创建时间
	private String updateBy;//更新者信息
	private Date updateTime;//部门更新时间
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
