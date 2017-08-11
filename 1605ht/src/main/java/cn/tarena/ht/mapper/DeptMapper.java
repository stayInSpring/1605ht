package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {

	List<Dept> findAllDept();

	void insert(Dept dept);

	Dept findDeptById(String deptId);

	void update(Dept dept);

	void deleteOne(String deptId);

	void delete(String[] deptId);

}
