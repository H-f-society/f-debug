/*
* @Author: zhonghongjie
* @Date:   2018-08-11 00:34:40
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-04-26 14:37:12
*/

$(document).ready(function(){
	$( document ).tooltip({ track: true });
	$("#introduction").tooltip({
		show: {
	        effect: "explode",
	        delay : 250
		}
	});
	$(".my_info li").hover(function(){
		$(this).find("div:first").stop().animate({height:'toggle'}, 350);
	});

	getCookieOnUsername();
	function getCookieOnUsername() {
		$.ajax({
			url: "getCookieOnUsername",
			type: "post",
			//contentType: "application/json",
			success: function(result) {
				console.log(result);

				if(result == "") return;
				$("#ShowUserName").html(result);
				$("#ShowUserName").attr("href", "/userInfo");
			},
			error: function(){console.log(false, ">>>");}
		});
	}
});

