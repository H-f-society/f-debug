/*
* @Author: H-f-society
* @Date:   2020-05-20 16:27:25
* @Last Modified by:   root
* @Last Modified time: 2020-05-21 14:23:12
*/
package com.fdebug.entity;

import java.util.Date;

public class Article {
	/**
	 * 文章ID
	 */
	private Integer id;
	/**
	 * 关联用户ID
	 */
	private Integer userId;
	/**
	 * 文章标题
	 */
	private String title;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 点赞数
	 */
	private Integer praise;
	/**
	 * 点击率
	 */
	private Integer clickRate;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 删除时间
	 */
	private Date deleteTime;

	public Integer getId() 			{ return this.id; }
	public Integer getUserId() 		{ return this.userId; }
	public String getTitle() 		{ return this.title; }
	public String getContent() 		{ return this.content; }
	public Integer getPraise() 		{ return this.praise; }
	public Integer getClickRate()	{ return this.clickRate; }
	public Date getCreateTime() 	{ return this.createTime; }
	public Date getUpdateTime() 	{ return this.updateTime; }
	public Date getDeleteTime() 	{ return this.deleteTime; }


	/**
	 * 文章关联用户名
	 */
	private String username;
	/**
	 * 文章关联用户头像
	 */
	private String headImg;
	public String getUsername() { return this.username; }
	public String getHeadImg()  { return this.headImg; }
}