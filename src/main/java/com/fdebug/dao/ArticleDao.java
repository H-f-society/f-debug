/*
* @Author: H-f-society
* @Date:   2020-05-20 17:07:48
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-05-24 23:12:53
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
	 * @param  aListNum	[文章列表编号]
	 */
	@Select(
		"SELECT "+
			"u.headImg, "+
			"a.author, a.Id, a.title, a.content, a.createTime, a.praise, a.clickRate "+
		"FROM "+
			"user u RIGHT JOIN article a ON u.username=a.author "+ 
		"ORDER BY a.Id DESC LIMIT #{aListNum}, 10"
	)
	List<Article> getArticleList(
		@Param("aListNum") Integer aListNum
	);

	/**
	 * 获取点击率前10的文章列表
	 * @author root 2020-05-22
	 * @return List<Article>
	 */
	@Select(
		"SELECT "+
			"u.headImg, "+
			"a.author, a.Id, a.title "+
		"FROM "+
			"user u RIGHT JOIN article a ON u.username=a.author "+
		"ORDER BY a.clickRate DESC LIMIT 10")
	List<Article> getArticleListTop10();

	/**
	 * 通过ID获取文章详细信息
	 * @param  articleId  [文章Id]
	 */
	@Select(
		"SELECT "+
			"u.headImg, "+
			"a.author, a.Id, a.title, a.content, a.createTime, a.praise, a.clickRate "+
		"FROM "+
			"user u RIGHT JOIN article a ON u.username=a.author "+
		"WHERE a.Id=#{articleId}"
	)
	Article getArticleInfoById(
		@Param("articleId") Integer articleId
	);

	/**
	 * 对数据表插入文章信息
	 * @author root 2020-05-23
	 * @param  author 	[用户名]
	 * @param  title    [文章标题]
	 * @param  content  [文章内容]
	 * @return          [true or false]
	 */
	@Insert(
		"INSERT INTO "+
			"article (author, title, content) " +
			"values (#{author}, #{title}, #{content})"
	)
	Boolean InsertArticleInfo(
		@Param("author") String author,
		@Param("title") String title,
		@Param("content") String content
	);

	/**
	 * 根据Id更新文章点击率
	 * @param  articleId  [文章Id]
	 */
	@Update(
		"UPDATE article SET " +
			"clickRate=clickRate+1 " +
		"WHERE Id=#{articleId}"
	)
	void updateClickRateById(
		@Param("articleId") Integer articleId
	);
}