<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Login</title>
<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/resources/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<body>




	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">

				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Se connecter</h3>
					</div>
					<div class="panel-body">
						<form action="j_spring_security_check" method="post">
							
								<div class="form-group">
									<input type="text" name="j_username" class="form-control" placeholder="nom utilisateur"/>
									</div>
								
								
									<div class="form-group">
									<input type="password" name="j_password" class="form-control" placeholder="mot de passe">
								</div>
								
									<input type="submit" value="Entrer" class="btn  btn-success">
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/sb-admin-2.js"></script>
</body>
</html>
