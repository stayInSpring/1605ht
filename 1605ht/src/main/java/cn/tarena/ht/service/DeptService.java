package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.pojo.Dept;

@Service
public class DeptService {
	@Autowired
	private DeptMapper deptMapper;

	public List<Dept> findAllDept() {
		// TODO Auto-generated method stub
		return deptMapper.findAllDept();
	}

	/*
	 * dept现在封装的属性有deptName,parentId
	 * 别忘了设置主键值、状态值等信息
	 */
	public void insert(Dept dept) {
		dept.setDeptId(UUID.randomUUID().toString());
		dept.setState(1);//默认的状态是启用的
		dept.setCreateTime(new Date());
		dept.setUpdateTime(dept.getCreateTime());
		deptMapper.insert(dept);
		
	}

	public Dept findDeptById(String deptId) {
		
		return deptMapper.findDeptById(deptId);
	}
	
	public void update(Dept dept) {
		//别忘了设置下更新时间
		dept.setUpdateTime(new Date());
		dept.setState(1);
		deptMapper.update(dept);
		
	}

	public void deleteOne(String deptId) {
		deptMapper.deleteOne(deptId);
		
	}

	public void delete(String[] deptId) {
		deptMapper.delete(deptId);
		
	}
}
