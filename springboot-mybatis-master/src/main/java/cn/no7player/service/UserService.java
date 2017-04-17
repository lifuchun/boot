package cn.no7player.service;

import cn.no7player.mapper.UserMapper;
import cn.no7player.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zl on 2015/8/27.
 */

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserInfo() {
		User user = userMapper.findUserInfo();
		return user;
	}

	public User getUserInfoByUser(String name) {
		User user = userMapper.getUserInfoByName(name);
		return user;
	}

}
