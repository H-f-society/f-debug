/*
* @Author: HJJ
* @Date:   2018-11-24 23:24:42
* @Last Modified by:   root
* @Last Modified time: 2020-04-27 17:00:32
*/
window.onload = function(){ 
	//code();//界面刷新时执行code()函数
	$("#login").toggle("puff");
}
/*function code(){
	var code = document.getElementById("code-num");
	code.value = parseInt(Math.floor(Math.random()*9000)+1000);
	code.style.backgroundColor = ran();
}*/
function ran(){
	var c = "#";
	for(var i=0; i<6; i++){
		c += parseInt(Math.random()*16).toString(16);
	}
	return c;
}
$(document).ready(function(){
	$("#h-login").click(function(){
		$("#login").toggle("puff");
		$("#register").hide("puff");
	});
	$("#h-register").click(function(){
		$("#register").toggle("puff");
		$("#login").hide("puff");
	});

	
	$("#btn_login").click(function() {
		login();
		return false;
	});
	$("#btn_register").click(function() {
		register();
		return false;
	});
	function login() {
		var data = {
				username: $(".L_username").val(),
				password: hex_md5($(".L_password").val()),
				validateCode: $(".code").val()
		};
		$.ajax({
			url: "LoginRequest",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),
			success: function(result) {
				if(result == true) window.location.href = "index";
				else {
					CreateValidateCode();
					alert("登录信息有误!");
				}
			},
			error: function(){console.log(false, ">>>");}
		});
	}
	function register() {
		if($(".R_username").val()=="" || $(".R_password").val()=="" || $(".R_againpwd").val()=="" || $(".R_email").val()=="") 
			return;
		var data = {
				username: $(".R_username").val(),
				password: hex_md5($(".R_password").val()),
				email: $(".R_email").val()
		};
		$.ajax({
			url: "RegisterRequest",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),
			success: function(result) {
				console.log(result);
				if(result == true) alert("注册成功，请前往登录!");
				else alert("注册失败，用户名或邮箱已被注册!");
			},
			error: function(){console.log(false, ">>>");}
		});
	}
	$("#ValidateCode").click(function() { CreateValidateCode(); });
	function CreateValidateCode() {
		$.ajax({
			url: "/CreateValidateCode",
			type: "get",
			success: function(data) {
				$("#ValidateCode").attr("src", "/CreateValidateCode");
			},
			error: function(){console.log(false, ">>>");}
		});
	}
});













