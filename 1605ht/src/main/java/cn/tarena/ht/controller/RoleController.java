package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("/sysadmin/role/list")
	public String list(Model model){
		List<Role> dataList=roleService.findAll();
		model.addAttribute("dataList", dataList);
		return "/sysadmin/role/jRoleList";
	}
	@RequestMapping("/sysadmin/role/tocreate")
	public String toCreate(){
		return "/sysadmin/role/jRoleCreate";
	}
	@RequestMapping("/sysadmin/role/insert")
	public String insert(Role role){
		roleService.insert(role);
		return "forward:/sysadmin/role/list";
	}
	@RequestMapping("/sysadmin/role/delete")
	public String delete(String roleId){
		roleService.deleteById(roleId);
		return "forward:/sysadmin/role/list";
	}
	@RequestMapping("/sysadmin/role/toupdate")
	public String toUpdate(String roleId,Model model){
		Role role=roleService.findRoleById(roleId);
		model.addAttribute("role", role);
		return "/sysadmin/role/jRoleUpdate";
	}
	@RequestMapping("/sysadmin/role/roleAction_update")
	public String update(Role role){
		roleService.update(role);
		return "forward:/sysadmin/role/list";
	}
	/*
	 * 这个方法是用来转向角色-权限的页面，对应的后台请求路径：/sysadmin/role/tomodule
	 * 对应前台页面：WEB-INF/pages/sysadmin/role/jRoleModule.jsp
	 * 实现思路：
	 * 1.在转向页面的时候，要准备数据——所有权限的数据-List<Module>
	 * 2.根据ztree的要求，组织成如下格式的数据：
	 * [{id:' ',name:' ',pId:' '},{},{}]
	 * 3.利用Model传给前台，前台会拿到值之后进行解析
	 */
	@RequestMapping("/sysadmin/role/tomodule")
	public String toRoleModule(Model model,String roleId){
		List<Module> moduleList=moduleService.findAllModule();
		//这个集合里，存储的是当前roleId查出的moduleId集合,（去role_module_p中间表去查)
		List<String> moduleIdsOfRole=roleService.findModuleIdsByRoleId(roleId);
		for(Module m:moduleList){
			if(moduleIdsOfRole.contains(m.getModuleId())){
				m.setChecked("true");
			}else{
				m.setChecked("false");
			}
			
		}
		
		//jackson工具，专门用来处理json数据格式的
		//先要引入它的依赖jar包，在汇通项目里，已经通过pom文件引入了
		ObjectMapper mapper=new ObjectMapper();
		try {
			//writeValueAsString可以把一个对象转换成json串，也可以把对象集合转换成json串
			//这个方法是讲对象的所有私有属性字段，都转换成json串里的字段
			//工作原理：
			//1.会拿到module对象，然后会遍历对象里所有的getXxx()
			//2.执行getXxx()方法时，会得到相应的值，以及get的Xxx字段，所以会组织成：Xxx:对应的值
			//然后用，拼接起来
			String json=mapper.writeValueAsString(moduleList);
			model.addAttribute("zTreeJson", json);
			model.addAttribute("roleId", roleId);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return "/sysadmin/role/jRoleModule";
	}
	/*
	 * 这个方法是用于角色-权限分配新增的，对应的请求路径： /sysadmin/role/module
	 */
	@RequestMapping("/sysadmin/role/module")
	public String insertRoleModule(String roleId,String[] moduleIds){
		roleService.insertRoleModule(roleId,moduleIds);
		return "forward:/sysadmin/role/list";
	}
}
