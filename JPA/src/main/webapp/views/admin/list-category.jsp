<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách Category</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 85%;
            margin: 30px auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.04);
            padding: 25px 30px;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        .navbar {
            margin-bottom: 25px;
        }
        .navbar ul {
            list-style: none;
            padding-left: 0;
            display: flex;
            gap: 20px;
        }
        .navbar li a {
            text-decoration: none;
            color: #0056b3;
            font-weight: 500;
        }
        .navbar li a:hover {
            color: #007bff;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #dee2e6;
            padding: 10px 12px;
            text-align: center;
        }
        th {
            background: #007bff;
            color: #fff;
            font-weight: 600;
        }
        tr:nth-child(even) {
            background: #f1f1f1;
        }
        .btn {
            padding: 6px 14px;
            border-radius: 4px;
            border: none;
            background: #007bff;
            color: #fff;
            cursor: pointer;
            text-decoration: none;
        }
        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="navbar">
        <ul>
            <li><a href="/JPA/admin/dashboard">Dashboard</a></li>
            <li><a href="/JPA/admin/users/list">Users</a></li>
            <li><a href="/JPA/admin/profile">Profile</a></li>
            <li><a href="/JPA/logout">Logout</a></li>
        </ul>
    </div>

    <h2>Danh sách Category</h2>
    <p>Xin chào, <strong>Admin</strong></p>

    <table>
        <thead>
            <tr>
                <th>STT</th>
                <th>Icon</th>
                <th>Tên Category</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cat" items="${categories}" varStatus="stt">
                <tr>
                    <td>${stt.index + 1}</td>
                    <td>
                        <c:if test="${not empty cat.icon}">
                            <img src="${cat.icon}" alt="icon" width="32" height="32"/>
                        </c:if>
                    </td>
                    <td>${cat.name}</td>
                    <td>
                        <a class="btn" href="/JPA/admin/category/edit?id=${cat.id}">Sửa</a>
                        <a class="btn" href="/JPA/admin/category/delete?id=${cat.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty categories}">
                <tr>
                    <td colspan="4">Không có dữ liệu danh mục.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <a class="btn" href="/JPA/admin/category/add">Thêm Category mới</a>
</div>
</body>
</html>