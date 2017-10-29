package com.sms.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.user.service.UserService;
import com.sms.user.dao.UserDao;
import com.sms.user.model.UserInfo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public UserInfo userPasswordChng(UserInfo userInfo) {
		return userDao.userPasswordChng(userInfo);
	}
	

}
