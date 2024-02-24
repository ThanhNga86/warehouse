<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<title>Quen Mat Khau</title>
</head>
<body>
	<div id="wrapper">
		<div class="container">
			<div class="row justify-content-around">
				<h3 class="text-center text-uppercase h3 py-3">QUÊN MẬT KHẨU</h3>
				<form:form enctype="multipart/form-data" class="col-md-6 bg-light p-3 my-3"
					action="/khachhang/quenmatkhau" modelAttribute="mail">
				<div class="form-group">
				<label>Tài khoản:</label><input type="text" name="taikhoan" value="${users}" class="form-control">
				</div>
				<div class="form-group">
				<label>Email:</label><input type="email" name="to" class="form-control"
						placeholder="Nhập email người nhận...">
				</div>
				<div class="form-group" style="color: red;">${message}</div>
				<br>
				<div class="form-group">
				<button class="form-control">Gửi</button>
				<br>
				<button class="form-control" formaction="/quayve">Quay về</button>
				</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>