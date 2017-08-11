package cn.tarena.ht.pojo;
/**
 * 这个类对应的是role_p表
 * @author ysq
 *
 */
public class Role extends BasePojo {
	
	private String roleId;//主键	
	private String name;//角色名称
	private String remark;//备注
	private int orderNo;//排序号
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
