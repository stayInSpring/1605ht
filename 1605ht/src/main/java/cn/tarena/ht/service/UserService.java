package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public List<User> findAllUser() {
		
		return userMapper.findAllUser();
	}
	
	/*
	 * 别忘了分配主键、状态、创建日期等信息
	 */
	public void insert(User user, UserInfo userInfo) {
		// TODO Auto-generated method stub
		user.setUserId(UUID.randomUUID().toString());
		user.setState(1);//表示启用
		user.setCreateTime(new Date());
		user.setUpdateTime(user.getCreateTime());
		userMapper.insertUser(user);
		//因为用户和用户信息是一对一，外键是两个表的主键，所以主键值要保持一致
		userInfo.setUserInfoId(user.getUserId());
		userInfo.setCreateTime(new Date());
		userInfo.setUpdateTime(userInfo.getCreateTime());
		userMapper.insertUserInfo(userInfo);
		
		
	}
	/*
	 * 删除的时候，两张表都要删，避免出现僵尸数据
	 */
	public void deleteById(String userId) {
		// TODO Auto-generated method stub
		userMapper.deleteUser(userId);
		userMapper.deleteUserInfo(userId);
	}

	public User findRoleOfUserId(String userId) {
		
		return userMapper.findRoleOfUserId(userId);
	}

	public User findUserByUsername(String username) {
		
		return userMapper.findUserByUsername(username);
	}

	public User findModulesOfUser(String username) {
		
		return userMapper.findModulesOfUser(username);
	}

}
