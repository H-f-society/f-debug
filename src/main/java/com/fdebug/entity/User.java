/*
* @Author: H-f-society
* @Date:   2020-04-24 10:31:06
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-05-23 23:39:39
*/
package com.fdebug.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@TableName("user")
public class User  {
	/**
	 * 用户ID
	 */
	private Integer Id;
	/**
	 * 用户名称
	 */
	@NotBlank(message = "用户名不能为空")
	private String username;
	/**
	 * 登录密码
	 */
	private String password;
	@Email(message = "邮箱格式不正确")
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 移动电话
	 */
	private String phone;
	/**
	 * 用户头像
	 */
	private String headImg;
	/**
	 * 上次登录IP地址
	 */
	private String ipAddress;
	/**
	 * 点赞过的文章ID
	 */
	private String praiseArtId;
	/**
	 * 用户类型，0普通用户，1管理员
	 */
	private Integer type; 
	/**
	 * 用户状态，0启用，1禁用，2删除
	 */
	private Integer status;
	/**
	 * 用户创建时间
	 */
	private String createTime;
	/**
	 * 用户信息更新时间
	 */
	private String updateTime;
	/**
	 * 用户软删除时间
	 */
	private String deleteTime;

	public String getUsername() 	{ return this.username; }
	public String getPassword() 	{ return this.password; }
	public String getEmail() 		{ return this.email; }
	public String getPhone() 		{ return this.phone; }
	public String getHeadImg() 		{ return this.headImg; }
	public String getIpAddress()	{ return this.ipAddress; }
	public String getPraiseArtId()  {return this.praiseArtId; }
	public Integer getType() 		{ return this.type; }
	public String getCreateTime() 	{ return this.createTime; }
	public String getUpdateTime() 	{ return this.updateTime; }
	public String getDeleteTime() 	{ return this.deleteTime; }

	/**
	 * 用户登录所需验证码
	 */
	private String validateCode;
	public String getValidateCode() { return this.validateCode; };
}