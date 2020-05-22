/*
* @Author: root
* @Date:   2020-02-12 00:32:09
* @Last Modified by:   root
* @Last Modified time: 2020-02-16 18:13:53
*/
$(document).ready(function() {
	var identity = localStorage.getItem("identity");
	console.log(identity);
	if(identity == "Watchwar") { //如果该用户为观战模式，禁止该用户点击 #GameMain
		$("#GameMain").css("pointer-events", "none");
	}

	window.onbeforeunload=function(e){ //退出房间时
	　　var e = window.event||e;  		
		if(identity == "Watchwar") {
			clearInfo();
		}else {
			ExitRoom();
		}
	}
	function clearInfo() {
		localStorage.setItem("RoomID", null);
		localStorage.setItem("identity", null);
		localStorage.setItem("GameModel", null);
		localStorage.setItem("GameName", null);
		localStorage.setItem("RoomName", null);
		localStorage.setItem("Player", null);
	}
	function ExitRoom() {
		$.ajax({
			type: "POST",
			url : "script/CreateRoom.php",
			data: {
				action: "ExitRoom",
				RoomID: localStorage.getItem("RoomID"),
				Player: localStorage.getItem("Player")
			},
			success: function() {},
			error: function() { }
		});
		clearInfo();
	}
});