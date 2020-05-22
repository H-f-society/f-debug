/*
* @Author: root
* @Date:   2019-11-22 22:13:07
* @Last Modified by:   H-f-society
* @Last Modified time: 2020-03-15 18:51:21
*/
$(document).ready(function() {	
	$( document ).tooltip({ track: true });
	$(".RoomName").tooltip({
		show: {
	        effect: "explode",
	        delay : 250
		}
	});
	$("#CreateRoom").click(function() {
		if($("#CreateWindow").is(":hidden"))
			$("#CreateWindow").toggle("bounce", {time:3}, "slow");
		else
			$("#CreateWindow").toggle("fade");
	});

	$("#RoomPWD").prop('disabled', true);
	$("#isPWD").change(function() {
		if($("#isPWD").is(':checked'))
			$("#RoomPWD").prop('disabled', false);
		else{
			$("#RoomPWD").val('');
			$("#RoomPWD").prop('disabled', true);
		}
	});

	$("#createRoomBtn").click(function() { // 创建房间
		if(localStorage.getItem("username") == null){
			alert("请先登录！");
			return;
		}
		RoomID    = hex_md5(localStorage.getItem("username") + (new Date()).toLocaleString());
		RoomName  = $("#RoomName").val() ? $("#RoomName").val() : localStorage.getItem("username");
		RoomPWD   = $("#isPWD").is(':checked') ? hex_md5($("#RoomPwd").val() + "") : null;
		GameName  = $("#GameName").val();
		GameModel = $("#GameModel").val();
		$.ajax({
			type: "POST",
			url : "script/CreateRoom.php",
			data: {
				action	  : "createRoom",
				username  : localStorage.getItem("username"),
				Header_one: localStorage.getItem("header_img"),
				RoomID	  : RoomID,
				RoomName  : RoomName,
				RoomPWD   : RoomPWD,
				GameName  : GameName,
				GameModel : GameModel
			},
			success: function(data){
				if(data == "true") {
					localStorage.setItem('RoomID', RoomID);
					localStorage.setItem('RoomName', RoomName);
					localStorage.setItem('GameName', GameName);
					localStorage.setItem('GameModel', GameModel);
					localStorage.setItem('Player', "one");				
					
					window.location.href = "GameRoom.php?RoomID=" + RoomID + "&Player=one" + "&GameName=" + GameName;
				}
			},
			error : function() {}
		});
	});
	var listNum = 0;
	loadRoomList();
	function loadRoomList() {	// 加载房间列表
		$.ajax({
			url : "getGameRoomListRequest",
			type : "POST",
			contentType: "application/json",
			data: { listNum: listNum },
			success : function(data) {
				if(data == null) return;
				for(var i=0; i<data.length; i++) {
					$("#RoomList").append(
						'<div class="RoomList">' +
							'<img src="images/gameLog/'+ data[i]["gameName"] +'.jpg" id="'+data[i]["gameName"]+'">' +
							'<span title="'+data[i]["roomName"]+'" class="RoomName"><b>'+data[i]["roomName"]+'</b></span>' +
							'<img class="header" src="images/header/'+ data[i]["header_one"] +'" title="'+ data[i]["player_one"] +'">' +
							'<b style="margin-right:0px;color:blue;float:left;"> VS </b>' +
							'<img class="header" src="images/header/'+ data[i]["header_two"] +'" title="'+ data[i]["player_two"] +'">' +
							'<button id="'+ data[i]["roomID"] +'" class="WatchWar">观战</button>' +
							'<button id="'+ data[i]["roomID"] +'" class="JoinGame">加入</button>' +
							'<span style="float:right;" class="' + data[i]["status"] + '">' + data[i]["status"] + '…</span>' +
						'</div>'
					);
				}
			},
			error : function() {console.log(false, ">>>>");}
		});
		listNum += 10;

		
		
	}
	$(".loading").click(function() { // 加载更多房间列表
		loadRoomList();
	});
	$("#RoomList").on("click", ".WatchWar", function() { // 点击观战模式按钮，进入观战
		var RoomID = this.id;
		// 判断是用户是否登录，否者以游客身份进入观战模式
		var username = localStorage.getItem("username") != null ? localStorage.getItem("username") : "Tourist";
		//console.log(username);
		localStorage.setItem("identity", "Watchwar");
		var GameName = document.getElementById(this.id).parentNode.firstChild.id;
		window.location.href = "GameRoom.php?RoomID=" + this.id + "&Player=Watchwar" + "&GameName=" + GameName;
	});
	$("#RoomList").on("click", ".JoinGame", function() { // 加入房间前判定用户是否登录
		if(localStorage.getItem("username") == null){
			alert("请先登录！");
			return;
		}
		JoinGame(this.id);
		//window.location.href = "GameRoom.php?RoomID=" + this.id + "&Player=two";
	});
	function JoinGame(RoomID) { // 加入房间
		$.ajax({
			type: "POST",
			dataType : "json",
			url : "script/CreateRoom.php",
			data: {
				action : "JoinGame",
				RoomID : RoomID,
				username : localStorage.getItem("username"),
				header_img : localStorage.getItem("header_img")
			},
			success : function(data) {
				console.log(data);
				if(data.isJoin == "false") {
					alert("博弈人数已满，请转到观战！");
				}else {
					localStorage.setItem('RoomID', RoomID);
					localStorage.setItem('RoomName', data.RoomName);
					localStorage.setItem('GameName', data.GameName);
					localStorage.setItem('GameModel', data.GameModel);
					localStorage.setItem('Player', data.Player);
					window.location.href = "GameRoom.php?RoomID=" + RoomID + "&Player="+data.Player + "&GameName=" + data.GameName;
				}
			},
			error : function() {}
		});
	}
	$(window).scroll(function() {
  
	    if ($(document).scrollTop()<=0){
	      //console.log("滚动条已经到达顶部为0");
	    }
	  
	    if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	      //console.log("滚动条已经到达底部为" + $(document).scrollTop());
	      loadRoomList();
	    }
	});
});

// 禁用div下所有标签点击事件
// $("div *").attr("disabled",true);