<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title" /></title>
<!-- Bootstrap CSS CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<!-- Custom admin CSS -->
<link href="${pageContext.request.contextPath}/common/admin/admin.css" rel="stylesheet" />
</head>
<body>
	<div class="d-flex">

		<!-- Sidebar -->
		<nav class="bg-dark text-white p-3 vh-100" style="width: 220px;">
			<h4 class="text-center">Admin</h4>
			<ul class="nav flex-column mt-4">
				<li class="nav-item"><a class="nav-link text-white"
					href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link text-white"
					href="${pageContext.request.contextPath}/admin/users">Users</a></li>
				<li class="nav-item"><a class="nav-link text-white"
					href="${pageContext.request.contextPath}/admin/profile">Profile</a></li>
				<li class="nav-item"><a class="nav-link text-white"
					href="${pageContext.request.contextPath}/logout">Logout</a></li>
			</ul>
		</nav>

		<!-- Main Content -->
		<div class="flex-grow-1">
			<!-- Topbar -->
			<nav class="navbar navbar-expand navbar-light bg-light shadow-sm">
				<div class="container-fluid">
					<span class="navbar-text">Xin chào, Admin</span>
				</div>
			</nav>

			<!-- Body -->
			<div class="container mt-4">
				<sitemesh:write property="body" />
			</div>
		</div>
	</div>

	<!-- Bootstrap JS CDN -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>