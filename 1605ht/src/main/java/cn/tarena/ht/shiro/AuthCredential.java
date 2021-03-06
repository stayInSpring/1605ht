package cn.tarena.ht.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import cn.tarena.ht.utils.Md5HashAuth;

public class AuthCredential extends SimpleCredentialsMatcher{
	 * shiro在做用户登录验证的时候，shiro要把用户输入的密码和用户的真实密码（数据库密码）作比对
	 * 我们的目的是，需要把用户输入的密码（123456）利用加密算法，得到密文，然后去和真实密码作比对
	 * 实现步骤：
	 * 1.从token里拿出当前用户输入的密码（即123456）
	 * 2.通过Md5hash对123456加密，得到密文
	 * 3.得到相应的密文之后，再把这个密文放回到token里，然后交给shiro,shiro做校验工作
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// TODO Auto-generated method stub
		UsernamePasswordToken loginToken=(UsernamePasswordToken) token;
		//相当于拿到123456
		String loginPassword=String.valueOf(loginToken.getPassword());
		String loginUsername=loginToken.getUsername();
		String credentialPassword=Md5HashAuth.md5(loginPassword,loginUsername);
		//把加密后的密文放回到token里
		loginToken.setPassword(credentialPassword.toCharArray());
		//因为loginToken装着加密后的密文，所以把他交给shiro来处理
		return super.doCredentialsMatch(loginToken, info);
	}
}
