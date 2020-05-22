/*
* @Author: H-f-society
* @Date:   2020-05-20 17:07:48
* @Last Modified by:   root
* @Last Modified time: 2020-05-22 17:07:53
*/
package com.fdebug.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.fdebug.entity.Article;

@Mapper
public interface ArticleDao {

	/**
	 * 按量获取文章列表
	 */
	@Select("SELECT u.username FROM user u RIGHT JOIN article a ON u.Id=a.userId ORDER BY a.Id DESC LIMIT #{num}, 10")
	List<Article> getArticleList(
		@Param("num") Integer num
	);

	/**
	 * 获取点击率前10的文章列表
	 * @author root 2020-05-22
	 * @return List<Article>
	 */
	@Select("SELECT u.username, u.headImg, a.Id, a.title FROM user u RIGHT JOIN article a ON u.Id=a.userId ORDER BY a.clickRate DESC LIMIT 10")
	List<Article> getArticleListTop10();

	/**
	 * 通过ID获取文章详细信息
	 */
	@Select("SELECT * FROM user u RIGHT JOIN article a ON u.Id=a.userId WHERE Id=#{articleId}")
	Article getArticleInfoById(
		@Param("articleId") Integer articleId
	);
}