/*
* @Author: HJJ
* @Date:   2018-12-29 02:38:21
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-05-26 04:14:25
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
var aListNum = 0;
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
			url: "loadArticleListRequest",
			type: "post",
			contentType: "application/json",
			data: { aListNum: aListNum },
			success: function(data) {
				if(data == null) return;
				for(var i=0; i<data.length; i++) {
					$("#article_list").append(
						'<div class="article_list">'+
							'<span style="display:none;" class="article_id">'+data[i]["id"]+'</span>'+
							'<img src="images/header/'+data[i]["headImg"]+'" title="'+data[i]["author"]+'">'+
							'<a href="/articleInfo?articleId='+data[i]["id"]+'">'+
							'<h4 title="'+data[i]["title"]+'--------'+data[i]["createTime"]+'" class="blog_title">'+data[i]["title"]+'</h4></a>'+
							'<span style="color:#044AFB;" title="阅读量">'+data[i]["clickRate"]+'</span>'+
							'<span style="color:red;" title="点赞">❤ '+data[i]["praise"]+'</span>'+
						'</div>'
					);
				}
			},
			error: function(){console.log(false, ">>>");}
		});
		aListNum += 10;
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