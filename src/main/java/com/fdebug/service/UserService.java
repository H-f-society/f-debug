/*
* @Author: H-f-society
* @Date:   2020-04-24 11:58:34
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-04-26 13:57:56
*/
package com.fdebug.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

import com.fdebug.entity.User;
import com.fdebug.dao.UserDao;

@Service
public class UserService{
	@Autowired
	private UserDao userDao;

	/**
	 * 通过用户名或邮箱获取该用户信息
	 * @author root 2020-04-26
	 * @param  username 用户名称
	 * @return user
	 */
	public User getUserInfoByUsernameOrEmail(String username) {
		return userDao.getUserInfoByUsernameOrEmail(username);
	}

	/**
	 * 验证用户名或邮箱与密码是否匹配
	 * @author root 2020-04-26
	 * @param  username 用户名称
	 * @param  password 登录密码
	 * @return boolean
	 */
	public boolean checkUserLogin(String username, String password) {
		return userDao.checkUserLogin(username, password) != 0 ? true : false;
	}

	/**
	 * 更新登录用户的IP地址
	 * @author root 2020-04-26
	 * @param  ipAddress IP地址
	 * @return boolean
	 */
	public boolean updateLoginIpAddr(String ipAddress, String username) {
		return userDao.updateLoginIpAddr(ipAddress, username) != 0 ? true : false;
	}

	/**
	 * 添加用户至user表
	 * @author root 2020-04-26
	 * @param  username 用户名称
	 * @param  password 登录密码
	 * @param  email    电子邮箱
	 * @return boolean
	 */
	public boolean addUser(String username, String password, String email) {
		if((userDao.findUserByUsernameOrEmail(username) == 0) && (userDao.findUserByUsernameOrEmail(email) == 0)) {
			return userDao.addUser(username, password, email) != 0 ? true : false;
		}
		return false;
	}
}