<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Issue Tracker</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<style type="text/css">
body {
	padding-top: 60px;
}
</style>

</head>
<body>
	<!-- inicio del menu -->
	<jsp:include page="Menu.jsp" />
	<!-- fin de menu -->
	<!-- container -->
	<div class="container">
		<h1>Usuarios <a class="btn"><i class="icon-plus-sign"></i></a></h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
				</tr>
			</thead>
			<tbody>
				<td></td>
				<td></td>
			</tbody>
		</table>
	</div>
	<!-- fin container -->
	<!-- javascript -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/knockout-2.2.1.js"></script>
	<script type="text/javascript">
		$("#menu_usuarios").addClass("active");
	</script>
</body>
</html>