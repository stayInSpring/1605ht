package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	
	/*
	 * 这个方法是用来定位用户查询列表页面的，对应的后台请求路径：/sysadmin/user/list
	 * 对应的前台页面：WEB-INF/pages/sysadmin/user/jUserList.jsp
	 * 前台要的值：
	 * 1.登录名 user_p的username
	 * 2.部门名称：涉及到user_p和dept_p的关键查询
	 * 3.真实姓名：user_info_p表里的name，设计到user_p和user_info_p的关联查询
	 * 4.领导姓名：通过user_info_P的manager_id来查询得到的，所以设计user_info_p的自关联查询
	 * 5.比如生日、薪资等都在user_info_p表里存储的
	 */
	@RequestMapping("/sysadmin/user/list")
	public String list(Model model){
		List<User> dataList=userService.findAllUser();
		model.addAttribute("dataList", dataList);
		return "/sysadmin/user/jUserList";
	}
	/*
	 * 这个方法是转向用户新增页面的方法，对应的后台请求路径：/sysadmin/user/tocreate
	 * 对应的前台页面：WEB-INF/pages/sysadmin/user/jUserCreate.jsp
	 * 前台要俩个数据集合：1.部门数据 2.直属领导数据（实际就是用户信息数据的集合）
	 * 所以我们需要在转向页面的时候传这两个集合。
	 * 
	 */
	@RequestMapping("/sysadmin/user/tocreate")
	public String toCreate(Model model){
		List<Dept> deptList=deptService.findAllDept();
		List<User> userList=userService.findAllUser();
		model.addAttribute("deptList", deptList);
		model.addAttribute("userList", userList);
		return "/sysadmin/user/jUserCreate";
	}
	/*
	 * 这个方法是用来新增用户的，对应的后台请求路径：/sysadmin/user/insert
	 * 在日期格式转换，容易出现格式转换出错的问题，
	 * 默认日期格式是yyyy/MM/dd
	 * 但是前台传来的日期：yyyy-MM-dd
	 *
	 */
	@RequestMapping("/sysadmin/user/insert")
	public String insert(User user,UserInfo userInfo){
		userService.insert(user,userInfo);
		return "forward:/sysadmin/user/list";
	}
	/*
	 * 删除用户，对应的请求路径： /sysadmin/user/delete
	 */
	@RequestMapping("/sysadmin/user/delete")
	public String delete(String userId){
		userService.deleteById(userId);
		return "forward:/sysadmin/user/list";
	}
	
	/*
	 * 这个方法是转向用户-角色页面的，对应的请求路径： /sysadmin/user/torole
	 * 
	 */
	@RequestMapping("/sysadmin/user/torole")
	public String toRole(String userId,Model model){
		User user=userService.findRoleOfUserId(userId);
		//这个角色集合就是要向前台传递的数据
		List<Role> roleList=user.getRoles();
		model.addAttribute("roleList", roleList);
		return "/sysadmin/user/jUserRole";
	}
}
