<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Danh sách Danh mục</h2>
<table class="table table-bordered">
  <thead>
    <tr>
      <th>ID</th>
      <th>Hình ảnh</th>
      <th>Tên danh mục</th>
      <th>Hành động</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="category" items="${categories}">
      <tr>
        <td>${category.id}</td>
        <td>
          <img src="${pageContext.request.contextPath}/image?fname=${category.icon}" alt="icon" style="width:40px; height:40px; object-fit:cover;">
        </td>
        <td>${category.name}</td>
        <td>
          <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/category/edit?id=${category.id}">Sửa</a>
          <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/admin/category/delete?id=${category.id}">Xóa</a>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<a class="btn btn-success" href="${pageContext.request.contextPath}/admin/category/add">Thêm danh mục mới</a>