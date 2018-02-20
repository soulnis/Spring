<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50xp;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}
.pagination {
  width: 100%;
}

.pagination li{
  list-style: none;
  float: left; 
  padding: 3px; 
  border: 1px solid blue;
  margin:3px;  
}

.pagination li a{
  margin: 3px;
  text-decoration: none;  
}
</style>
</head>
<body>
	<h2>Ajax Test Page</h2>
	<div>
		<div>
			REPLYER<input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT<input type="text" name="replytext" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	<ul id="replies">

	</ul>
	<div id="modDiv" style="display: none">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext">
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">Delete</button>
			<button type="button" id="closeBtn">Close</button>
		</div>
	</div>
	<ul class="pagination">
	</ul>
</body>
<script type="text/javascript">
	var bno = 3;
	getPageList(1);
	$("#replies").on("click", ".replyLi button", function() {
		var reply = $(this).parent();

		var no = reply.attr("data-no");
		var replytext = reply.text();

		$(".modal-title").html(no);
		$("#replytext").val(replytext);
		$("#modDiv").show("slow");
	});

	$("#replyModBtn").on("click", function() {
		var no = $(".modal-title").html();
		var replytext = $("#replytext").val();

		$.ajax({
			type : 'put',
			url : "/replies/" + no,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				replytext : replytext
			}),
			dataType : 'text',
			success : function(result) {
				console.log("result:", result);
				if (result == "SUCCESS") {
					alert("수정완료");
					$("#modDiv").hide("slow");
					getAllList();
				}
			}/* ,
						error: function(request, status, error) {
							console.log("status:",status);
							console.log("error:",error);
						} */
		});
	})

	$("#replyDelBtn").on("click", function() {
		var no = $(".modal-title").html();
		var replaytext = $("#replytext").val();

		$.ajax({
			type : "delete",
			url : "/replies/" + no,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : "text",
			success : function(result) {
				console.log("result:", result);
				if (result == "SUCCESS") {
					alert("삭제");
					$("#modDiv").hide("slow");
					// getAllList();
					getPageList(replyPage);
				}
			}
		});
	})

	$("#replyAddBtn").on("click", function() {
		var replyer = $("#newReplyWriter").val();
		var replytext = $("#newReplyText").val();

		$.ajax({
			type : "post",
			url : "/replies",
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Overrid" : "POST"
			},
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replytext : replytext
			}),
			success : function(result) {
				if (result = "SUCCESS") {
					alert("등록완료");
					getAllList();
				}
			}
		});
	});

	function getAllList() {
		$.getJSON("/replies/all/" + bno, function(data) {
			var str = "";
			//console.log(data.length);
			$(data).each(
					function() {
						str = str
								+ "<li data-no='"+this.no+"' class='replyLi'>"
								+ this.no + ":" + this.replytext
								+ "<button>MOD</button>" + "</li>";
					});
			$('#replies').html(str);
		});
	}

	function getPageList(page) {
		$.getJSON("/replies/" + bno + "/" + page, function(data) {
			//console.log(data.list.length);
			var str = "";
			$(data.list).each(
					function() {
						str += "<li data-no='" + this.no + "' class='replyLi'>"
								+ this.no + ":" + this.replytext
								+ "<button>MOD</button>"
					});
			$("#replies").html(str);
			printPaging(data.pageMaker);
		});
	}

	function printPaging(pageMaker) {
		var str = "";
		//console.log(pageMaker);
		if (pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1)+ "'> << </a></li>";
		}

		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
		}

		if (pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
		}
		$('.pagination').html(str);
	}

	var replyPage = 1;
	$(".pagination").on("click", "li a", function(event) {
		event.preventDefault();
		replyPage = $(this).attr("href");
		getPageList(replyPage);
	});

	/* 	function printPaging(pageMaker) {
	 var str = "";
	 if(pageMaker.prev) {
	 str += "<li><a href'"+(pageMaker.startpage-1)+"'> << </a></li>"
	 }
	
	 for(var i = pageMaker.startPage, len = pagemaker.endPage; i <= len; i++) {
	 var strClass = pageMaker.cri.page == i ?'class=active' : '';	
	 str += "<li "+strClass+"><a href='"+i+"</a></li>";
	 }

	 if(pageMaker.next) {
	 str += "<li><a href='"+(pageMaker.endPage+1)+"'> >> </a></li>";
	 }
	 $('.pagination').html(str);
	 } */
</script>
</html>