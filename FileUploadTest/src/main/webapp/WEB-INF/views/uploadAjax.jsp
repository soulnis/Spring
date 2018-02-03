<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}
small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<body>
	<h3>Ajax File Upload</h3>
	<div class="fileDrop"></div>
	<div class="uploadedList"></div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();		
		});

		$(".fileDrop").on("drop", function(event) {
			event.preventDefault();	
			// 파일을 찾기 위하여 dataTransfer.files를 사용(전달된 데이터를 가지고오는 부분)
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];

			console.log(file);

			// FormData 는 IE10이상만 사용 브라우저 체크 필수!
			var formData = new FormData();
			formData.append("file", file);

			$.ajax({
				url: '/uploadAjax',
				data: formData,
				dataType: 'text',
				processData: false,
				contentType: false,
				type: 'POST',
				success: function(data) {
					/* alert(data); */
 					var str = "";
					if(checkImageType(data)) {
						// str ="<div>"+"<img src='displayFile?fileName="+data+"'/>"+data+"</div>"
						str =""
						+"<div>"
						+"<a href='displayFile?fileName="+getImageLink(data)+"'>"
						+"<img src='displayFile?fileName="+data+"'/>"
						+getImageLink(data)
						+"</a>"
						+"<small data-src="+data+">X</small>"
						+"</div>"
					} else {
						console.log("fileName:", data);
						str = ""
						+"<div>"
						+"<a href='displayFile?fileName="+data+"'>"
						+getOriginalName(data)+"</a>"
						+"<small data-src="+data+">X</small>"
						+"</div>";
						console.log("str: ", str);
					}
					$(".uploadedList").append(str); 
				}
			});
		});

		function checkImageType(fileName) {
			var pattern = /jpg$|gif$|png$|jpeg$/i;
			return fileName.match(pattern);
		}

		function getImageLink(fileName) {
			if(!checkImageType(fileName)) {
				return;	
			}
			var front = fileName.substr(0,12);
			var end = fileName.substr(14);
			console.log("front+ent: ", front+end);
			return front + end;	
		}

		function getOriginalName(fileName) {
			if(checkImageType(fileName)) {
				return;	
			}

			var idx = fileName.indexOf("_")+1;
			return fileName.substr(idx);
		}
	</script>

</body>
</html>