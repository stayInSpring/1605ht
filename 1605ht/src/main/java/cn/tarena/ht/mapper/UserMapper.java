package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserMapper {

	List<User> findAllUser();

	void insertUser(User user);

	void insertUserInfo(UserInfo userInfo);

	void deleteUser(String userId);

	void deleteUserInfo(String userId);

	User findRoleOfUserId(String userId);

	User findUserByUsername(String username);

	User findModulesOfUser(String username);

}
