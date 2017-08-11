package cn.tarena.ht.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.pojo.Module;

@Service
public class ModuleService {
	@Autowired
	private ModuleMapper moduleMapper;

	public List<Module> findAllModule() {
		
		return moduleMapper.findAllModule();
	}

	public List<Module> findByAnnotation() {
		
		return moduleMapper.findByAnnotation();
	}

	public List<Module> findByProvider() {
		// TODO Auto-generated method stub
		return moduleMapper.findByProvider();
	}

	public void insertByAnnotation(Module module) {
		module.setModuleId(UUID.randomUUID().toString());
		module.setState(1);//启动
		moduleMapper.insertByAnnotation(module);
		
	}

	public void deleteByAnnotation(String moduleId) {
		moduleMapper.deleteByAnnotation(moduleId);
		
	}
}
