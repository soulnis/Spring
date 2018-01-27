<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->
				<form role="form" action="modifyPage" method="post">
					<input type="hidden" name="no" value="${boardVO.no}">	
					<input type="hidden" name="page" value="${cri.page}">	
					<input type="hidden" name="perPageNum" value="${cri.perPageNum}">	
					<input type="hidden" name="searchType" value="${cri.searchType}">
					<input type="hidden" name="keyword" value="${cri.keyword}">
				</form>
				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail">Title</label>
						<input type="text" name="title" class="form-control" value="${boardVO.title}" readonly="readonly">
					</div>	
					<div class="form-group">
						<label for="exampleInputEmail">Content</label>	
						<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail">Writer</label>	
						<input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
					</div>
				</div> <!-- /.box-body -->
				<div class="box-footer">
					<button type="submit" class="btn btn-warning modifyBtn">MODIFY</button>	
					<button type="submit" class="btn btn-danger removeBtn">REMOVE</button>	
					<button type="submit" class="btn btn-primary goListBtn">LIST ALL</button>	
				</div>


				<script>
					$(document).ready(function() {
						var formObj = $("form[role='form']");
						/* console.log(formObj); */
						
						$(".goListBtn").on("click", function() {
							formObj.attr("method", "get");
							formObj.attr("action", "/sboard/list");
							formObj.submit();
						});

						$(".removeBtn").on("click", function() {
							formObj.attr("action", "/sboard/removePage");
							formObj.submit();
						});

						$(".modifyBtn").on("click", function() {
							formObj.attr("action", "/sboard/modifyPage");	// form태그의 속성추가
							formObj.attr("method", "get");
							formObj.submit(); // form 태그 전송
						});

					});
				</script>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>

