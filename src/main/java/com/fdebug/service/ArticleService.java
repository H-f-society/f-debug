/*
* @Author: H-f-society
* @Date:   2020-05-21 14:33:14
* @Last Modified by:   root
* @Last Modified time: 2020-05-22 17:09:35
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
	 * 过去点击率前10的文章列表
	 * @author root 2020-05-21
	 * @return List<Article>
	 */
	public List<Article> getArticleListTop10() {
		return this.articleDao.getArticleListTop10();
	}

	/**
	 * 通过ID获取文章详细信息
	 * @author root 2020-05-22
	 * @param  articleId 文章ID
	 * @return           Article
	 */
	public Article getArticleInfoById(Integer articleId) {
		return this.articleDao.getArticleInfoById();
	}
}