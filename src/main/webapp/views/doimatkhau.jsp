<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<title>Doi Mat Khau</title>
</head>
<body>
<div id="wrapper">
	<div class="container">
		<div class="row justify-content-around">
	<h3 class="text-center text-uppercase h3 py-3">ĐỔI MẬT KHẨU</h3>
	<form:form action="/khachhang/doimatkhau" modelAttribute="item" class="col-md-6 bg-light p-3 my-3" enctype="multipart/form-data">
		<div class="form-group">
		<label>Mật khẩu:</label>
		<input type="password" name="matkhau" class="form-control" placeholder="Nhập mật khẩu cũ...">
		<br>
		</div>
		<div class="form-group">
		<label>Mật khẩu mới:</label>
		<input type="password" name="matkhaumoi" class="form-control" placeholder="Nhập mật khẩu mới...">
		<br>
		</div>
		<div class="form-group">
		<label>Nhập lại mật khẩu mới:</label>
		<input type="password" name="matkhaulai" class="form-control" placeholder="Nhập lại mật khẩu mới...">
		<br>
		<div class="form-group" style="color: red;">${message}</div> <br>
		<hr>
		<div class="form-group">
		<button class="form-control" formaction="/khachhang/doimatkhau/${item.taikhoan}">Xác nhận</button>
		<br>
		<button class="form-control" formaction="/trangchu/${item.taikhoan}">Quay về</button>
		</div>
		</div>
	</form:form>
	</div>
	</div>
</div>
</body>
</html>