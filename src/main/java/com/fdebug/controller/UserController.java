/*
* @Author: H-f-society
* @Date:   2020-04-22 03:21:41
* @Last Modified by:   root
* @Last Modified time: 2020-04-27 17:14:01
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
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdebug.entity.User;
import com.fdebug.entity.ValidateCode;
import com.fdebug.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 创建登录所需验证码
	 * @author root 2020-04-26
	 * @param  request  [description]
	 * @param  response [description]
	 */
	@RequestMapping("/CreateValidateCode")
	@ResponseBody
	public void CreateValidateCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/png");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setHeader("Expire", "0");
	        response.setHeader("Pragma", "no-cache");

			ValidateCode validateCode = new ValidateCode();
			validateCode.getValidateCodeImage(request, response);

			System.out.println("session: " + request.getSession().getAttribute("VALIDATECODE"));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 登录请求处理
	 * @author root 2020-04-26
	 * @param  user      [description]
	 * @param  request   [description]
	 * @param  response  [description]
	 * @return boolean   
	 * @throws Exception [description]
	 */
	@RequestMapping(value = "/LoginRequest", method = RequestMethod.POST)
	@ResponseBody
	public boolean LoginRequest(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String validateCode = (String)request.getSession().getAttribute("VALIDATECODE");
		if(userService.checkUserLogin(user.getUsername(), user.getPassword()) && validateCode.equals(user.getValidateCode())) {
			User userInfo = userService.getUserInfoByUsernameOrEmail(user.getUsername());
			
			Cookie cookie = new Cookie("username", userInfo.getUsername());
			cookie.setMaxAge(7 * 24 * 60 * 60);
			response.addCookie(cookie);

			userService.updateLoginIpAddr(getIpAddr(request), userInfo.getUsername());

			return true;
		}
		return false;
	}
	@RequestMapping(value = "/RegisterRequest", method = RequestMethod.POST)
	@ResponseBody
	public boolean RegisterRequest(@RequestBody User user) {
		return userService.addUser(user.getUsername(), user.getPassword(), user.getEmail());
	}

	/**
	 * 获取COOKIES中的用户名
	 * @author root 2020-04-26
	 * @param  request  [description]
	 * @param  response [description]
	 */
	@RequestMapping(value = {"/getCookieOnUsername"})
	@ResponseBody
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
	/**
	 * 退出登录，清楚COOKIE中的username
	 * @author root 2020-04-27
	 * @param  request  [description]
	 * @param  response [description]
	 * @return subpages/login
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("username", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "subpages/login";
	}

	/**
	 * 获取登录请求方IP地址
	 * @author root 2020-04-26
	 * @param  request [description]
	 * @return ipAddress
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
   }

}