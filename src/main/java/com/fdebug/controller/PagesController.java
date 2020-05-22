/*
* @Author: root
* @Date:   2020-04-21 10:38:20
* @Last Modified by:   root
* @Last Modified time: 2020-05-22 13:15:42
*/
package com.fdebug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

	/**
	 * WEB主页请求地址
	 * @author root 2020-04-26
	 * @return "index"
	 */
	@RequestMapping("/index")
	public String index() { return "index"; }

	/**
	 * 登录页面请求地址
	 * @author root 2020-04-26
	 * @return "subpages/login"
	 */
	@RequestMapping("/login")
	public String login() { return "subpages/login"; }

	/**
	 * 论坛编辑文章
	 * @author root 2020-04-27
	 * @return subpages/writeBlog
	 */
	@RequestMapping("/writeBlog")
	public String writeBlog() { return "subpages/writeBlog"; }

	/**
	 * 下载页面
	 * @author root 2020-05-22
	 * @return subpages/download
	 */
	@RequestMapping("/download")
	public String download() { return "subpages/download"; }

}
