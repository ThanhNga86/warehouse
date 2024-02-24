<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chu</title>
</head>
<body>
<div> <h1>TRANG CHỦ</h1> </div>
<form:form action="/trangchu" enctype="multipart/form-data" modelAttribute="item">
<button formaction="/khachhang/doimatkhau/${item.taikhoan}">Đổi mật khẩu</button>
</form:form>
</body>
</html>