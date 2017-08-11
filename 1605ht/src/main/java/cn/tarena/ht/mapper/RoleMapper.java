package cn.tarena.ht.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	@Select("select * from role_p")
	List<Role> findAll();

	void insert(Role role);
	
	@Delete("delete from role_p where role_id=#{roleId}")
	void deleteById(String roleId);
	
	@Select("select * from role_p where role_id=#{roleId}")
	Role findRoleById(String roleId);
	@Update("update role_p set name=#{name},remark=#{remark},"
			+ "update_time=#{updateTime} where role_id=#{roleId}")
	void update(Role role);

	void insertRoleModule(Map<String, String> map);

	List<String> findModuleIdsByRoleId(String roleId);

}
