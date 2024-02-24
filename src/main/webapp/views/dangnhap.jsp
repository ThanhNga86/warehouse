<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<title>Dang nhap</title>
</head>
<body>
<div id="wrapper">
	<div class="container">
		<div class="row justify-content-around">
		<h3 class="text-center text-uppercase h3 py-3">Đăng Nhập</h3>
<form action="dangnhap" method="post" class="col-md-6 bg-light p-3 my-3">
	<label>Tài khoản:</label> <input type="text" name="taikhoan" value="${users}" class="form-control">
	<label>Mật khẩu:</label> <input type="password" name="matkhau" value="${pass}" class="form-control">
	<input name="remember" type="checkbox">Lưu tài khoản?
	<a style="margin-left: 280px;" href="/quenmatkhau">Quên mật khẩu?</a>
	<div style="color: red;">${message}</div> <br>
	<button class="btn btn-primary" style="margin-left: 320px">Đăng Nhập</button>
	<button class="btn btn-danger" style="margin-left: 20px" formaction="/dangky">Đăng Ký</button>
</form>
	</div>
	</div>
</div>
</body>
</html>