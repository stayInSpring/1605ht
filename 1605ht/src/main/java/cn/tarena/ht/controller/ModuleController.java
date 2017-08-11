package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;

@Controller
public class ModuleController {
	@Autowired
	private ModuleService moduleService;
	
	/*
	 * 权限表的单表查询
	 * 需要掌握：
	 * 1.Mybatis的驼峰映射的使用，
	 * 2.Mybatis的配置是有顺序要求的，
	 */
	@RequestMapping("/sysadmin/module/list")
	public String list(Model model){
		//List<Module> dataList=moduleService.findAllModule();
		List<Module> dataList=moduleService.findByAnnotation();
		model.addAttribute("dataList", dataList);
		return "/sysadmin/module/jModuleList";
	}
	@RequestMapping("/sysadmin/module/tocreate")
	public String toCreate(Model model){
		List<Module> moduleList=moduleService.findByProvider();
		model.addAttribute("moduleList", moduleList);
		return "/sysadmin/module/jModuleCreate";
	}
	@RequestMapping("/sysadmin/module/insert")
	public String insert(Module module){
		moduleService.insertByAnnotation(module);
		return "forward:/sysadmin/module/list";
	}
	@RequestMapping("/sysadmin/module/delete")
	public String delete(String moduleId){
		moduleService.deleteByAnnotation(moduleId);
		return "forward:/sysadmin/module/list";
	}
}
