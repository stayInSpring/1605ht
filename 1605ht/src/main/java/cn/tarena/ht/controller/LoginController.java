package cn.tarena.ht.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.StringUtils;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	/*
	 * 这个方法是利用shiro实现用户登录验证
	 * 实现思路：
	 * 1.做用户名和密码的非空校验
	 * 2.调用shiro的Subject对象的login方法执行登录验证
	 * 3.当执行login方法的时候，首先会找的shiro的securityManager，
	 * 4.sercurityManager会找到自定义的authRealm类
	 * 5.如果是登录验证的话，就会执行authRealm类里的Authtacation方法
	 * 6.在这个方法里，提交资料
	 */
	@RequestMapping("/validate/doLogin")
	public String validate(String username,String password,HttpSession session){
		if(StringUtils.isEmptyOrWhitespaceOnly(username)
		||StringUtils.isEmptyOrWhitespaceOnly(password)){
		//如果不通过，重定向到login.jsp
		//这个代码是为了和前台的校验信息做交互的，2代表空值的提示
		//model.addAttribute("loginFailed",2);
		session.setAttribute("loginFailed", 2);
		return "redirect:/login.jsp";
		}
		//调用shiro的API
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		//subject在安全界领域代表当前用户的视图，表示和当前程序交互的对象，不一定是一个人（User),
		//也可能是一个线程或程序
		Subject subject=SecurityUtils.getSubject();
		//执行login方法，这个方法会抛异常，如果登录验证没通过，比如用户名或密码错误，就会抛异常
		//我们可以根据异常来做相关的转发或重定向处理
		try {
			subject.login(token);
			subject.getSession().setAttribute("username", username);
			return "forward:/index";
		} catch (Exception e) {
			// TODO: handle exception
			//抛异常走到这里，说明验证没通过，重新登录
			session.setAttribute("loginFailed", 1);
			return "redirect:/login.jsp";
		}
		
	}
	
//	/*
//	 * 这个方法是做用户登录验证的方法，对应后台请求的路径： /validate/doLogin
//	 * 需要注意的地方：
//	 * 通过重定向做的页面跳转，两次请求两次转发，所以如果利用model传的话，值会丢失
//	 * 所以可以通过session来传。
//	 */
//	@RequestMapping("/validate/doLogin")
//	public String validate(String username,String password,Model model,
//			HttpSession session){
//		//1.非空校验
//		if(StringUtils.isEmptyOrWhitespaceOnly(username)
//				||StringUtils.isEmptyOrWhitespaceOnly(password)){
//			//如果不通过，重定向到login.jsp
//			//这个代码是为了和前台的校验信息做交互的，2代表空值的提示
//			//model.addAttribute("loginFailed",2);
//			session.setAttribute("loginFailed", 2);
//			return "redirect:/login.jsp";
//		}
//		User user=userService.findUserByUsername(username);
//		if(user!=null){
//			if(user.getPassword().equals(password)){
//				//如果相当，证明通过密码校验,就转向欢迎页面
//				return "forward:/index";
//			}
//		}
//		//这个代码是为了和前台的校验信息做交互的，1代表用户名或密码错误的提示
//		//model.addAttribute("loginFailed",1);
//		session.setAttribute("loginFailed", 1);
//		return "redirect:/login.jsp";
//		
//	}
}
