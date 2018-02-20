<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> --%>
<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
<!-- Main content -->
<section class="content">
	<div class ="row">
	<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
				<form action="/member/loginPost" method="post">
					<div class="form-group has-feedback">
						<input type="text" name="userid" class="form-control" placeholder="USER ID"/>
						<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>	
					<div class="form-group has-feedback">
						<input type="password" name="userpw" class="form-control"/>
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-xs-8">
							<label>
								<input type="checkbox" name="userCookie">Remember Me
							</label>	
						</div>	

						<div class="col-xs-4">
							<button type="submit" class="btn btn-primary btn-block btn-flat">SignIn</button>
						</div>
					</div>
				</form>
		</div> <!-- /.col (left) -->
	</div> <!-- /.content -->
</section>
<%@ include file="../include/footer.jsp" %>