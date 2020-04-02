package com.lj.sys.service.impl;
/*
 * 用户登录的实现直接使用一个查询语句，只要查询条件是用户名和登录密码就好了。
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.lj.sys.domain.User;
import com.lj.sys.mapper.UserMapper;
import com.lj.sys.service.UserService;
import com.lj.sys.vo.UserVo;

//@Service其实和@Component注解的作用差不多，都是将这个类放入到spring容器中。更加语义化。
@Service
public class UserServiceImpl implements UserService {
	
	//在spring的配置文件中配置了自动扫描指定的包，所以可以自动填充，而不需要一个个的配置bean。
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(UserVo userVo) {
		
		//明文 123456 md5 加密生成密文
		
		String pwd=DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
		userVo.setPwd(pwd);
		
		return userMapper.login(userVo);
	}

}
