package com.lj.sys.service;

import com.lj.sys.domain.User;
import com.lj.sys.vo.UserVo;

public interface UserService {
		User login(UserVo userVo);
}
