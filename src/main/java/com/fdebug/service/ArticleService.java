/*
* @Author: H-f-society
* @Date:   2020-05-21 14:33:14
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-05-23 17:16:53
*/
package com.fdebug.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.fdebug.entity.Article;
import com.fdebug.dao.ArticleDao;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	/**
	 * [getArticleList description]
	 * @author root 2020-05-23
	 * @param  aListNum [文章列表编号]
	 * @return          [List<Article>]
	 */
	public List<Article> getArticleList(Integer aListNum) {
		return this.articleDao.getArticleList(aListNum);
	}

	/**
	 * 过去点击率前10的文章列表
	 * @author root 2020-05-21
	 * @return [List<Article>]
	 */
	public List<Article> getArticleListTop10() {
		return this.articleDao.getArticleListTop10();
	}

	/**
	 * 通过ID获取文章详细信息
	 * @author root 2020-05-22
	 * @param  articleId [文章ID]
	 * @return           [Article]
	 */
	public Article getArticleInfoById(Integer articleId) {
		return this.articleDao.getArticleInfoById(articleId);
	}

	/**
	 * 对数据表插入文章信息
	 * @author root 2020-05-23
	 * @param  author  [用户名]
	 * @param  title   [文章标题]
	 * @param  content [文章内容]
	 * @return         [true or false]
	 */
	public Boolean InsertArticleInfo(String author, String title, String content) {
		return this.articleDao.InsertArticleInfo(author, title, content);
	}
}