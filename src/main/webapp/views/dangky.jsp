<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<title>Dang Ky</title>
</head>
<body>
<div id="wrapper">
	<div class="container">
		<div class="row justify-content-around">
	<h3 class="text-center text-uppercase h3 py-3">ĐĂNG KÝ</h3>
	<form:form action="/khachhang/dangky" modelAttribute="kh" class="col-md-6 bg-light p-3 my-3"
		enctype="multipart/form-data">
		<div class="form-group">
		<label>Tài khoản:</label>
		<form:input path="taikhoan" class="form-control"/>
		<form:errors path="taikhoan"></form:errors>
		<br>
		</div>
		<div class="form-group">
		<label>Mật khẩu:</label>
		<form:password path="matkhau" class="form-control"/>
		<form:errors path="matkhau"></form:errors>
		<br>
		</div>
		<div class="form-group">
		<label>Họ và tên:</label>
		<form:input path="hoten" class="form-control"/>
		<form:errors path="hoten"></form:errors>
		<br>
		</div>
		<div class="form-group">
		<label>Ngày sinh:</label>
		<input name="ngaysinh" type="date" class="form-control">
		<br>
		</div>
		<div class="form-group">
		<label>Giới tính:</label>
		<form:radiobuttons items="${gioitinhs}" path="gioitinh" />
		<form:errors path="gioitinh"></form:errors>
		<br>
		</div>
		<div class="form-group">
		<label>Số điện thoại:</label>
		<form:input path="sdt" class="form-control"/>
		<form:errors path="sdt"></form:errors>
		<br>
		</div>
		<div class="form-group">
		<label>Email:</label>
		<form:input path="email" class="form-control"/>
		<form:errors path="email"></form:errors>
		</div>
		 <div class="form-group" style="color: red;">${message}</div> <br>
		<hr>
		<div class="form-group">
		<button class="form-control">Xác nhận</button>
		<br>
		<button class="form-control" formaction="/quayve">Quay về</button>
		</div>
	</form:form>
	</div>
	</div>
</div>
</body>
</html>