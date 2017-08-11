package cn.tarena.ht.pojo;



public class Dept extends BasePojo{
		
	private String deptId;//部门的主键 
	private String parentId;//当前部门父部门的id
	private String deptName;//当前部门名称
	private int orderNo;//部门的排序号
	private int state;//部门状态，1代表启用，0代表停用
	
	//这个对象表示当前部门的父部门
	private Dept parent;
	
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
