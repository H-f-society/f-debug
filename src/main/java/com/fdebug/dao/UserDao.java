/*
* @Author: H-f-society
* @Date:   2020-04-24 11:22:12
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-04-26 13:57:07
*/
package com.fdebug.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.fdebug.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
	/**
	 * 通过用户名或邮箱查找该用户是否存在
	 * @author root 2020-04-26
	 * @param  username 	用户名称
	 * @return 1 or 0              
	 */
	@Select("SELECT count(*) FROM user WHERE username=#{username} or email=#{username}")
	Integer findUserByUsernameOrEmail(
		@Param("username") String username
	);
	/**
	 * 通过用户名或邮箱获取该用户信息
	 */
	@Select("SELECT Id, username, email, phone, ipAddress, type, createTime, updateTime from user "+
			" WHERE username=#{username} or email=#{username}")
	User getUserInfoByUsernameOrEmail(
		@Param("username") String username
	);

	/**
	 * 验证用户名或邮箱与密码是否匹配
	 * @author root 2020-04-26
	 * @param  password 	登录密码
	 * @return 1 or 0
	 */
	@Select("SELECT count(*) from user WHERE (username=#{username} or email=#{username}) and password=#{password}")
	Integer checkUserLogin(
		@Param("username") String username,
		@Param("password") String password
	);

	/**
	 * 更新登录用户的IP地址
	 */
	@Update("UPDATE user SET ipAddress=#{ipAddress} WHERE username=#{username}")
	Integer updateLoginIpAddr(
		@Param("ipAddress") String ipAddress,
		@Param("username") String username
	);

	/**
	 * 添加用户至user表
	 * @author root 2020-04-26
	 * @param  username     用户名称
	 * @param  password     登录密码
	 * @param  email 		电子邮箱
	 * @return 1 or 0  
	 */
	@Insert("INSERT INTO user(username, password, email) values(#{username}, #{password}, #{email})")
	Integer addUser(
		@Param("username") String username,
		@Param("password") String password,
		@Param("email") String email
	);


}