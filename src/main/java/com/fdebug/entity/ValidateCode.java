/*
* @Author: H-f-society
* @Date:   2020-04-22 01:48:56
* @Last Modified by:   root
* @Last Modified time: 2020-04-26 10:04:16
*/
package com.fdebug.entity;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.text.Document;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateCode {
	private int imgWidth  = 120;//图片宽度
	private int imgHeight = 42;	//图片高度
	private int lineSize  = 30;	//验证码干扰线数量
	private int codeLen   = 6;	//验证码字符长度
	private String validateCode = "";
	private String codeString   = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String sessionKey = "VALIDATECODE"; //session关键字

	public String getValidateCode() { return this.validateCode; }


	private Font getFont() {
		return new Font("Lucida Handwriting", Font.PLAIN, 27);
	}
	/**
	 * 获取随机颜色值
	 * @author root 2020-04-26
	 * @return [description]
	 */
	private static Color getRandomColor() {
		int r = (int)(Math.random()*(255));
		int g = (int)(Math.random()*(255));
		int b = (int)(Math.random()*(255));
		return new Color(r, g, b);
	}
	/**
	 * 获取随机字符串
	 * @author root 2020-04-26
	 */
	private void getRandomString() {
		validateCode = "";
		for(int i=0; i<codeLen; i++) {
			validateCode += codeString.charAt((int)(Math.random()*(codeString.length() + 1)));
		}
	}
	/**
	 * 获取验证码图片
	 * @author root 2020-04-26
	 * @param  request  [description]
	 * @param  response [description]
	 */
	public void getValidateCodeImage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		try{
			getRandomString();
			g2d.setFont(getFont());
			g2d.setBackground(getRandomColor());
			g2d.clearRect(0, 0, imgWidth, imgHeight);
        	g2d.drawRect(-1, -1, imgWidth+1, imgHeight+1);
			g2d.setColor(getRandomColor());
			g2d.drawString(validateCode, 0, 30);  
			ImageIO.write(img, "PNG", response.getOutputStream());
			
			session.removeAttribute(sessionKey);
			session.setAttribute(sessionKey, validateCode);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}