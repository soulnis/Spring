<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Board List</h3>
				</div>
				<div class='box-body'>
					<select name="searchType">
						<option value="n" 
							<c:out value="${cri.searchType == null ? 'selected' : ''}"/>>
							---
						</option>
						<option value="t" 
							<c:out value="${cri.searchType eq 't' ? 'selected' : ''}"/>>
							title
						</option>
						<option value="c" 
							<c:out value="${cri.searchType eq 'c' ? 'selected' : ''}"/>>
							content
						</option>
						<option value="w" 
							<c:out value="${cri.searchType eq 'w' ? 'selected' : ''}"/>>
							writer
						</option>
						<option value="tc" 
							<c:out value="${cri.searchType eq 'tc' ? 'selected' : ''}"/>>
							title, content
						</option>
						<option value="cw" 
							<c:out value="${cri.searchType eq 'cw' ? 'selected' : ''}"/>>
							content, writer
						</option>
						<option value="tcw" 
							<c:out value="${cri.searchType eq 'tcw' ? 'selected' : ''}"/>>
							title, content, writer
						</option>
					</select>
					<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}">
					<button id="searchBtn">Search</button>
					<button id="newBtn">New Board</button>
				</div>
			</div>
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">NO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th style="width: 40px">VIEWCNT</th>
						</tr>
						<c:forEach items="${list}" var="boardVO">
							<tr>
								<td>${boardVO.no}</td>
								<td><a href='/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&no=${boardVO.no}'> ${boardVO.title} <strong>[${boardVO.replycnt}]</strong></a></td>
								<td>${boardVO.writer}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
								<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">

					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a href="${pageMaker.makeSearch(pageMaker.startPage - 1)}">&laquo;</a></li>	
							</c:if>
 							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li
									<c:out value="${(pageMaker.cri.page == idx) ? 'class=active' : ''}"/>>
									<a href="${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>	
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a href="${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>	
							</c:if>
						</ul>	
					</div>

				</div>
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<form id="jobForm">
 	<input type='hidden' name="page" value=${pageMaker.cri.perPageNum}> 
	<input type='hidden' name="perPageNum" value=${pageMaker.cri.perPageNum}>
 	<input type='hidden' name="searchType" value=${searchType}>
	<input type='hidden' name="keyword" value=${keyword}>
</form> 

<script>

	$(function() {
		$('#searchBtn').on("click", function(event) {
		self.location = ""
						+"list"
						+"${pageMaker.makeQuery(1)}"
						+"&searchType="
						+$("select option:selected").val()
						+"&keyword="
						+encodeURIComponent($("#keywordInput").val());
		});
	
		$('#newBtn').on("click", function(event) {
			self.location = "register";
		});

		var result = '${msg}';
		if (result == 'SUCCESS') {
			alert("처리가 완료되었습니다.");
		}	
	});


/*   	$(".pagination li a").on("click", function(event) {
		event.preventDefault(); // 실제 화면의 이동을 막아준다. 
		var targetPage = $(this).attr("href");

		var jobForm = $("#jobForm");
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr("action", "/board/listPage").attr("method", "get");
		jobForm.submit();
	});  */ 

</script>

<%@include file="../include/footer.jsp"%>