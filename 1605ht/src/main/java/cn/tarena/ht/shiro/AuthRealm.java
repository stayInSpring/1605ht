package cn.tarena.ht.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;

public class AuthRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	
	/* 用于用户授权管理的
	 * 这个方法用于提交用户授权相关的资料
	 * 授权管理：
	 * 应该是根据当前的用户信息，查询对应有哪些权限
	 * 然后组织成一个权限信息集合，交给shiro
	 * 实现思路：
	 * 1.想办法拿到当前的用户的用户名
	 * 2.根据去查对应的用户信息，主要要查有哪些权限信息
	 * 3.组织成权限集合，交给shiro,shiro做授权管理
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		Subject subject=SecurityUtils.getSubject();
		List<String> list=new ArrayList<>();
		String username=(String) subject.getSession().getAttribute("username");
		
		System.out.println(username);
		
		User user=userService.findModulesOfUser(username);
	
		List<Module> moduleList=user.getModules();
		if(moduleList.size()>0){
		for(Module m:moduleList){
			list.add(m.getName());
			}
		}
		
		
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addStringPermissions(list);
		return info;
	}
	/* 用于用户登录验证的方法
	 * 实现思路：
	 * 这个方法是用来提交用户登录验证的资料的方法
	 * 1.先从token里拿到当前登录用户输入的登录名
	 * 2.根据登录名，去数据库查，查出对应的对象信息
	 * 3.提交的资料：①对象信息（查出来的User)，②真实密码（数据库里的密码），③当前AuthRealm的名字
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken loginToken=(UsernamePasswordToken) token;
		//拿到当前用户输入的登录名
		String username=loginToken.getUsername();
		User user=userService.findUserByUsername(username);
		AuthenticationInfo info=new SimpleAuthenticationInfo(user,
				user.getPassword(),this.getName());
		//告诉shiro，可以做相关的登录验证了
		return info;
	}

}
