/*
* @Author: H-f-society
* @Date:   2020-05-20 16:27:25
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-05-23 23:39:46
*/
package com.fdebug.entity;


public class Article {
	/**
	 * 文章ID
	 */
	private Integer id;
	/**
	 * 关联用户ID
	 */
	private String author;
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
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 删除时间
	 */
	private String deleteTime;

	public Integer getId() 			{ return this.id; }
	public String getAuthor() 		{ return this.author; }
	public String getTitle() 		{ return this.title; }
	public String getContent() 		{ return this.content; }
	public Integer getPraise() 		{ return this.praise; }
	public Integer getClickRate()	{ return this.clickRate; }
	public String getCreateTime() 	{ return this.createTime; }
	public String getUpdateTime() 	{ return this.updateTime; }
	public String getDeleteTime() 	{ return this.deleteTime; }


	/**
	 * 文章关联用户头像
	 */
	private String headImg;
	public String getHeadImg()  { return this.headImg; }
}