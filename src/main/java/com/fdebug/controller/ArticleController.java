/*
* @Author: H-f-society
* @Date:   2020-05-20 16:25:04
* @Last Modified by:   root
* @Last Modified time: 2020-05-22 16:40:57
*/
package com.fdebug.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.fdebug.entity.Article;
import com.fdebug.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	/**
	 * 文章主页，
	 * @author root 2020-05-22
	 * @param  model [description]
	 * @return       subpages/forum
	 */
	@RequestMapping("/forum")
	public String forum(Model model) { 
		List<Article> articleList = this.articleService.getArticleListTop10();
		model.addAttribute("articleListTop10", articleList);
		return "subpages/forum"; 
	}

	/**
	 * 文章详细信息
	 * @author root 2020-05-22
	 * @param  model     [description]
	 * @param  articleId [description]
	 * @return           [description]
	 */
	@RequestMapping("/articleInfo")
	public String articleInfo(Model model, String articleId) {
		List<Article> articleList = this.articleService.getArticleListTop10();
		model.addAttribute("articleListTop10", articleList);

		Article artInfo;
		return "subpages/articleInfo";
	}

}