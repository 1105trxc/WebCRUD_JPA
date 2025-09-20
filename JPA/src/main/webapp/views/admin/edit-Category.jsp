<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Category</title>
</head>
<body>
	<c:url value="${pageContext.request.contextPath}/admin/category/edit" var="edit"></c:url>
	<form role="form" action="${edit}" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="id" value="${category.id}" />
		<div class="form-group">
			<label>Tên danh mục:</label>
			<input type="text" class="form-control" name="name" value="${category.name}" />
		</div>
		<div class="form-group">
			<c:url value="/image?fname=${category.icon}" var="imgUrl"></c:url>
			<img class="img-responsive" width="100px" src="${imgUrl}" alt="Category Icon" />
			<label>Ảnh đại diện</label>
			<input type="file" name="icon" />
		</div>
		<button type="submit" class="btn btn-success">Edit</button>
		<button type="reset" class="btn btn-secondary">Reset</button>
	</form>
</body>
</html>