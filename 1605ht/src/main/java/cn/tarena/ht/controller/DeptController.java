package cn.tarena.ht.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
public class DeptController {

	@Autowired
	private DeptService deptService;
	
	/*
	 * 这个方法是用来定位部门查询列表页面的
	 * 对应的后台请求路径：/sysadmin/dept/list
	 * 对应的前台页面：WEB-INF/pages/sysadmin/dept/jDeptList.jsp
	 * 页面要取的值：
	 * 1.部门id
	 * 2.父部门名称
	 * 3.部门名称
	 * 4.部门状态
	 * 所以根据取值情况，要做部门表的自关联查询，要掌握sql语句的写法，以及要考虑Mybatis同名字段的bug问题
	 * select * from dept_p left join 
		(select dept_id as p_id,dept_name as p_name from dept_p)p 
		on dept_p.parent_id=p.p_id
	 */
	@RequestMapping("/sysadmin/dept/list")
	public String list(Model model){
		List<Dept> dataList=deptService.findAllDept();
		model.addAttribute("dataList", dataList);
		return "/sysadmin/dept/jDeptList";
	}
	/*
	 * 这个方法是转向部门新增页面的，对应的后台请求路径：/sysadmin/dept/tocreate
	 * 对应的新增页面：WEB-INF/pages/sysadmin/dept/jDeptCreate.jsp
	 * 部门新增的信息有：
	 * 1.部门名称
	 * 2.部门的父部门信息
	 * 根据父部门信息，在转向新增页面的时候，要组织部门数据，传给页面
	 */
	@RequestMapping("/sysadmin/dept/tocreate")
	public String toCreate(Model model){
		List<Dept> dataList=deptService.findAllDept();
		model.addAttribute("dataList", dataList);
		return "/sysadmin/dept/jDeptCreate";
	}
	/*
	 * 这个方法是用来实现保存的，对应的请求路径：/sysadmin/dept/insert
	 * 
	 */
	@RequestMapping("/sysadmin/dept/insert")
	public String save(Dept dept){
		deptService.insert(dept);
		//新增完后转向部门列表页面
		return "forward:/sysadmin/dept/list";
	}
	/*
	 * 这个方法是转向部门更新页面的，对于的后台请求路径：/sysadmin/dept/toupdate
	 * 对应前台页面：WEB-INF/pages/sysadmin/dept/jDeptUpdte.jsp
	 * 要注意的点：
	 * 1.要接收前台传来部门主键值
	 * 2.因为页面要做数据的回显，所以我们要根据主键值查出对应的部门信息，然后传给前台
	 * 3.因为要父部门信息，所以要组织所有部门的数据传给前台
	 * request.getParameter("参数名"),利用原生的反射机制，是拿不到形参名的，一般框架都是
	 * 利用反射增强包，来拿到形参名。只要拿到形参名，就能够通过getParameter()前传过来的值
	 * @RequestParam(value="id") 通过反射 拿到 deptId=>id  
	 */
	@RequestMapping("/sysadmin/dept/toupdate")
	public String toUpdate(String deptId,Model model){
		Dept dept=deptService.findDeptById(deptId);
		List<Dept> dataList=deptService.findAllDept();
		model.addAttribute("dept", dept);
		model.addAttribute("dataList", dataList);
		return "/sysadmin/dept/jDeptUpdate";
	}
	/*
	 * 这个方法是用来更新保存的
	 * 对应的后台请求路径： /sysadmin/dept/update
	 */
	@RequestMapping("/sysadmin/dept/update")
	public String update(Dept dept){
		deptService.update(dept);
		return "forward:/sysadmin/dept/list";
	}
	/*
	 * 这个方法是用来删除单个部门对象的，对应后台请求路径：/sysadmin/dept/deleteone
	 */
	@RequestMapping("/sysadmin/dept/deleteone")
	public String deleteone(String deptId){
		deptService.deleteOne(deptId);
		return "forward:/sysadmin/dept/list";
	}
	/*
	 * 这个方法是用来做批量删除的，对应的后台请求路径： /sysadmin/dept/delete
	 * 要掌握用数组形式来接参，
	 * 注意，如果是用String来接，id1,id2,id3.所以也可以用string来接
	 */
	@RequestMapping("/sysadmin/dept/delete")
	public String delete(String[] deptId){
		deptService.delete(deptId);
		return "forward:/sysadmin/dept/list";
	}
}
