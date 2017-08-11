package cn.tarena.ht.pojo;

import java.util.List;

/**
 * 这个类对应的user_p这个表
 */
public class User extends BasePojo{
	
	private String userId;
	private String deptId;
	private String username;
	private String password;
	private int state;
	//表示出和部门的关联关系
	private Dept dept;
	//表示出用户信息的关联关系
	private UserInfo userInfo;
	//表示和角色的对多关系
	private List<Role> roles;
	//表示和权限的对多关系
	private List<Module> modules;
	
	
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	


}
