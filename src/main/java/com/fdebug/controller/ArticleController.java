/*
* @Author: H-f-society
* @Date:   2020-05-20 16:25:04
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-05-23 18:14:39
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdebug.entity.Article;
import com.fdebug.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	/**
	 * 文章主页
	 * @author root 2020-05-22
	 * @param  model [description]
	 * @return       [subpages/forum]
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
	 * @return           [subpages/articleInfo]
	 */
	@RequestMapping("/articleInfo")
	public String articleInfo(Model model, String articleId) {
		List<Article> articleList = this.articleService.getArticleListTop10();
		model.addAttribute("articleListTop10", articleList);

		Article artInfo = this.articleService.getArticleInfoById(Integer.valueOf(articleId));
		model.addAttribute("artInfo", artInfo);
		return "subpages/articleInfo";
	}

	/**
	 * 处理用户发布文章时的请求
	 * @author root 2020-05-23
	 * @param  article   [文章对象]
	 * @param  request   [description]
	 * @param  response  [description]
	 * @return           [true or false]
	 * @throws Exception [description]
	 */
	@RequestMapping(value="subBlogRequest", method=RequestMethod.POST)
	@ResponseBody
	public Boolean subBlogRequest(@RequestBody Article article, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!this.getCookieOnUsername(request, response).equals("") && article.getTitle()!=null && article.getContent()!=null) {
			return this.articleService.InsertArticleInfo(
				this.getCookieOnUsername(request, response),
				article.getTitle(),
				article.getContent()
			);
		}
		return false;
	}

	/**
	 * 加载部分文章列表请求
	 * @author root 2020-05-23
	 * @param  aListNum [文章列表编号]
	 * @return          [description]
	 */
	@RequestMapping(value="loadArticleListRequest", method=RequestMethod.POST)
	@ResponseBody
	public List<Article> loadArticleListRequest(@RequestBody String data) throws Exception {
		
		int aListNum = 0; //data为alistNum=10，暂时找不到方法只发10过来就干脆手动提取吧。。。
		for(int i=data.indexOf('=')+1; i<data.length(); i++) aListNum = aListNum * 10 + (data.charAt(i) - '0');
		
		System.out.println("aListNum: " + aListNum);
		return this.articleService.getArticleList(aListNum);
	}

	/**
	 * 获取COOKIES中的用户名
	 * @author root 2020-04-26
	 * @param  request  [description]
	 * @param  response [description]
	 */
	public String getCookieOnUsername(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
	        for(Cookie result : cookies){
	            if(result.getName().equals("username")){
	                System.out.println("cookie username: " + result.getValue());
	                return result.getValue();
	            }
	        }
	    }
	    return "";
	}
}