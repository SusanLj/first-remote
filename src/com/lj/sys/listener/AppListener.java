package com.lj.sys.listener;
/*
 *实现 ServletContextListener这个接口会随着web应用启动而作用
 *当前这个类的作用是确保Servlet容器创建成功，
 *即web应用启动成功，并且把web路径放入作用域中
 *在这个类上面加上@WebListener，这个注解，使用@WebListener进行标注，这样Web容器就会把它当做一个监听器进行注册和使用了。
 */
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//取到ServletContext
		ServletContext context=arg0.getServletContext();
		context.setAttribute("ctx", context.getContextPath());
		System.err.println("---------Servlet容器创建成功 ctx被放到ServletContext作用域-------");
	}

}
