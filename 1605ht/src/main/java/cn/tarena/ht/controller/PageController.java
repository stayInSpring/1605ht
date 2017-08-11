package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这个类使用来定位汇通欢迎首页的
 * 需要掌握：
 * 1.学会通过Controller定位前台页面
 * 2.知道 / 拦截所有请求可能会带来的问题（静态资源文件被拦截的问题）以及相应的处理方法
 * 3.也可以配置比如*.action的这种形式，也能静态找资源被拦截的问题
 * 4.掌握利用RESTFUL的形式开发Controller方法
 * @author ysq
 *
 */
@Controller
public class PageController {
	/*
	 * 这个方法是利用RESTFUL改造页面访问
	 */
	@RequestMapping("/{pageName}")
	public String goPage(@PathVariable String pageName){
		return "/home/"+pageName;
	}
	/*
	 * 这个方法是用来定位系统管理左帧页面，对应的页面路径：WEB-INF/pages/sysadmin/left.jsp
	 * 对应的后台请求路径：/sysadmin/left
	 */
	@RequestMapping("/sysadmin/left")
	public String goSysAdminLeft(){
		return "/sysadmin/left";
	}
	/*
	 * 这个方法是用来定位系统管理主帧页面，对应的页面路径：WEB-INF/pages/sysadmin/main.jsp
	 * 对应的后台请求路径： /sysadmin/main
	 */
	@RequestMapping("/sysadmin/main")
	public String goSysAdminMain(){
		return "/sysadmin/main";
	}
	
//	/*
//	 * 这个方法用来定位 欢迎首页，对应的前台页面的路径：
//	 * WEB-INF/pages/home/index.jsp
//	 * index.jsp用的是帧框架技术，把页面分成了三个帧，分别是top,left,main,
//	 * 每个帧分别对应一个jsp页面
//	 */
//	@RequestMapping("/index")
//	public String goIndex(){
//		//返回的视图逻辑名要根据视图解析器来确定
//		///WEB-INF/pages
//		return "/home/index";
//	}
//	/*
//	 * 这个方法是用来定位 顶帧的，对应前台页面：WEB-INF/pages/home/title.jsp
//	 * 对应的后台访问路径：/title
//	 */
//	@RequestMapping("/title")
//	public String goTitle(){
//		return "/home/title";
//	}
//	/*
//	 * 这个方法是用来定位 左帧的，对应前台页面：WEB-INF/pages/home/left.jsp
//	 * 对应的后台访问路径：/left
//	 */
//	@RequestMapping("/left")
//	public String goLeft(){
//		return "/home/left";
//	}
//	/*
//	 * 这个方法是用来定位 主帧的，对应前台页面：WEB-INF/pages/home/main.jsp
//	 * 对应的后台访问路径：/main
//	 */
//	@RequestMapping("/main")
//	public String goMain(){
//		return "/home/main";
//	}

}
