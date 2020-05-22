/*
* @Author: HJJ
* @Date:   2018-12-29 02:38:21
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-02-24 22:59:50
*/
$(function() {
	$( document ).tooltip({
		track: true
	});
	$(".blog_title, .top").tooltip({
	//$(document).tooltip({
		show: {
	        effect: "explode",
	        delay : 250
		}
	});
});
var AlistNum = 0;
$(document).ready(function() {
	$(".write_blog").click(function(){
		$(".Write-blog").toggle("fold", 1000);
	});
	$(".loading").click(function() {
		getArticleList();
	});
	getArticleList();
	function getArticleList() {
		$.ajax({
			url: "getArticleListRequest",
			type: "post",
			contentType: "application/json",
			data: { alistNum: AlistNum },
			success: function(data) {
				if(data == null) return;
				for(var i=0; i<data.length; i++) {
					$("#article_list").append(
						'<div class="article_list">'+
							'<span style="display:none;" class="article_id">'+data[i]["id"]+'</span>'+
							'<img src="images/header/'+data[i]["header_img"]+'" title="'+data[i]["username"]+'">'+
							'<a href="/articleInfo?id='+data[i]["id"]+'">'+
							'<h4 title="'+data[i]["title"]+'--------'+data[i]["time"]+'" class="blog_title">'+data[i]["title"]+'</h4></a>'+
							'<span style="color:#044AFB;" title="阅读量">'+data[i]["praise"]+'</span>'+
							'<span style="color:red;" title="点赞">❤ '+data[i]["click_rate"]+'</span>'+
						'</div>'
					);
				}
			},
			error: function(){console.log(false, ">>>");}
		});
		AlistNum += 10;
	}
	ArticleTopList();
	function ArticleTopList() {
		$.ajax({
			url: "ArticleTopListRequest",
			type: "post",
			contentType: "application/json",
			success: function(data) {
				for(let i=0; i<10; i++) {
					$(".recommend").append(
							"<div class='recommend_list'>" +
								"<img src='images/header/"+data[i]["header_img"]+"' title='"+data[i]["username"]+"'>" +
								"<a href='/articleInfo?id="+data[i]["id"]+"' title='"+data[i]["title"]+"'>" +
									"<h5>" + data[i]["title"] + "</h5>" +
								"</a>" +
							"</div>"
					);
				}
			},
			error: function(){console.log(false, ">>>");}
		});
	}

	$(window).scroll(function() {
  
	    if ($(document).scrollTop()<=0){
	      //console.log("滚动条已经到达顶部为0");
	    }
	  
	    if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	      //console.log("滚动条已经到达底部为" + $(document).scrollTop());
	      getArticleList();
	    }
	});
});