/*
* @Author: root
* @Date:   2020-04-21 10:38:20
* @Last Modified by:   root
* @Last Modified time: 2020-04-21 10:43:16
*/
package com.fdebug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

	@RequestMapping("/index")
	public String index() { return "index"; }

}