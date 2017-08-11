package cn.tarena.ht.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;

@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;

	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleMapper.findAll();
	}

	public void insert(Role role) {
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateTime(new Date());
		role.setUpdateTime(role.getCreateTime());
		roleMapper.insert(role);
		
	}

	public void deleteById(String roleId) {
		roleMapper.deleteById(roleId);
		
	}

	public Role findRoleById(String roleId) {
		
		return roleMapper.findRoleById(roleId);
	}

	public void update(Role role) {
		// TODO Auto-generated method stub
		role.setUpdateTime(new Date());
		roleMapper.update(role);
	}
	/*实现思路：
	 * 向中间表插值
	 * insert into role_module_p (role_id,module_id)values(#{roleId},#{moduleId})
	 * 所以：
	 * 第一能够确定：service利用for循环，多次执行sql语句
	 * 第二，根据取值，决定用map来做
	 */
	public void insertRoleModule(String roleId, String[] moduleIds) {
		Map<String,String> map=new HashMap<>();
		map.put("roleId", roleId);
		for(String moduleId:moduleIds){
			map.put("moduleId", moduleId);
			//多次执行sql语句
			roleMapper.insertRoleModule(map);
		}
		
	}

	public List<String> findModuleIdsByRoleId(String roleId) {
		
		return roleMapper.findModuleIdsByRoleId(roleId);
	}
}
